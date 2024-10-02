package daelim.emergency_backend.test

import daelim.emergency_backend.models.AvailavleBedInfo.AvailableBedInfoResult
import daelim.emergency_backend.models.EmergencyAndSevereCaseMessage.EmergencyAndSevereCaseMessageResult
import daelim.emergency_backend.models.EmergencyMedicalInstitutionBasicInfoResult
import daelim.emergency_backend.models.EmergencyMedicalInstitutionInfo.EmergencyMedicalInstitutionInfoResult
import daelim.emergency_backend.models.EmergencyMedicalInstitutionLocationResult
import daelim.emergency_backend.models.SevereCaseAcceptanceInfo.SevereCaseAcceptanceInfoResult
import daelim.emergency_backend.models.TraumaCenterBasicInfo.TraumaCenterBasicInfoResult
import daelim.emergency_backend.models.TraumaCenterListResult
import daelim.emergency_backend.models.TraumaCenterLocation.TraumaCenterLocationResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
class TestController(val testService: TestService) {

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