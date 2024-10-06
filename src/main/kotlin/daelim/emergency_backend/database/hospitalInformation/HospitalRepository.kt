package daelim.emergency_backend.database.hospitalInformation

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface HospitalRepository : JpaRepository<HospitalInformation, Long> {
    @Query("""SELECT hi, eh
              FROM HospitalInformation hi 
              JOIN EmergencyHospitalData eh ON hi.hpid = eh.hpid 
              WHERE hi.hpid = :hpid""", nativeQuery = false)//hi(병원)의 hpid와 eh(응급실)의 hpid로 관계형성하여 조인
    fun findByHpid(hpid: String): List<HospitalInformation>
}