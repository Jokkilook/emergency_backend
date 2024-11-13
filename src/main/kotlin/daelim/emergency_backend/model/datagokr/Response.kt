package daelim.emergency_backend.model.datagokr

data class  Response <T> (
    var resultCode:Int,
    var message:String,
    var data:T?
)
