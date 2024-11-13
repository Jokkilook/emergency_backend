package daelim.emergency_backend.infra.repository

import daelim.emergency_backend.infra.entity.HospitalInformation
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface HospitalRepository:JpaRepository<HospitalInformation, String> {

    @Query("SELECT hi FROM HospitalInformation hi WHERE hi.dutyAddr LIKE %:stage1% AND hi.dutyAddr LIKE %:stage2%")
    fun findByAddress(stage1:String, stage2:String): List<HospitalInformation>


    override fun findAll(pageable: Pageable): Page<HospitalInformation>

    @Query("SELECT hi FROM HospitalInformation hi WHERE hi.hpid = :hpid")
    fun findByHpid(hpid: String): HospitalInformation?
}