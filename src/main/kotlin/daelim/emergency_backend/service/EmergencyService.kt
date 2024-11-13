package daelim.emergency_backend.Service

import daelim.emergency_backend.Infra.Entity.EmergencyHospitalData
import daelim.emergency_backend.Infra.Entity.HospitalInformation
import daelim.emergency_backend.Infra.Entity.HospitalInformationWithDistance
import daelim.emergency_backend.Infra.Repository.EmergencyRepository
import daelim.emergency_backend.Infra.Repository.HospitalRepository
import daelim.emergency_backend.exception.DataNotFoundException
import daelim.emergency_backend.exception.EmergencyDataNotFoundException
import daelim.emergency_backend.exception.HospitalNotFoundException
import daelim.emergency_backend.exception.InvalidParameterException
import daelim.emergency_backend.lib.SortType
import daelim.emergency_backend.models.hospital.HospitalInformationDTO
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import daelim.emergency_backend.utils.EmergencyUtils.Companion.getDistanceWithLonLat

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

    fun getAllEmergencyHospitalData(page: Int, size: Int, sortType: Int): Page<EmergencyHospitalData> {
        val pageable = PageRequest.of(page, size)
        val hospitals = emergencyRepository.findAll(pageable).content

        // 정렬 처리
        val sortedHospitals: List<EmergencyHospitalData> = when (sortType) {
            // 병원 이름 순
            0 -> hospitals.sortedBy { it.dutyName }
            // 거리 순 (거리 관련 정보를 제거한 후 이름 순 정렬)
            1 -> hospitals.sortedBy { it.dutyName }
            // 수술실 가용 병상 순 (기능 추가 필요)
            2 -> throw InvalidParameterException("Sort type 2 is not implemented.")
            // 당직의 (기능 추가 필요)
            3 -> throw InvalidParameterException("Sort type 3 is not implemented.")
            // 구급차 (기능 추가 필요)
            4 -> throw InvalidParameterException("Sort type 4 is not implemented.")
            else -> throw InvalidParameterException("Invalid sort type.")
        }
        // 정렬된 병원 데이터를 페이지로 반환
        return PageImpl(sortedHospitals, pageable, sortedHospitals.size.toLong())
    }

    fun searchWithCity(
        stage1: String,
        stage2: String,
        sortType:SortType = SortType.NAMEASC,
        filter:List<String>?,
        originLat:Double?,
        originLon:Double?
    ): List<HospitalInformationDTO> {

        val hospitals = hospitalRepository.findByAddress(stage1, stage2)

        if (hospitals.isEmpty()) {
            throw DataNotFoundException("주소 ($stage1, $stage2)에 해당하는 병원이 존재하지 않습니다.")
        }

        val hospitalDistances: MutableList<HospitalInformationDTO> = mutableListOf()

        if(originLat!=null && originLon!=null){
            hospitals.forEach{ hospital ->
                val distance = getDistanceWithLonLat(originLat, originLon, hospital.wgs84Lat?:0.0, hospital.wgs84Lon?:0.0)
                hospitalDistances.add(HospitalInformationDTO(hospital, distance))
            }
        }else if(originLat ==null &&originLon ==null){
            hospitals.forEach { hospital ->
                hospitalDistances.add(HospitalInformationDTO(hospital, -1.0))
            }
        }else{
            throw InvalidParameterException()
        }

        var filteredHospitals = mutableListOf<HospitalInformationDTO>()

        if(!filter.isNullOrEmpty()){
            hospitalDistances.forEach { hospital ->
                if(filter.any{ hospital.dgidIdName?.contains(it) == true }){
                    filteredHospitals.add(hospital)
                }
            }
        } else {
            filteredHospitals = hospitalDistances
        }

        val sortedHospitals:List<HospitalInformationDTO> = when (sortType) {
            //병원 이름 오름차순
            SortType.NAMEASC -> filteredHospitals.sortedBy { it.dutyName }
            //병원 이름 내림차순
            SortType.NAMEDESC -> filteredHospitals.sortedByDescending { it.dutyName }
            //거리순 오름차순
            SortType.DISTANCEASC -> filteredHospitals.sortedWith(
                compareBy<HospitalInformationDTO> { it.distance } // 첫 번째 기준: 거리 오름차순
                    .thenBy { it.dutyName }                       // 두 번째 기준: 이름 오름차순
            )
            //거리순 내림차순
            SortType.DISTANCEDESC -> filteredHospitals.sortedWith(
                compareByDescending<HospitalInformationDTO> { it.distance } // 첫 번째 기준: 거리 내림차순
                    .thenBy { it.dutyName }                                 // 두 번째 기준: 이름 오름차순
            )
            //수술실 가용 병상 순
            //당직의
            //구급차
            //이외
            else -> throw InvalidParameterException("There is no such sort type.")
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
        includeEmergencyData: Boolean = true,
        sort: Int = 0,
        filter: List<String>? = null
    ): Map<String, Any?> {
        val result = mutableMapOf<String, Any?>()

        // 병원 정보 조회
        if (includeHospitalInfo) {
            val hospitalInfo = hospitalRepository.findByHpid(hpid)
            result["hospitalInfo"] = hospitalInfo
        }

        // 응급실 정보 조회
        if (includeEmergencyData) {
            val emergencyDataPage = getAllEmergencyHospitalData(0, 20, sort)
            var emergencyDataList = emergencyDataPage.content

            if (!filter.isNullOrEmpty()) {
                emergencyDataList = emergencyDataList.filter { hospital ->
                    filter.any { hospital.dutyName?.contains(it) == true }
                }
            }
            result["emergencyInfo"] = emergencyDataList
        }

        return result
    }


}
