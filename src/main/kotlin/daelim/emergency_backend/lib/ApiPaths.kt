package daelim.emergency_backend.lib

object Path {
}

object ApiPaths {
    const val LOCALHOST = "http://localhost:"

    private const val COMMON = "/common"
    const val ERROR = "$COMMON/error"

    private  const val APP = "/app"
    const val HOSPITAL = "$APP/getEmergencyHospitalList"
}
