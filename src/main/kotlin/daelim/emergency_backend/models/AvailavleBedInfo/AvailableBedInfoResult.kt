package daelim.emergency_backend.models.AvailavleBedInfo

data class AvailableBedInfoResult(
    val resultCode:Int,
    val resultMag:String,
    val items:List<AvailableBedInfo>
)
