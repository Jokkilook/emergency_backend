package daelim.emergency_backend.models.EmergencyMedicalInstitutionInfo

data class EmergencyMedicalInstitutionInfo(
    val rnum:Int, //아이템 번호
    val hpid:String, //기관ID
    val phpid:String, //구 기관ID(old)
    val dutyEmcls:String, //응급의료기관분류코드
    val dutyEmclsName:String, //응급의료기관분류명
    val dutyAddr:String, //주소
    val dutyName:String, //기관명
    val dutyTel1:String, //대표전화1
    val dutyTel3:String, //응급실전화
    val wgs84Lon:Double, //병원경도
    val wgs84Lat:Double //병원 위도
)
