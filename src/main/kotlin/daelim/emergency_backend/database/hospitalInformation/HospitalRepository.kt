package daelim.emergency_backend.database.hospitalInformation

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface HospitalRepository:JpaRepository<HospitalInformation, String> {

    @Query("SELECT hi FROM HospitalInformation hi WHERE hi.dutyAddr LIKE %:stage1% AND hi.dutyAddr LIKE %:stage2%")
    fun findByAddress(stage1:String, stage2:String): List<HospitalInformation>

}