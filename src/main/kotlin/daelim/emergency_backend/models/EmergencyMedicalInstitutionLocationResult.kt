package daelim.emergency_backend.models

data class EmergencyMedicalInstitutionLocationResult(
val resultCode:Int,//결과코드
val resultMag : String, //결과메시지

val Items: List<EmergencyMedicalInstitutionLocation>
//병원 목록
)