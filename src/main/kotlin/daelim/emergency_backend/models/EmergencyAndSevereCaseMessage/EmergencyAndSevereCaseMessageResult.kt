package daelim.emergency_backend.models.EmergencyAndSevereCaseMessage

data class EmergencyAndSevereCaseMessageResult(
    val resultCode: Int, //결과코드
    val resultMag: String, //결과메세지
    val items: List<EmergencyAndSevereCaseMessage>
)