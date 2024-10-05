package daelim.emergency_backend.database.emergencyHospital

import org.springframework.data.jpa.repository.JpaRepository

interface EmergencyHospitalRepository : JpaRepository<EmergencyHospitalData, Int> {


   override fun findAll(): List<EmergencyHospitalData>
}
