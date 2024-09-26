package daelim.emergency_backend.models.SevereCaseAcceptanceInfo

data class SevereCaseAcceptanceInfoResult(
    val resultCode:Int, //결과코드
    val resultMag:String, //결과메세지
    val items:List<SevereCaseAcceptanceInfo> //중증질환자 수용가능 정보
)
