package daelim.emergency_backend.exception

enum class ErrorCode(
    val code: Int,
    val message: String
) {
    // 데이터 관련 에러
    DATA_NOT_FOUND(1001, "요청한 데이터를 찾을 수 없습니다"),
    INVALID_PARAMETER(1002, "잘못된 파라미터가 전달되었습니다"),
    INVALID_COORDINATES_PARAMETER(1003,"좌표 파라미터가 범위를 벗어났습니다."),
    
    // 병원 관련 에러
    HOSPITAL_NOT_FOUND(2001, "병원 정보를 찾을 수 없습니다"),
    EMERGENCY_DATA_NOT_FOUND(2002, "응급실 정보를 찾을 수 없습니다"),
    
    // 시스템 에러
    SYSTEM_ERROR(3001, "시스템 에러가 발생했습니다"),
    DATABASE_ERROR(3002, "데이터베이스 처리 중 에러가 발생했습니다")


}

open class EmergencyException(
    val errorCode: ErrorCode,
    override val message: String = errorCode.message,
    override val cause: Throwable? = null
) : RuntimeException(message, cause)

class DataNotFoundException(
    message: String = ErrorCode.DATA_NOT_FOUND.message,
    cause: Throwable? = null
) : EmergencyException(ErrorCode.DATA_NOT_FOUND, message, cause)

class HospitalNotFoundException(
    message: String = ErrorCode.HOSPITAL_NOT_FOUND.message,
    cause: Throwable? = null
) : EmergencyException(ErrorCode.HOSPITAL_NOT_FOUND, message, cause)

class EmergencyDataNotFoundException(
    message: String = ErrorCode.EMERGENCY_DATA_NOT_FOUND.message,
    cause: Throwable? = null
) : EmergencyException(ErrorCode.EMERGENCY_DATA_NOT_FOUND, message, cause)

class InvalidParameterException(
    message: String = ErrorCode.INVALID_PARAMETER.message,
    cause: Throwable? = null
) : EmergencyException(ErrorCode.INVALID_PARAMETER, message, cause)

class InvalidCoordinatesParameterException(
    message: String = ErrorCode.INVALID_COORDINATES_PARAMETER.message,
    cause: Throwable? = null
): EmergencyException(ErrorCode.INVALID_COORDINATES_PARAMETER, message,cause)

class SystemException(
    message: String = ErrorCode.SYSTEM_ERROR.message,
    cause: Throwable? = null
) : EmergencyException(ErrorCode.SYSTEM_ERROR, message, cause)
