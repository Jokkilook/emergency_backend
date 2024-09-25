package daelim.emergency_backend.models.TraumaCenterLocation

data class TraumaCenterLocationResult(
    val resultCode: Int, //결과코드
    val resultMag: String, //결과메세지
    val items: List<TraumaCenterLocation>
)