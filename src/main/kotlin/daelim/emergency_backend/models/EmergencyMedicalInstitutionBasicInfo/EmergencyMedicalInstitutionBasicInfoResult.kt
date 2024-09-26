package daelim.emergency_backend.models


//응급의료기관 기본정보 조회
data class EmergencyMedicalInstitutionBasicInfoResult(
val resultCode:Int,//결과코드
val resultMag : String, //결과메시지

val Items: List<EmergencyMedicalInstitutionBasicInfo>

)