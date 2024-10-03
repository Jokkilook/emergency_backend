package daelim.emergency_backend.database

import org.springframework.stereotype.Service

@Service
class EmergencyService(val emergencyRepository: EmergencyRepository) {

    fun test(id: String) : EmergencyHospitalData? {
        return emergencyRepository.findById(id).orElse(null)
    }
}