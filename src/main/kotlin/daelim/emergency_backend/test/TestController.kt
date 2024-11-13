package daelim.emergency_backend.test

import daelim.emergency_backend.Service.EmergencyService
import daelim.emergency_backend.infra.entity.HospitalInformation
import daelim.emergency_backend.models.datagokr.AvailableBedInfoResult
import daelim.emergency_backend.models.Response
import io.swagger.v3.oas.annotations.tags.Tag
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import utils.EmergencyUtils

@Tag(name = "Test API", description = "테스트 API")
@RestController
class TestController(val testService: TestService, val emergencyService: EmergencyService) {
    //---- 이 밑은 테스트 코드, 위는 나중에 옮길 것 --------------------------------------------------
    val logger = LoggerFactory.getLogger(TestController::class.java)

    @GetMapping("/test/distance")
    fun testDistance(
        @RequestParam(defaultValue = "37.34399215") lat: Double,
        @RequestParam(defaultValue = "126.94353722") lon: Double):Double{

        return EmergencyUtils.getDistanceWithLonLat(lat,lon,37.34440803,126.94307256)
    }

    @GetMapping("/test/{error}")
    fun <T> test(
        @PathVariable error:Int
    ): ResponseEntity<Response<String>> {

        val response = Response<String>(HttpStatus.OK.value(),"success","bye bye jito bye bye")


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
    fun getHostipalList(): AvailableBedInfoResult {

        return AvailableBedInfoResult(header = null, body = null)
    }

//    @GetMapping("/test1")
//    fun test1():AvailableBedInfoResult{
//        return testService.getTest("/getEmrrmRltmUsefulSckbdInfoInqire", mapOf())
//    }
//
//    @GetMapping("/test2")
//    fun test2():SevereCaseAcceptanceInfoResult{
//        return testService.getTest("/getSrsillDissAceptncPosblInfoInqire", mapOf())
//    }
//    @GetMapping("/test3")
//    fun test3():EmergencyMedicalInstitutionInfoResult{
//        return testService.getTest("/getEgytListInfoInqire", mapOf())
//    }
//
//    @GetMapping("/test4")
//    fun test4():EmergencyMedicalInstitutionLocationResult{
//        return testService.getTest("/getEgytLcinfoInqire", mapOf())
//    }
//
//    @GetMapping("/test5")
//    fun test5():EmergencyMedicalInstitutionBasicInfoResult{
//        return testService.getTest("/getEgytBassInfoInqire", mapOf())
//    }
//
//    @GetMapping("/test6")
//    fun test6():TraumaCenterListResult{
//        return testService.getTest("/getStrmListInfoInqire", mapOf())
//    }
//
//    @GetMapping("/test7")
//    fun test7():TraumaCenterLocationResult{
//        return testService.getTest("/getStrmLcinfoInqire", mapOf())
//    }
//
//    @GetMapping("/test8")
//    fun test8():TraumaCenterBasicInfoResult{
//        return testService.getTest("/getStrmBassInfoInqire", mapOf())
//    }
//
//    @GetMapping("/test9")
//    fun test9():EmergencyAndSevereCaseMessageResult{
//        return testService.getTest("/getEmrrmSrsillDissMsgInqire", mapOf())
//    }
}