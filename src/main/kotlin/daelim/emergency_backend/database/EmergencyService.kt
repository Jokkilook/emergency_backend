package daelim.emergency_backend.database

import daelim.emergency_backend.database.emergencyHospital.EmergencyHospitalData
import daelim.emergency_backend.database.emergencyHospital.EmergencyRepository
import daelim.emergency_backend.database.hospitalInformation.HospitalInformation
import daelim.emergency_backend.database.hospitalInformation.HospitalRepository
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class EmergencyService(val emergencyRepository: EmergencyRepository,
                       val hospitalRepository: HospitalRepository,
) {
    val logger = LoggerFactory.getLogger(EmergencyService::class.java)

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

    fun findHospitalAndEmergencyDataByHpid(
        hpid: String,
        includeHospitalInfo: Boolean = true,
        includeEmergencyData: Boolean = true
    ): Map<String, Any?> {
        val hospitalInfo = if (includeHospitalInfo) {
            hospitalRepository.findByHpid(hpid)
        } else {
            null
        }

        val emergencyData = if (includeEmergencyData) {
            emergencyRepository.findByHpid(hpid)
        } else {
            null
        }


        return mutableMapOf<String, Any?>("hospitalInfo" to hospitalInfo,"emergencyInfo" to emergencyData)
    }
}