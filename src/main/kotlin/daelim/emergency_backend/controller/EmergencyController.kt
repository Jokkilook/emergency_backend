package daelim.emergency_backend.controller

import daelim.emergency_backend.Infra.Entity.EmergencyHospitalData
import daelim.emergency_backend.Infra.Entity.HospitalInformationWithDistance
import daelim.emergency_backend.Service.EmergencyService
import daelim.emergency_backend.exception.DataNotFoundException
import daelim.emergency_backend.exception.EmergencyException
import daelim.emergency_backend.exception.ErrorCode
import daelim.emergency_backend.exception.HospitalNotFoundException
import daelim.emergency_backend.lib.ApiPaths
import daelim.emergency_backend.lib.SortType
import daelim.emergency_backend.models.Response
import daelim.emergency_backend.models.hospital.EmergencyHospitalDTO
import daelim.emergency_backend.models.hospital.HospitalInformationDTO
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Emergency API", description = "응급실, 병원 정보 반환 API")
@RestController
class EmergencyController(val emergencyService: EmergencyService) {
    val logger = LoggerFactory.getLogger(EmergencyController::class.java)

    //emergency hospital data List 반환
    @Operation(summary = "응급 병원 리스트 가져오기", description = "응급 병원 데이터를 페이징, 정렬, 필터링하여 반환합니다.")
    @GetMapping(ApiPaths.EMERGENCY_LIST, produces = ["application/json"])
    @ResponseBody
    fun getEmergencyHospitals(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "20") size: Int,
        @RequestParam(required = false, defaultValue = "NAMEASC") sortType: SortType,  // SortType으로 변경
        @RequestParam(required = false, defaultValue = "") filter: List<String>?  // 필터 추가
    ): ResponseEntity<Response<Page<EmergencyHospitalDTO>>?> {
        return try {
            // 응급 병원 데이터를 가져오는 서비스 메소드 호출
            val response = Response(
                HttpStatus.OK.value(),
                "success",
                emergencyService.getAllEmergencyHospitalData(page, size, sortType, filter)  // SortType과 filter를 전달
            )
            ResponseEntity.ok(response)
        } catch (e: EmergencyException) {
            logger.error(e.message ?: "Failed error")
            val response = Response<Page<EmergencyHospitalDTO>>(e.errorCode.code, e.message, null)
            ResponseEntity(response, null, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }



    @Operation(summary = "시군구 검색으로 병원 정보 리스트 반환", description = "시군구 단계별로 병원 정보를 검색하여 리스트를 반환합니다.")
    @GetMapping(ApiPaths.HOSPITAL_LIST_ADDRESS, produces = ["application/json"])
    @ResponseBody
    fun getHospitalInfoByAddress(
        @RequestParam stage1:String,
        @RequestParam stage2:String,
        @RequestParam(required = false, defaultValue = "NAMEASC") sortType: SortType,
        @RequestParam(required = false) filter: List<String>?,
        @RequestParam(required = false) originLat: Double?,
        @RequestParam(required = false) originLon: Double?,
    ): ResponseEntity<Response<List<HospitalInformationDTO>>>{
        val data = emergencyService.searchWithCity(stage1, stage2, sortType, filter, originLat, originLon)

        return ResponseEntity(Response(HttpStatus.OK.value(),"success",data),null,HttpStatus.OK)
    }

    //hospital information List 반환
    @Operation(summary = "병원 정보 리스트 반환", description = "병원 데이터를 페이징하여 반환합니다.")
    @GetMapping(ApiPaths.HOSPITAL_LIST, produces = ["application/json"])
    @ResponseBody
    fun getHospitalList(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "20") size: Int,
        @RequestParam(required = false, defaultValue = "NAMEASC") sortType: SortType,
        @RequestParam(required = false) filter: List<String>?,
        @RequestParam(required = false) originLat: Double?,
        @RequestParam(required = false) originLon: Double?,
    ): ResponseEntity<Response<Page<HospitalInformationDTO>>?> {
        var rootResult :Page<HospitalInformationDTO> =  Page.empty()
        try{
            val result = emergencyService.getHospitalInformationsByPage(page, size,sortType,filter,originLat,originLon)

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
    @GetMapping(ApiPaths.HOSPITAL, produces = ["application/json"])
    @ResponseBody
    fun getEmergencyAndHospitalByHpid(
        @RequestParam hpid: String,
        @RequestParam(required = false, defaultValue = "true") includeHospitalInfo: Boolean,
        @RequestParam(required = false, defaultValue = "true") includeEmergencyData: Boolean,
        @RequestParam(required = false) originLat: Double?,
        @RequestParam(required = false) originLon: Double?,
    ): ResponseEntity<Response<Map<String, Any?>>?> {
        val data = emergencyService.findHospitalAndEmergencyDataByHpid(
            hpid,
            includeHospitalInfo,
            includeEmergencyData,
            originLat,originLon
        )

        return ResponseEntity(Response(HttpStatus.OK.value(),"success",data),null,HttpStatus.OK)
    }
}