package daelim.emergency_backend.database.hospitalInformation

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface HospitalRepository:JpaRepository<HospitalInformation, String> {

    @Query("SELECT hi FROM HospitalInformation hi WHERE hi.dutyAddr LIKE %:stage1% AND hi.dutyAddr LIKE %:stage2%")
    fun findByAddress(stage1:String, stage2:String): List<HospitalInformation>


    //hospital information List query
    @Query("SELECT hi FROM HospitalInformation hi WHERE hi.id>:pageRange1 AND hi.id<= :pageRange2")
    fun getHospitalInformationsByPageQuery(pageRange1:Int, pageRange2:Int): List<HospitalInformation>
}