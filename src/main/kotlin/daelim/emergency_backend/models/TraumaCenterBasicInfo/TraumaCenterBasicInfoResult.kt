package daelim.emergency_backend.models.TraumaCenterBasicInfo

data class TraumaCenterBasicInfoResult(
    val resultCode: Int, //결과코드
    val resultMag: String, //결과메세지
    val items: List<TraumaCenterBasicInfo>
)