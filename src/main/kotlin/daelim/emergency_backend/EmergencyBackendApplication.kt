package daelim.emergency_backend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class EmergencyBackendApplication

fun main(args: Array<String>) {
	runApplication<EmergencyBackendApplication>(*args)
}
