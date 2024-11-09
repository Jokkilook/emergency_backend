package daelim.emergency_backend.database

import daelim.emergency_backend.database.emergencyHospital.EmergencyHospitalData
import daelim.emergency_backend.database.emergencyHospital.EmergencyRepository
import daelim.emergency_backend.database.hospitalInformation.HospitalInformationWithDistance
import daelim.emergency_backend.database.hospitalInformation.HospitalInformation
import daelim.emergency_backend.database.hospitalInformation.HospitalRepository
import daelim.emergency_backend.exception.DataNotFoundException
import daelim.emergency_backend.exception.EmergencyDataNotFoundException
import daelim.emergency_backend.exception.HospitalNotFoundException
import daelim.emergency_backend.exception.InvalidParameterException
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import utils.EmergencyUtils.Companion.getDistanceWithLonLat

@Service
class EmergencyService(
    val emergencyRepository: EmergencyRepository,
    val hospitalRepository: HospitalRepository,
) {
    val logger = LoggerFactory.getLogger(EmergencyService::class.java)

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

    fun searchWithCity(
        stage1: String,
        stage2: String,
        sortType:Int = 0,
        filter:List<String>?,
        originLat:Double?,
        originLon:Double?
    ): List<HospitalInformationWithDistance> {

        val hospitals = hospitalRepository.findByAddress(stage1, stage2)

        if (hospitals.isEmpty()) {
            throw DataNotFoundException("주소 ($stage1, $stage2)에 해당하는 병원이 존재하지 않습니다.")
        }

        val hospitalDistances: MutableList<HospitalInformationWithDistance> = mutableListOf()

        if(originLat!=null && originLon!=null){
            hospitals.forEach{ hospital ->
                val distance = getDistanceWithLonLat(originLat, originLon, hospital.wgs84Lat!!, hospital.wgs84Lon!!)
                hospitalDistances.add(HospitalInformationWithDistance(hospital.id, hospital, distance))
            }
        }else if(originLat ==null &&originLon ==null){
            hospitals.forEach { hospital ->
                hospitalDistances.add(HospitalInformationWithDistance(hospital.id, hospital, -1.0))
            }
        }else{
            throw InvalidParameterException()
        }

        var filteredHospitals = mutableListOf<HospitalInformationWithDistance>()

        if(!filter.isNullOrEmpty()){
            hospitalDistances.forEach { hospital ->
                if(filter.any{ hospital.hospital.dgidIdName?.contains(it) == true }){
                    filteredHospitals.add(hospital)
                }
            }
        } else {
            filteredHospitals = hospitalDistances
        }

        val sortedHospitals:List<HospitalInformationWithDistance> = when (sortType) {
            //병원 이름 순
            0 -> filteredHospitals.sortedBy { it.hospital.dutyName }
            //거리순
            1 -> filteredHospitals.sortedWith(
                compareBy<HospitalInformationWithDistance> { it.distance } // 첫 번째 기준: 거리 오름차순
                    .thenBy { it.hospital.dutyName }                       // 두 번째 기준: 이름 오름차순
                )
            //수술실 가용 병상 순
            2 -> throw InvalidParameterException()
            //당직의
            3 -> throw InvalidParameterException()
            //구급차
            4 -> throw InvalidParameterException()
            //이외
            else -> throw InvalidParameterException()
        }

        return sortedHospitals
    }

    fun getHospitalInformationsByPage(
        page: Int,
        size: Int,
        sortType: Int,
        originLat: Double?,
        originLon: Double?
    ): Page<HospitalInformationWithDistance> {

        val hospitals = hospitalRepository.findAll()
        val hospitalDistances: MutableList<HospitalInformationWithDistance> = mutableListOf()
        if(originLat !=null &&originLon !=null){

             hospitals.forEach { hospital ->
                val distance = getDistanceWithLonLat(originLat, originLon, hospital.wgs84Lat!! , hospital.wgs84Lon!!)
                 hospitalDistances.add(HospitalInformationWithDistance(hospital.id,hospital, distance))
            }
        }else if(originLat ==null &&originLon ==null){

            hospitals.forEach { hospital ->
                hospitalDistances.add(HospitalInformationWithDistance(hospital.id,hospital, -1.0))
            }
        }else{
            throw InvalidParameterException()
        }

        val sortedHospitals = when (sortType) {
            0 -> hospitalDistances.sortedBy { it.hospital.dutyName } // 이름순
            1 -> hospitalDistances.sortedBy { it.distance } // 거리순
//            2 -> hospitalDistances.sortedByDescending { it.hospital.hvoc } // 수술실 가용 병상/hvoc hpopyn 두개 있음 hv 응급 수술실일 가능성 hp일반 수술실일 가능성
//            3 -> hospitalDistances.sortedBy { it.hospital.dutyEryn } // 당직의 이름 db 수정 필요 or api 신규 생성필
//            4 -> hospitalDistances.sortedByDescending { it.hospital.hvamyn } // 구급차 유무 없
            else -> throw InvalidParameterException()
        }

        val pageable = PageRequest.of(page, size)
        val start = page * size
        val end = Math.min(start + size, sortedHospitals.size)
        val pagedHospitals = sortedHospitals.subList(start, end)

        return PageImpl(pagedHospitals, pageable, sortedHospitals.size.toLong())
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
