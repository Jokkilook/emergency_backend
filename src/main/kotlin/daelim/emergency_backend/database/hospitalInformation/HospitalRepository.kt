package daelim.emergency_backend.database.hospitalInformation

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface HospitalRepository : JpaRepository<HospitalInformation, Long> {}