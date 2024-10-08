package daelim.emergency_backend.database

import daelim.emergency_backend.database.emergencyHospital.EmergencyHospitalData
import daelim.emergency_backend.database.emergencyHospital.EmergencyRepository
import daelim.emergency_backend.database.hospitalInformation.HospitalInformation
import daelim.emergency_backend.database.hospitalInformation.HospitalRepository
import jakarta.persistence.EntityManager
import jakarta.persistence.NoResultException
import jakarta.persistence.PersistenceContext
import jakarta.persistence.TypedQuery
import org.springframework.stereotype.Service

@Service
class EmergencyService(
    val emergencyRepository: EmergencyRepository,
    val hospitalRepository: HospitalRepository
) {

    @PersistenceContext
    private lateinit var entityManager: EntityManager

    fun test(id: String): EmergencyHospitalData? {
        return emergencyRepository.findById(id).orElse(null)
    }

    fun testHospital(id: String): HospitalInformation? {
        return hospitalRepository.findById(id).orElse(null)
    }

    fun findHospitalInformationByHpid(hpid: String): HospitalInformation? {
        val query = entityManager.createQuery(
            "SELECT hi FROM HospitalInformation hi WHERE hi.hpid = :hpid",
            HospitalInformation::class.java
        )
        query.setParameter("hpid", hpid)
        return query.singleResultOrNull()
    }

    fun findEmergencyHospitalDataByHpid(hpid: String): EmergencyHospitalData? {
        val query = entityManager.createQuery(
            "SELECT eh FROM EmergencyHospitalData eh WHERE eh.hpid = :hpid",
            EmergencyHospitalData::class.java
        )
        query.setParameter("hpid", hpid)
        return query.singleResultOrNull()
    }

    fun findByHpid(hpid: String): Pair<HospitalInformation?, EmergencyHospitalData?> {
        val hospitalInfo = findHospitalInformationByHpid(hpid)
        val emergencyData = findEmergencyHospitalDataByHpid(hpid)

        return Pair(hospitalInfo, emergencyData)
    }

    fun <T> TypedQuery<T>.singleResultOrNull(): T? {
        return try {
            this.singleResult
        } catch (e: NoResultException) {
            null
        }
    }
}