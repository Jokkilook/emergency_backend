package daelim.emergency_backend.database

import org.springframework.data.jpa.repository.JpaRepository

interface EmergencyRepository:JpaRepository<EmergencyHospitalData, String> {}