package daelim.emergency_backend.exception

import daelim.emergency_backend.models.Response
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    private val logger = LoggerFactory.getLogger(this::class.java)

    @ExceptionHandler(EmergencyException::class)
    fun handleEmergencyException(e: EmergencyException): ResponseEntity<Response<Nothing>> {
        logger.error("Emergency Exception 발생: ${e.errorCode.code} - ${e.message}", e)
        
        val status = when (e) {
            is DataNotFoundException,
            is HospitalNotFoundException,
            is EmergencyDataNotFoundException -> HttpStatus.NOT_FOUND
            else -> HttpStatus.INTERNAL_SERVER_ERROR
        }

        val response = Response<Nothing>(
            resultCode = status.value(),
            message = "[${e.errorCode.code}] ${e.message}",
            data = null
        )

        return ResponseEntity(response, status)
    }

    @ExceptionHandler(Exception::class)
    fun handleGeneralException(e: Exception): ResponseEntity<Response<Nothing>> {
        logger.error("Unexpected Exception 발생: ${e.message}", e)
        
        val response = Response<Nothing>(
            resultCode = HttpStatus.INTERNAL_SERVER_ERROR.value(),
            message = "시스템 에러가 발생했습니다. 관리자에게 문의하세요.",
            data = null
        )

        return ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR)
    }
}
