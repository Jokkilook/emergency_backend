package daelim.emergency_backend.models.EmergencyMedicalInstitutionInfo

data class EmergencyMedicalInstitutionInfoResult(
    val resultCode:Int, //결과코드
    val resultMsg:String, //결과메세지
    val items:List<EmergencyMedicalInstitutionInfo> //응급의료기관 목록정보
)
