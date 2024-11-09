package daelim.emergency_backend.controller

import daelim.emergency_backend.database.emergencyHospital.EmergencyHospitalData
import daelim.emergency_backend.database.EmergencyService
import daelim.emergency_backend.database.hospitalInformation.HospitalInformation
import daelim.emergency_backend.database.hospitalInformation.HospitalInformationWithDistance
import daelim.emergency_backend.exception.DataNotFoundException
import daelim.emergency_backend.exception.EmergencyException
import daelim.emergency_backend.exception.ErrorCode
import daelim.emergency_backend.exception.HospitalNotFoundException
import daelim.emergency_backend.models.Response
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.lang.Exception

@Tag(name = "Emergency API", description = "응급실, 병원 정보 반환 API")
@RestController
class EmergencyController(val emergencyService: EmergencyService) {
    val logger = LoggerFactory.getLogger(EmergencyController::class.java)

    //emergency hospital data List 반환
    @Operation(summary = "응급 병원 리스트 가져오기", description = "응급 병원 데이터를 페이징하여 반환합니다.")
    @GetMapping("/getEmergencyHospitalList")
    fun getEmergencyHospitals(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "20") size: Int,
        @RequestParam(defaultValue = "0") sortType: Int  // Add sortType parameter
    ): ResponseEntity<Response<Page<EmergencyHospitalData>>?> {
        return try {
            val response = Response(
                HttpStatus.OK.value(),
                "success",
                emergencyService.getAllEmergencyHospitalData(page, size, sortType)  // Pass sortType to the service method
            )
            ResponseEntity.ok(response)
        } catch (e: EmergencyException) {
            logger.error(e.message ?: "Failed error")
            val response = Response<Page<EmergencyHospitalData>>(e.errorCode.code, e.message, null)
            ResponseEntity(response, null, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }


    @Operation(summary = "시군구 검색으로 병원 정보 리스트 반환", description = "시군구 단계별로 병원 정보를 검색하여 리스트를 반환합니다.")
    @GetMapping("/getHospitalInfoByAddr")
    fun getHospitalInfoByAddress(
        @RequestParam stage1:String,
        @RequestParam stage2:String,
        @RequestParam(defaultValue = "0") sortType: Int,
        @RequestParam(required = false) filter: List<String>?,
        @RequestParam(required = false) originLat: Double?,
        @RequestParam(required = false) originLon: Double?,
    ): ResponseEntity<Response<List<HospitalInformationWithDistance>>>{
        val data = emergencyService.searchWithCity(stage1, stage2, sortType, filter, originLat, originLon)

        return ResponseEntity(Response(HttpStatus.OK.value(),"success",data),null,HttpStatus.OK)
    }

    //hospital information List 반환
    @Operation(summary = "병원 정보 리스트 반환", description = "병원 데이터를 페이징하여 반환합니다.")
    @GetMapping("/getHospitalInfoList")
    fun getHospitalList(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "20") size: Int,
        @RequestParam(defaultValue = "0") sortType: Int,
        @RequestParam(required = false) originLat: Double?,
        @RequestParam(required = false) originLon: Double?,
    ): ResponseEntity<Response<Page<HospitalInformationWithDistance>>?> {
        var rootResult :Page<HospitalInformationWithDistance> =  Page.empty()
        try{
            val result = emergencyService.getHospitalInformationsByPage(page, size,sortType,originLat,originLon)

            rootResult = result
        }catch (
            e:EmergencyException
        ){

            logger.info("getHospitalInfoList ---- no more hospital data")
            logger.error(ErrorCode.HOSPITAL_NOT_FOUND.code.toString())
            logger.error(ErrorCode.HOSPITAL_NOT_FOUND.message)
            logger.warn("병원 정보가 없습니다")
            logger.warn("unable to find hospital data")
        }
        if(rootResult== null){

            throw HospitalNotFoundException("no more hospital data")
        }

        val response = Response(
            resultCode = HttpStatus.OK.value(),
            message = "success.",
            data = rootResult
        )
        return ResponseEntity.ok(response)
    }

    @Operation(summary = "병원 정보와 응급실 정보 반환", description = "hpid로 병원 정보와 응급실 정보를 선택적으로 반환합니다.")
    @GetMapping("/getEmergencyAndHospitalByHpid/{hpid}")
    fun getEmergencyAndHospitalByHpid(
        @PathVariable hpid: String,
        @RequestParam(required = false, defaultValue = "true") includeHospitalInfo: Boolean,
        @RequestParam(required = false, defaultValue = "true") includeEmergencyData: Boolean
    ): ResponseEntity<Response<Map<String, Any?>>?> {
        var rootResult :Map<String, Any?> = emptyMap()
        try{
            val result = emergencyService.findHospitalAndEmergencyDataByHpid(hpid, includeHospitalInfo, includeEmergencyData)
            rootResult = result
        }catch (
            e:EmergencyException
        ){

            logger.info("getEmergencyAndHospitalByHpid ---- invalid HPID")
            logger.error(ErrorCode.DATA_NOT_FOUND.code.toString())
            logger.error(ErrorCode.DATA_NOT_FOUND.message)
            logger.warn("정확한 hpid를 입력하세요")
            logger.warn("request valid hpid")
        }
        if(rootResult["hospitalInfo"] == null && rootResult["emergencyInfo"] == null){

            throw DataNotFoundException("invalid HPID")
        }


        val response = Response(
            resultCode = HttpStatus.OK.value(),
            message = "success.",
            data = rootResult
        )
        return ResponseEntity.ok(response)

    }
}