package daelim.emergency_backend.Infra.Repository

import daelim.emergency_backend.Infra.Entity.EmergencyHospitalData
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface EmergencyRepository:JpaRepository<EmergencyHospitalData, String> {
    override fun findAll(pageable: Pageable): Page<EmergencyHospitalData>

    @Query("SELECT eh FROM EmergencyHospitalData eh WHERE eh.hpid = :hpid")
    fun findByHpid(hpid: String): EmergencyHospitalData?
}