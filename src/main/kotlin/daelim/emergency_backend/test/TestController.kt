package daelim.emergency_backend.test

import daelim.emergency_backend.database.emergencyHospital.EmergencyHospitalData
import daelim.emergency_backend.database.EmergencyService
import daelim.emergency_backend.database.hospitalInformation.HospitalInformation
import daelim.emergency_backend.models.AvailavleBedInfo.AvailableBedInfoResult
import daelim.emergency_backend.models.EmergencyAndSevereCaseMessage.EmergencyAndSevereCaseMessageResult
import daelim.emergency_backend.models.EmergencyMedicalInstitutionBasicInfoResult
import daelim.emergency_backend.models.EmergencyMedicalInstitutionInfo.EmergencyMedicalInstitutionInfoResult
import daelim.emergency_backend.models.EmergencyMedicalInstitutionLocationResult
import daelim.emergency_backend.models.SevereCaseAcceptanceInfo.SevereCaseAcceptanceInfoResult
import daelim.emergency_backend.models.TraumaCenterBasicInfo.TraumaCenterBasicInfoResult
import daelim.emergency_backend.models.TraumaCenterListResult
import daelim.emergency_backend.models.TraumaCenterLocation.TraumaCenterLocationResult
import org.springframework.data.domain.Page
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController(val testService: TestService, val emergencyService: EmergencyService) {

    @GetMapping("/test")
    fun test(): EmergencyHospitalData?{
        return emergencyService.test("1")
    }

    @GetMapping("/testhospital")
    fun testHospital(): HospitalInformation?{
        return emergencyService.testHospital("1")
    }
    @GetMapping("/emergency-hospitals")
    fun getEmergencyHospitals(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "20") size: Int
    ): Page<EmergencyHospitalData> {
        return emergencyService.getAllEmergencyHospitalData(page, size)
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
    @GetMapping("/getHospitalList")
    fun getHostipalList():AvailableBedInfoResult {

        return AvailableBedInfoResult(header = null, body = null)
    }

    //시군구 검색으로 병원 정보 리스트 반환하기
    @GetMapping("/getHospitalInfoByAddr")
    fun getByAddress(
        @RequestParam stage1:String,
        @RequestParam stage2:String
    ):List<HospitalInformation>?{
        return emergencyService.searchWithCity(stage1, stage2)
    }

}