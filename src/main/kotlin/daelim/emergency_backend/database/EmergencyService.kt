package daelim.emergency_backend.database

import daelim.emergency_backend.database.emergencyHospital.EmergencyHospitalData
import daelim.emergency_backend.database.emergencyHospital.EmergencyRepository
import daelim.emergency_backend.database.hospitalInformation.HospitalInformation
import daelim.emergency_backend.database.hospitalInformation.HospitalRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class EmergencyService(val emergencyRepository: EmergencyRepository,
                       val hospitalRepository: HospitalRepository,
                       ) {

    fun test(id: String) : EmergencyHospitalData? {
        return emergencyRepository.findById(id).orElse(null)
    }

    fun testHospital(id: String) : HospitalInformation? {
        return hospitalRepository.findById(id).orElse(null)
    }
    
    fun getAllEmergencyHospitalData(page: Int, size: Int): Page<EmergencyHospitalData> {
        val pageable = PageRequest.of(page, size)
        return emergencyRepository.findAll(pageable)
    }
    
    fun searchWithCity(stage1:String, stage2:String) : List<HospitalInformation> {

        return hospitalRepository.findByAddress(stage1,stage2);
    }



    fun getHospitalInformationsByPage(page: Int, size: Int): Page<HospitalInformation> {
        val pageable = PageRequest.of(page, size)
        return hospitalRepository.findAll(pageable)
    }
}