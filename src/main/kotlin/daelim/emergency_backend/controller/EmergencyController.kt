package daelim.emergency_backend.controller

import daelim.emergency_backend.database.emergencyHospital.EmergencyHospitalData
import daelim.emergency_backend.database.EmergencyService
import daelim.emergency_backend.database.hospitalInformation.HospitalInformation
import daelim.emergency_backend.models.Response
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
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
class EmergnecyController(val emergencyService: EmergencyService) {

    //emergency hospital data List 반환
    @Operation(summary = "응급 병원 리스트 가져오기", description = "응급 병원 데이터를 페이징하여 반환합니다.")
    @GetMapping("/getEmergencyHospitalList")
    fun getEmergencyHospitals(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "20") size: Int
    ): ResponseEntity<Response<Page<EmergencyHospitalData>>?> {
        return try {
            val response = Response(
                resultCode =  HttpStatus.OK.value(),
                message =  "success",
                data =  emergencyService.getAllEmergencyHospitalData(page, size)
            )
            ResponseEntity.ok(response)
        } catch (e:Exception) {
            val response = Response<Page<EmergencyHospitalData>>(HttpStatus.INTERNAL_SERVER_ERROR.value(),e.message.toString(),null)
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response)
        }
    }

    @Operation(summary = "시군구 검색으로 병원 정보 리스트 반환", description = "시군구 단계별로 병원 정보를 검색하여 리스트를 반환합니다.")
    @GetMapping("/getHospitalInfoByAddr")
    fun getHospitalInfoByAddress(
        @RequestParam stage1:String,
        @RequestParam stage2:String
    ): ResponseEntity<Response<List<HospitalInformation>>?>{
        return try {
            ResponseEntity(Response(HttpStatus.OK.value(),"success",emergencyService.searchWithCity(stage1, stage2)),null,HttpStatus.OK)
        } catch (e:Exception) {
            ResponseEntity(Response(HttpStatus.INTERNAL_SERVER_ERROR.value(),e.message.toString(),null),null, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    //hospital information List 반환
    @Operation(summary = "병원 정보 리스트 반환", description = "병원 데이터를 페이징하여 반환합니다.")
    @GetMapping("/getHospitalInfoList")
    fun getHospitalList(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "20") size: Int
    ): ResponseEntity<Response<Page<HospitalInformation>>?> {
        return try {
            ResponseEntity(Response(HttpStatus.OK.value(),"success",emergencyService.getHospitalInformationsByPage(page, size)),null,HttpStatus.OK)
        } catch (e:Exception) {
            ResponseEntity(Response(HttpStatus.INTERNAL_SERVER_ERROR.value(),e.message.toString(),null),null, HttpStatus.INTERNAL_SERVER_ERROR)
        }

    }

    @Operation(summary = "병원 정보와 응급실 정보 반환", description = "hpid로 병원 정보와 응급실 정보를 선택적으로 반환합니다.")
    @GetMapping("/getEmergencyAndHospitalByHpid/{hpid}")
    fun getEmergencyAndHospitalByHpid(
        @PathVariable hpid: String,
        @RequestParam(required = false, defaultValue = "true") includeHospitalInfo: Boolean,
        @RequestParam(required = false, defaultValue = "true") includeEmergencyData: Boolean
    ): ResponseEntity<Response<Map<String, Any?>>?> {
        val result = emergencyService.findHospitalAndEmergencyDataByHpid(hpid, includeHospitalInfo, includeEmergencyData)

        return if (result["hospitalInfo"] != null || result["emergencyInfo"] != null) {
            val response = Response(
                resultCode = HttpStatus.OK.value(),
                message = "success.",
                data = result
            )
            ResponseEntity.ok(response)
        } else {
            val response = Response(
                resultCode = HttpStatus.NOT_FOUND.value(),
                message = "fail.",
                data = mapOf<String, Any?>("hospitalInfo" to null,"emergencyInfo" to null)
            )
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(response)
        }
    }
}