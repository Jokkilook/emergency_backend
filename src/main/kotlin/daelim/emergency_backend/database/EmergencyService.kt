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

    //hospital information List 반환 서비스
    fun getHospitalInformationsByPage(page:String?): List<HospitalInformation>{

        if(page == null){                       //page가 null일 때  -- 기본 1부터 20까지 보여줌

            return hospitalRepository.getHospitalInformationsByPageQuery(0,20);
        }else{

            try{

                val integeredPage = page.toInt()

                when(integeredPage){
                    0 -> {
                        throw NumberFormatException("0은 안돼영! >< 1부터 입력하세연")

                    }
                    1 -> { //page가 1일 때  -- 기본 1부터 20까지 보여줌

                        return hospitalRepository.getHospitalInformationsByPageQuery(0,20)

                    }
                    else -> {

                        return hospitalRepository.getHospitalInformationsByPageQuery((integeredPage-1)*20,integeredPage*20)
                    }
                }

            }catch( e:NumberFormatException){
                println("입력값 오류 - ${e.message}")
                return emptyList();                                             //빈 리스트를 반환
            }
        }
    }
}