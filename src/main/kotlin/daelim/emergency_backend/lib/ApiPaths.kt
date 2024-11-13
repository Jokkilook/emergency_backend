package daelim.emergency_backend.lib

object Path {
}

object ApiPaths {
    const val LOCALHOST = "http://localhost:"

    private const val COMMON = "/common"
    const val ERROR = "$COMMON/error"

    private const val VERSION = "v1"

    private  const val APP = "$VERSION/app"
    const val HOSPITAL = "$APP/hospital"
}

