package daelim.emergency_backend.database.hospitalInformation

import org.springframework.data.jpa.repository.JpaRepository

interface HospitalRepository:JpaRepository<HospitalInformation, String> {}