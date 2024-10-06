package daelim.emergency_backend.database.emergencyHospital

import jakarta.persistence.*
import java.sql.Timestamp
import java.util.Date

@Entity
@Table(name = "emergency_hospital_data")
data class EmergencyHospitalData(
    @Id
    @GeneratedValue
    val id: Long = 0,

    @Column(name = "result_code")
    val resultCode: String? = null,

    @Column(name = "result_msg")
    val resultMsg: String? = null,

    @Column(name = "rnum")
    val rnum: String? = null,

    @Column(name = "hpid")
    val hpid: String? = null,

    @Column(name = "phpid")
    val phpid: String? = null,

    @Column(name = "hvidate")
    val hvidate: Date? = null,

    @Column(name = "hvec")
    val hvec: String? = null,

    @Column(name = "hvoc")
    val hvoc: String? = null,

    @Column(name = "hvcc")
    val hvcc: String? = null,

    @Column(name = "hvncc")
    val hvncc: String? = null,

    @Column(name = "hvccc")
    val hvccc: String? = null,

    @Column(name = "hvicc")
    val hvicc: String? = null,

    @Column(name = "hvgc")
    val hvgc: String? = null,

    @Column(name = "hvdnm")
    val hvdnm: String? = null,

    @Column(name = "hvctayn")
    val hvctayn: String? = null,

    @Column(name = "hvmriayn")
    val hvmriayn: String? = null,

    @Column(name = "hvangioayn")
    val hvangioayn: String? = null,

    @Column(name = "hvventiayn")
    val hvventiayn: String? = null,

    @Column(name = "hvventisoayn")
    val hvventisoayn: String? = null,

    @Column(name = "hvincuayn")
    val hvincuayn: String? = null,

    @Column(name = "hvcrrtayn")
    val hvcrrtayn: String? = null,

    @Column(name = "hvecmoayn")
    val hvecmoayn: String? = null,

    @Column(name = "hvoxyayn")
    val hvoxyayn: String? = null,

    @Column(name = "hvhypoayn")
    val hvhypoayn: String? = null,

    @Column(name = "hvamyn")
    val hvamyn: String? = null,

    @Column(name = "hv1")
    val hv1: String? = null,

    @Column(name = "hv2")
    val hv2: String? = null,

    @Column(name = "hv3")
    val hv3: String? = null,

    @Column(name = "hv4")
    val hv4: String? = null,

    @Column(name = "hv5")
    val hv5: String? = null,

    @Column(name = "hv6")
    val hv6: String? = null,

    @Column(name = "hv7")
    val hv7: String? = null,

    @Column(name = "hv8")
    val hv8: String? = null,

    @Column(name = "hv9")
    val hv9: String? = null,

    @Column(name = "hv10")
    val hv10: String? = null,

    @Column(name = "hv11")
    val hv11: String? = null,

    @Column(name = "hv12")
    val hv12: String? = null,

    @Column(name = "hv13")
    val hv13: String? = null,

    @Column(name = "hv14")
    val hv14: String? = null,

    @Column(name = "hv15")
    val hv15: String? = null,

    @Column(name = "hv16")
    val hv16: String? = null,

    @Column(name = "hv17")
    val hv17: String? = null,

    @Column(name = "hv18")
    val hv18: String? = null,

    @Column(name = "hv19")
    val hv19: String? = null,

    @Column(name = "hv21")
    val hv21: String? = null,

    @Column(name = "hv22")
    val hv22: String? = null,

    @Column(name = "hv23")
    val hv23: String? = null,

    @Column(name = "hv24")
    val hv24: String? = null,

    @Column(name = "hv25")
    val hv25: String? = null,

    @Column(name = "hv26")
    val hv26: String? = null,

    @Column(name = "hv27")
    val hv27: String? = null,

    @Column(name = "hv28")
    val hv28: String? = null,

    @Column(name = "hv29")
    val hv29: String? = null,

    @Column(name = "hv30")
    val hv30: String? = null,

    @Column(name = "hv31")
    val hv31: String? = null,

    @Column(name = "hv32")
    val hv32: String? = null,

    @Column(name = "hv33")
    val hv33: String? = null,

    @Column(name = "hv34")
    val hv34: String? = null,

    @Column(name = "hv35")
    val hv35: String? = null,

    @Column(name = "hv36")
    val hv36: String? = null,

    @Column(name = "hv37")
    val hv37: String? = null,

    @Column(name = "hv38")
    val hv38: String? = null,

    @Column(name = "hv39")
    val hv39: String? = null,

    @Column(name = "hv40")
    val hv40: String? = null,

    @Column(name = "hv41")
    val hv41: String? = null,

    @Column(name = "hv42")
    val hv42: String? = null,

    @Column(name = "hv43")
    val hv43: String? = null,

    @Column(name = "duty_name")
    val dutyName: String? = null,

    @Column(name = "duty_tel3")
    val dutyTel3: String? = null,

    @Column(name = "last_updated")
    val lastUpdated: Timestamp? = null,
)