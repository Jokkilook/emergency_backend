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
import daelim.emergency_backend.models.hospital.EmergencyHospitalDTO
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

    fun getAllEmergencyHospitalData(
        page: Int,
        size: Int,
        sortType: SortType,
        filter: List<String>?):
            Page<EmergencyHospitalDTO> {

        val pageable = PageRequest.of(page, size)
        val hospitals = emergencyRepository.findAll(pageable).content

        // DTO로 변환
        val hospitalDTOs = hospitals.map { EmergencyHospitalDTO(it) }

        // 필터 조건이 있을 경우 필터링
        val filteredHospitals = if (!filter.isNullOrEmpty()) {
            hospitalDTOs.filter { hospital ->
                filter.any { hospital.dutyName?.contains(it, ignoreCase = true) == true }
            }
        } else {
            hospitalDTOs
        }

        // 정렬 처리
        val sortedHospitals: List<EmergencyHospitalDTO> = when (sortType) {
            // 병원 이름 오름차순
            SortType.NAMEASC -> filteredHospitals.sortedBy { it.dutyName }
            // 병원 이름 내림차순
            SortType.NAMEDESC -> filteredHospitals.sortedByDescending { it.dutyName }
            // 거리 오름차순 (거리 관련 정보를 제공하지 않음, 예외 처리)
            SortType.DISTANCEASC -> throw InvalidParameterException("This api has no DISTANCEASC option.")
            // 거리 내림차순 (거리 관련 정보를 제공하지 않음, 예외 처리)
            SortType.DISTANCEDESC -> throw InvalidParameterException("This api has no DISTANCEDESC option.")
            // 수술실 가용 오름차순 (수술실 관련 정보 제공하지 않음, 예외 처리)
            SortType.OPERROOMASC -> throw InvalidParameterException("This api has no OPERROOMASC option.")
            // 수술실 가용 내림차순 (수술실 관련 정보 제공하지 않음, 예외 처리)
            SortType.OPERROOMDESC -> throw InvalidParameterException("This api has no OPERROOMDESC option.")
            // 당직의 이름 오름차순 (당직의 정보 제공하지 않음, 예외 처리)
            SortType.DOCNAMEASC -> throw InvalidParameterException("This api has no DOCNAMEASC option.")
            // 당직의 이름 내림차순 (당직의 정보 제공하지 않음, 예외 처리)
            SortType.DOCNAMEDESC -> throw InvalidParameterException("This api has no DOCNAMEDESC option.")
            // 구급차 가용 여부 (구급차 관련 정보 제공하지 않음, 예외 처리)
            SortType.AMBULANCE -> throw InvalidParameterException("This api has no AMBULANCE option.")
            // 정의되지 않은 정렬 항목에 대해 예외 처리
            else -> throw InvalidParameterException("Invalid sort type.")
        }

        // 정렬된 데이터를 페이지로 반환
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
            //수술실 가용 병상 오름차순
            SortType.OPERROOMASC -> throw InvalidParameterException("This api has no OPERROOMASC option.")
            //수술실 가용 병상 내림차순
            SortType.OPERROOMDESC -> throw InvalidParameterException("This api has no OPERROOMDESC option.")
            //당직의 이름 오름차순
            SortType.DOCNAMEASC -> throw InvalidParameterException("This api has no DOCNAMEASC option.")
            //당직의 이름 내림차순
            SortType.DOCNAMEDESC -> throw InvalidParameterException("This api has no DOCNAMEDESC option.")
            //구급차
            SortType.AMBULANCE -> throw InvalidParameterException("This api has no AMBULANCE option.")
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
        sort: SortType = SortType.NAMEASC,
        filter: List<String>? = null
    ): Map<String, Any?> {
        val result = mutableMapOf<String, Any?>()

        // 병원 정보 조회
        if (includeHospitalInfo) {
            val hospitalInfoList = hospitalRepository.findAllBySomeCriteria() // 다중 병원 정보 조회
            val sortedHospitalInfo = when (hospitalSort) {
                SortType.NAMEASC -> hospitalInfoList.sortedBy { it.name }
                SortType.NAMEDESC -> hospitalInfoList.sortedByDescending { it.name }
                SortType.DISTANCEASC -> throw InvalidParameterException("This api has no Distance option.")
                SortType.DISTANCEDESC -> throw InvalidParameterException("This api has no Distance option.")
                SortType.OPERROOMASC -> throw InvalidParameterException("This api has no Operating room availability option.")
                SortType.OPERROOMDESC -> throw InvalidParameterException("This api has no Operating room availability option.")
                SortType.DOCNAMEASC -> throw InvalidParameterException("This api has no Doctor name sorting option.")
                SortType.DOCNAMEDESC -> throw InvalidParameterException("This api has no Doctor name sorting option.")
                SortType.AMBULANCE -> throw InvalidParameterException("This api has no availability sorting option.")
                else -> throw InvalidParameterException("This sort type is not supported.")
            }
            result["hospitalInfo"] = sortedHospitalInfo
        }

        if (includeEmergencyData) {
            val emergencyDataPage = getAllEmergencyHospitalData(0, 20, sort, filter)
            var emergencyDataList = emergencyDataPage.content

            // 필터 조건이 있을 경우 필터링 처리
            if (!filter.isNullOrEmpty()) {
                emergencyDataList = emergencyDataList.filter { hospital ->
                    filter.any { hospital.dutyName?.contains(it, ignoreCase = true) == true }
                }
            }

            // 정렬
            emergencyDataList = when (sort) {
                SortType.NAMEASC -> emergencyDataList.sortedBy { it.dutyName }
                SortType.NAMEDESC -> emergencyDataList.sortedByDescending { it.dutyName }
                SortType.DISTANCEASC -> throw InvalidParameterException("This api has no Distance option.")
                SortType.DISTANCEDESC -> throw InvalidParameterException("This api has no Distance option.")
                SortType.OPERROOMASC -> throw InvalidParameterException("This api has no Operating room availability option.")
                SortType.OPERROOMDESC -> throw InvalidParameterException("This api has no Operating room availability option.")
                SortType.DOCNAMEASC -> throw InvalidParameterException("This api has no Doctor name sorting option.")
                SortType.DOCNAMEDESC -> throw InvalidParameterException("This api has no Doctor name sorting option.")
                SortType.AMBULANCE -> throw InvalidParameterException("This api has no availability sorting option.")
                else -> throw InvalidParameterException("This sort type is not supported.")
            }

            result["emergencyInfo"] = emergencyDataList
        }
        return result
    }
    return result
    }
}
