package daelim.emergency_backend.database

import daelim.emergency_backend.database.emergencyHospital.EmergencyHospitalData
import daelim.emergency_backend.database.emergencyHospital.EmergencyRepository
import daelim.emergency_backend.database.hospitalInformation.HospitalInformation
import daelim.emergency_backend.database.hospitalInformation.HospitalRepository
import daelim.emergency_backend.exception.DataNotFoundException
import daelim.emergency_backend.exception.EmergencyDataNotFoundException
import daelim.emergency_backend.exception.HospitalNotFoundException
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class EmergencyService(
    val emergencyRepository: EmergencyRepository,
    val hospitalRepository: HospitalRepository,
) {

    fun test(id: String): EmergencyHospitalData {
        return emergencyRepository.findById(id).orElseThrow {
            DataNotFoundException("응급실 데이터가 ID($id)로 존재하지 않습니다.")
        }
    }

    fun testHospital(id: String): HospitalInformation {
        return hospitalRepository.findById(id).orElseThrow {
            HospitalNotFoundException("병원 정보가 ID($id)로 존재하지 않습니다.")
        }
    }

    fun getAllEmergencyHospitalData(page: Int, size: Int): Page<EmergencyHospitalData> {
        val pageable = PageRequest.of(page, size)
        return emergencyRepository.findAll(pageable)
    }

    fun searchWithCity(stage1: String, stage2: String): List<HospitalInformation> {
        val hospitals = hospitalRepository.findByAddress(stage1, stage2)
        if (hospitals.isEmpty()) {
            throw DataNotFoundException("주소 ($stage1, $stage2)에 해당하는 병원이 존재하지 않습니다.")
        }
        return hospitals
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
            hospitalRepository.findByHpid(hpid) ?: throw HospitalNotFoundException("HPID($hpid)에 해당하는 병원 정보가 존재하지 않습니다.")
        } else {
            null
        }

        val emergencyData = if (includeEmergencyData) {
            emergencyRepository.findByHpid(hpid) ?: throw EmergencyDataNotFoundException("HPID($hpid)에 해당하는 응급실 정보가 존재하지 않습니다.")
        } else {
            null
        }

        return mapOf("hospitalInfo" to hospitalInfo, "emergencyInfo" to emergencyData)
    }
}
