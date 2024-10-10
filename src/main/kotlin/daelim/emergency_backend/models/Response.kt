package daelim.emergency_backend.models

data class  Response <T> (
    var resultCode:Int,
    var message:String,
    var data:T?
)
