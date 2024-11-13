package daelim.emergency_backend.Infra.Entity

import jakarta.persistence.*
import java.sql.Timestamp

@Entity
@Table(name = "hospital_information")
data class HospitalInformation(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(name = "result_code", length = 50)
    val resultCode: String = "",

    @Column(name = "result_mag", length = 10)
    val resultMag: String? = null,

    @Column(name = "items", length = 10)
    val items: String? = null,

    @Column(name = "hpid", length = 10, nullable = false)
    val hpid: String = "",

    @Column(name = "duty_name", length = 100)
    val dutyName: String? = null,

    @Column(name = "post_cdn1")
    val postCdn1: Int? = null,

    @Column(name = "post_cdn2")
    val postCdn2: Int? = null,

    @Column(name = "duty_addr", length = 200)
    val dutyAddr: String? = null,

    @Column(name = "duty_tel1", length = 14)
    val dutyTel1: String? = null,

    @Column(name = "duty_tel3", length = 14)
    val dutyTel3: String? = null,

    @Column(name = "hvec")
    val hvec: Int? = null,

    @Column(name = "hvoc")
    val hvoc: Int? = null,

    @Column(name = "hvcc")
    val hvcc: Int? = null,

    @Column(name = "hvncc")
    val hvncc: Int? = null,

    @Column(name = "hvccc")
    val hvccc: Int? = null,

    @Column(name = "hvicc")
    val hvicc: Int? = null,

    @Column(name = "hvgc")
    val hvgc: Int? = null,

    @Column(name = "duty_hayn")
    val dutyHayn: Int? = null,

    @Column(name = "duty_hano")
    val dutyHano: Int? = null,

    @Column(name = "duty_inf", length = 300)
    val dutyInf: String? = null,

    @Column(name = "duty_mapimg", length = 100)
    val dutyMapimg: String? = null,

    @Column(name = "duty_eryn")
    val dutyEryn: Byte? = null,

    @Column(name = "duty_time1c", length = 4)
    val dutyTime1c: String? = null,

    @Column(name = "duty_time2c", length = 4)
    val dutyTime2c: String? = null,

    @Column(name = "duty_time3c", length = 4)
    val dutyTime3c: String? = null,

    @Column(name = "duty_time4c", length = 4)
    val dutyTime4c: String? = null,

    @Column(name = "duty_time5c", length = 4)
    val dutyTime5c: String? = null,

    @Column(name = "duty_time6c", length = 4)
    val dutyTime6c: String? = null,

    @Column(name = "duty_time7c", length = 4)
    val dutyTime7c: String? = null,

    @Column(name = "duty_time8c", length = 4)
    val dutyTime8c: String? = null,

    @Column(name = "duty_time1s", length = 4)
    val dutyTime1s: String? = null,

    @Column(name = "duty_time2s", length = 4)
    val dutyTime2s: String? = null,

    @Column(name = "duty_time3s", length = 4)
    val dutyTime3s: String? = null,

    @Column(name = "duty_time4s", length = 4)
    val dutyTime4s: String? = null,

    @Column(name = "duty_time5s", length = 4)
    val dutyTime5s: String? = null,

    @Column(name = "duty_time6s", length = 4)
    val dutyTime6s: String? = null,

    @Column(name = "duty_time7s", length = 4)
    val dutyTime7s: String? = null,

    @Column(name = "duty_time8s", length = 4)
    val dutyTime8s: String? = null,

    @Column(name = "mkiosk_ty25", length = 4)
    val mkioskTy25: String? = null,

    @Column(name = "mkiosk_ty1", length = 4)
    val mkioskTy1: String? = null,

    @Column(name = "mkiosk_ty2", length = 4)
    val mkioskTy2: String? = null,

    @Column(name = "mkiosk_ty3", length = 4)
    val mkioskTy3: String? = null,

    @Column(name = "mkiosk_ty4", length = 4)
    val mkioskTy4: String? = null,

    @Column(name = "mkiosk_ty5", length = 4)
    val mkioskTy5: String? = null,

    @Column(name = "mkiosk_ty6", length = 4)
    val mkioskTy6: String? = null,

    @Column(name = "mkiosk_ty7", length = 4)
    val mkioskTy7: String? = null,

    @Column(name = "mkiosk_ty8", length = 4)
    val mkioskTy8: String? = null,

    @Column(name = "mkiosk_ty9", length = 4)
    val mkioskTy9: String? = null,

    @Column(name = "mkiosk_ty10", length = 4)
    val mkioskTy10: String? = null,

    @Column(name = "mkiosk_ty11", length = 4)
    val mkioskTy11: String? = null,

    @Column(name = "wgs84_lon")
    val wgs84Lon: Double? = null,

    @Column(name = "wgs84_lat")
    val wgs84Lat: Double? = null,

    @Column(name = "dgid_id_name", length = 200)
    val dgidIdName: String? = null,

    @Column(name = "hpbdn")
    val hpbdn: Int? = null,

    @Column(name = "hpccuyn")
    val hpccuyn: Int? = null,

    @Column(name = "hpcuyn")
    val hpcuyn: Int? = null,

    @Column(name = "hperyn")
    val hperyn: Int? = null,

    @Column(name = "hpgryn")
    val hpgryn: Int? = null,

    @Column(name = "hpicuyn")
    val hpicuyn: Int? = null,

    @Column(name = "hpnicuyn")
    val hpnicuyn: Int? = null,

    @Column(name = "hpopyn")
    val hpopyn: Int? = null,

    @Column(name = "last_updated")
    val lastUpdated: Timestamp? = null
)


@Entity
@Table(name = "hospital_information_with_distance")
data class HospitalInformationWithDistance(
    @Id
    @Column(name = "id")
    private val id: Long = 0,

    @ManyToOne
    @JoinColumn(name = "hospital")
    val hospital: HospitalInformation,


    @Column(name = "distance")
    val distance: Double? = null
)