package daelim.emergency_backend.lib

object ApiPaths {
    const val LOCALHOST = "http://localhost:"

    private const val COMMON = "/common"
    const val ERROR = "$COMMON/error"

    private const val VERSION = "v1"

    private  const val APP = "$VERSION/app"
    const val HOSPITAL = "$APP/hospital"
    const val HOSPITAL_LIST = "$APP/hospitalList"
    const val EMERGENCY_LIST = "$APP/emergencyList"
    const val HOSPITAL_LIST_ADDRESS = "$APP/hospitalListByAddr"
}
enum class SortType {
    NAMEASC,
    NAMEDESC,
    DISTANCEASC,
    DISTANCEDESC,

}