package daelim.emergency_backend.models.hospital

import daelim.emergency_backend.Infra.Entity.HospitalInformation
import jakarta.persistence.Column
import java.sql.Timestamp

data class HospitalInformationDTO(
    val id: Long = 0,
    val resultCode: String = "",
    val resultMag: String? = null,
    val items: String? = null,
    val hpid: String = "",
    val dutyName: String? = null,
    val postCdn1: Int? = null,
    val postCdn2: Int? = null,
    val dutyAddr: String? = null,
    val dutyTel1: String? = null,
    val dutyTel3: String? = null,
    val hvec: Int? = null,
    val hvoc: Int? = null,
    val hvcc: Int? = null,
    val hvncc: Int? = null,
    val hvccc: Int? = null,
    val hvicc: Int? = null,
    val hvgc: Int? = null,
    val dutyHayn: Int? = null,
    val dutyHano: Int? = null,
    val dutyInf: String? = null,
    val dutyMapimg: String? = null,
    val dutyEryn: Byte? = null,
    val dutyTime1c: String? = null,
    val dutyTime2c: String? = null,
    val dutyTime3c: String? = null,
    val dutyTime4c: String? = null,
    val dutyTime5c: String? = null,
    val dutyTime6c: String? = null,
    val dutyTime7c: String? = null,
    val dutyTime8c: String? = null,
    val dutyTime1s: String? = null,
    val dutyTime2s: String? = null,
    val dutyTime3s: String? = null,
    val dutyTime4s: String? = null,
    val dutyTime5s: String? = null,
    val dutyTime6s: String? = null,
    val dutyTime7s: String? = null,
    val dutyTime8s: String? = null,
    val mkioskTy25: String? = null,
    val mkioskTy1: String? = null,
    val mkioskTy2: String? = null,
    val mkioskTy3: String? = null,
    val mkioskTy4: String? = null,
    val mkioskTy5: String? = null,
    val mkioskTy6: String? = null,
    val mkioskTy7: String? = null,
    val mkioskTy8: String? = null,
    val mkioskTy9: String? = null,
    val mkioskTy10: String? = null,
    val mkioskTy11: String? = null,
    val wgs84Lon: Double? = null,
    val wgs84Lat: Double? = null,
    val dgidIdName: String? = null,
    val hpbdn: Int? = null,
    val hpccuyn: Int? = null,
    val hpcuyn: Int? = null,
    val hperyn: Int? = null,
    val hpgryn: Int? = null,
    val hpicuyn: Int? = null,
    val hpnicuyn: Int? = null,
    val hpopyn: Int? = null,
    val lastUpdated: Timestamp? = null,
    val distance: Double? = null,
    var hospitalAvailable: Boolean? = null
) {
    constructor(entity:HospitalInformation, distance: Double? = 0.0) : this(
        id = entity.id,
        resultCode = entity.resultCode?:"",
        resultMag = entity.resultMag,
        items = entity.items,
        hpid = entity.hpid,
        dutyName = entity.dutyName,
        postCdn1 = entity.postCdn1,
        postCdn2 = entity.postCdn2,
        dutyAddr = entity.dutyAddr,
        dutyTel1 = entity.dutyTel1,
        dutyTel3 = entity.dutyTel3,
        hvec = entity.hvec,
        hvoc = entity.hvoc,
        hvcc = entity.hvcc,
        hvncc = entity.hvncc,
        hvccc = entity.hvccc,
        hvicc = entity.hvicc,
        hvgc = entity.hvgc,
        dutyHayn = entity.dutyHayn,
        dutyHano = entity.dutyHano,
        dutyInf = entity.dutyInf,
        dutyMapimg = entity.dutyMapimg,
        dutyEryn = entity.dutyEryn,
        dutyTime1c = entity.dutyTime1c,
        dutyTime2c = entity.dutyTime2c,
        dutyTime3c = entity.dutyTime1c,
        dutyTime4c = entity.dutyTime2c,
        dutyTime5c = entity.dutyTime5c,
        dutyTime6c = entity.dutyTime6c,
        dutyTime7c = entity.dutyTime5c,
        dutyTime8c = entity.dutyTime6c,
        dutyTime1s = entity.dutyTime1c,
        dutyTime2s = entity.dutyTime2c,
        dutyTime3s = entity.dutyTime2c,
        dutyTime4s = entity.dutyTime5c,
        dutyTime5s = entity.dutyTime5c,
        dutyTime6s = entity.dutyTime6c,
        dutyTime7s = entity.dutyTime1c,
        dutyTime8s = entity.dutyTime1c,
        mkioskTy25 = entity.mkioskTy2,
        mkioskTy1 = entity.mkioskTy10,
        mkioskTy2 = entity.mkioskTy2,
        mkioskTy3 = entity.mkioskTy10,
        mkioskTy4 = entity.mkioskTy1,
        mkioskTy5 = entity.mkioskTy1,
        mkioskTy6 = entity.mkioskTy1,
        mkioskTy7 = entity.mkioskTy2,
        mkioskTy8 = entity.mkioskTy2,
        mkioskTy9 = entity.mkioskTy10,
        mkioskTy10 = entity.mkioskTy10,
        mkioskTy11 = entity.mkioskTy1,
        wgs84Lon = entity.wgs84Lon,
        wgs84Lat = entity.wgs84Lat,
        dgidIdName = entity.dgidIdName,
        hpbdn = entity.hpbdn,
        hpccuyn = entity.hpccuyn,
        hpcuyn = entity.hpcuyn,
        hperyn = entity.hperyn,
        hpgryn = entity.hpgryn,
        hpicuyn = entity.hpicuyn,
        hpnicuyn = entity.hpnicuyn,
        hpopyn = entity.hpopyn,
        lastUpdated = entity.lastUpdated,
        distance = distance,
    ){
        hospitalAvailable = if((hvec?:0).toDouble()/(hperyn?:0).toDouble() > 0.7) false else true
        //hvec = 사용가능한 응급실 병상수 & hperyn = 총 응급실 병상 수
    }
}
