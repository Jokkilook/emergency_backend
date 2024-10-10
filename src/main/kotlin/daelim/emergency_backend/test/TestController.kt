package daelim.emergency_backend.test

import daelim.emergency_backend.database.emergencyHospital.EmergencyHospitalData
import daelim.emergency_backend.database.EmergencyService
import daelim.emergency_backend.database.hospitalInformation.HospitalInformation
import daelim.emergency_backend.models.AvailavleBedInfo.AvailableBedInfoResult
import daelim.emergency_backend.models.EmergencyAndSevereCaseMessage.EmergencyAndSevereCaseMessageResult
import daelim.emergency_backend.models.EmergencyMedicalInstitutionBasicInfoResult
import daelim.emergency_backend.models.EmergencyMedicalInstitutionInfo.EmergencyMedicalInstitutionInfoResult
import daelim.emergency_backend.models.EmergencyMedicalInstitutionLocationResult
import daelim.emergency_backend.models.Response
import daelim.emergency_backend.models.SevereCaseAcceptanceInfo.SevereCaseAcceptanceInfoResult
import daelim.emergency_backend.models.TraumaCenterBasicInfo.TraumaCenterBasicInfoResult
import daelim.emergency_backend.models.TraumaCenterListResult
import daelim.emergency_backend.models.TraumaCenterLocation.TraumaCenterLocationResult
import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.lang.Exception

@RestController
class TestController(val testService: TestService, val emergencyService: EmergencyService) {

    //emergency hospital data List 반환
    @GetMapping("/getEmergencyHospitalList")
    fun getEmergencyHospitals(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "20") size: Int
    ): Page<EmergencyHospitalData> {
        return emergencyService.getAllEmergencyHospitalData(page, size)
    }

    //시군구 검색으로 병원 정보 리스트 반환하기
    @GetMapping("/getHospitalInfoByAddr")
    fun getHospitalInfoByAddress(
        @RequestParam stage1:String,
        @RequestParam stage2:String
    ):ResponseEntity<Response<List<HospitalInformation>>?>{
        return try {
            ResponseEntity(Response(HttpStatus.OK.value(),"success",emergencyService.searchWithCity(stage1, stage2)),null,HttpStatus.OK)
        } catch (e:Exception) {
            ResponseEntity(Response(HttpStatus.INTERNAL_SERVER_ERROR.value(),e.message.toString(),null),null, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }


    //hospital information List 반환
    @GetMapping("/getHospitalInfoList")
    fun getHospitalList(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "20") size: Int
    ): Page<HospitalInformation> {
        return emergencyService.getHospitalInformationsByPage(page, size)
    }

    /* 병원 정보만 요청하려면 /test10/{hpid}?includeEmergencyData=false
    응급실 정보만 요청하려면 /test10/{hpid}?includeHospitalInfo=false
    둘 다 요청하려면 /test10/{hpid} 또는 /hospital/{hpid}?includeHospitalInfo=true&includeEmergencyData=true */
    @GetMapping("/getEmergencyAndHospitalByHpid/{hpid}")
    fun getEmergencyAndHospitalByHpid(
        @PathVariable hpid: String,
        @RequestParam(required = false, defaultValue = "true") includeHospitalInfo: Boolean,
        @RequestParam(required = false, defaultValue = "true") includeEmergencyData: Boolean
    ): ResponseEntity<Response<Pair<HospitalInformation?, EmergencyHospitalData?>>> {
        val result = emergencyService.findHospitalAndEmergencyDataByHpid(hpid, includeHospitalInfo, includeEmergencyData)

        return if (result.first != null || result.second != null) {
            val response = Response(
                resultCode = HttpStatus.OK.value(),
                message = "success.",
                data = result
            )
            ResponseEntity.ok(response)
        } else {
            val response = Response<Pair<HospitalInformation?, EmergencyHospitalData?>>(
                resultCode = HttpStatus.NOT_FOUND.value(),
                message = "fail.",
                data = Pair(null, null)
            )
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(response)
        }
    }

    //---- 이 밑은 테스트 코드, 위는 나중에 옮길 것 --------------------------------------------------

    //
    @GetMapping("/test/{error}")
    fun <T> test(
        @PathVariable error:Int
    ): ResponseEntity<Response<String>> {

        val response = Response<String>(HttpStatus.OK.value(),"success","Hello zito Hello")

        return try {
            ResponseEntity(response,null,HttpStatus.OK)
        } catch (e:Error) {
            ResponseEntity(null,null,HttpStatus.BAD_REQUEST)
        }
    }

    @GetMapping("/testhospital")
    fun testHospital(): HospitalInformation?{
        return emergencyService.testHospital("1")
    }

    @GetMapping("/getHospitalList")
    fun getHostipalList():AvailableBedInfoResult {

        return AvailableBedInfoResult(header = null, body = null)
    }

    @GetMapping("/test1")
    fun test1():AvailableBedInfoResult{
        return testService.getTest("/getEmrrmRltmUsefulSckbdInfoInqire", mapOf())
    }

    @GetMapping("/test2")
    fun test2():SevereCaseAcceptanceInfoResult{
        return testService.getTest("/getSrsillDissAceptncPosblInfoInqire", mapOf())
    }
    @GetMapping("/test3")
    fun test3():EmergencyMedicalInstitutionInfoResult{
        return testService.getTest("/getEgytListInfoInqire", mapOf())
    }

    @GetMapping("/test4")
    fun test4():EmergencyMedicalInstitutionLocationResult{
        return testService.getTest("/getEgytLcinfoInqire", mapOf())
    }

    @GetMapping("/test5")
    fun test5():EmergencyMedicalInstitutionBasicInfoResult{
        return testService.getTest("/getEgytBassInfoInqire", mapOf())
    }

    @GetMapping("/test6")
    fun test6():TraumaCenterListResult{
        return testService.getTest("/getStrmListInfoInqire", mapOf())
    }

    @GetMapping("/test7")
    fun test7():TraumaCenterLocationResult{
        return testService.getTest("/getStrmLcinfoInqire", mapOf())
    }

    @GetMapping("/test8")
    fun test8():TraumaCenterBasicInfoResult{
        return testService.getTest("/getStrmBassInfoInqire", mapOf())
    }

    @GetMapping("/test9")
    fun test9():EmergencyAndSevereCaseMessageResult{
        return testService.getTest("/getEmrrmSrsillDissMsgInqire", mapOf())
    }
}