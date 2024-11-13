package daelim.emergency_backend.model.datagokr

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonRootName
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.MapperFeature
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule


//응급의료기관 기본정보 조회


@JsonRootName("response")
data class EmergencyMedicalInstitutionBasicInfoResult(
    @set:JsonProperty("header")
    var header: Header?,

    @set:JsonProperty("body")
    var body: EmergencyMedicalInstitutionBasicInfoBody?,
)

@JsonRootName("body")
data class EmergencyMedicalInstitutionBasicInfoBody(
    @set:JsonProperty("items")
    var items: EmergencyMedicalInstitutionBasicInfoItems?,

    @set:JsonProperty("numOfRows")//한 페이지 결과수
    var numOfRows:Int?,

    @set:JsonProperty("pageNo")//페이지번호
    var pageNo:Int?,

    @set:JsonProperty("totalCount")//총 결과수
    var totalCount:Int?,
)

@JsonRootName("items")
data class EmergencyMedicalInstitutionBasicInfoItems(
    @set:JsonProperty("item")
    var item:List<EmergencyMedicalInstitutionBasicInfo>?,
)


@JsonRootName("item")
data class EmergencyMedicalInstitutionBasicInfo(
    @set:JsonProperty("hpid")
    var hpid: String? = null, // 기관 ID

    @set:JsonProperty("dutyName")
    var dutyName: String? = null, // 기관명

    @set:JsonProperty("postCdn1")
    var postCdn1: String? = null, // 우편번호1

    @set:JsonProperty("postCdn2")
    var postCdn2: String? = null, // 우편번호2

    @set:JsonProperty("dutyAddr")
    var dutyAddr: String? = null, // 주소

    @set:JsonProperty("dutyTel1")
    var dutyTel1: String? = null, // 대표전화1

    @set:JsonProperty("dutyTel3")
    var dutyTel3: String? = null, // 응급실 전화

    @set:JsonProperty("hvec")
    var hvec: Int? = null, // 응급실

    @set:JsonProperty("hvoc")
    var hvoc: Int? = null, // 수술실

    @set:JsonProperty("hvcc")
    var hvcc: Int? = null, // 신경 중환자

    @set:JsonProperty("hvncc")
    var hvncc: Int? = null, // 신생 중환자

    @set:JsonProperty("hvccc")
    var hvccc: Int? = null, // 흉부 중환자

    @set:JsonProperty("hvicc")
    var hvicc: Int? = null, // 일반 중환자

    @set:JsonProperty("hvgc")
    var hvgc: Int? = null, // 입원실

    @set:JsonProperty("dutyHayn")
    var dutyHayn: Int? = null, // 입원실 가용여부(1/2)

    @set:JsonProperty("dutyHano")
    var dutyHano: Int? = null, // 병상수

    @set:JsonProperty("dutyInf")
    var dutyInf: String? = null, // 기관설명상세

    @set:JsonProperty("dutyMapimg")
    var dutyMapimg: String? = null, // 간이약도

    @set:JsonProperty("dutyEryn")
    var dutyEryn: Int? = null, // 응급실 가용여부(1/2)

    @set:JsonProperty("dutyTime1c")
    var dutyTime1c: String? = null, // 진료시간 (월요일)C

    @set:JsonProperty("dutyTime2c")
    var dutyTime2c: String? = null, // 진료시간 (화요일)C

    @set:JsonProperty("dutyTime3c")
    var dutyTime3c: String? = null, // 진료시간 (수요일)C

    @set:JsonProperty("dutyTime4c")
    var dutyTime4c: String? = null, // 진료시간 (목요일)C

    @set:JsonProperty("dutyTime5c")
    var dutyTime5c: String? = null, // 진료시간 (금요일)C

    @set:JsonProperty("dutyTime6c")
    var dutyTime6c: String? = null, // 진료시간 (토요일)C

    @set:JsonProperty("dutyTime7c")
    var dutyTime7c: String? = null, // 진료시간 (일요일)C

    @set:JsonProperty("dutyTime8c")
    var dutyTime8c: String? = null, // 진료시간 (공휴일)C

    @set:JsonProperty("dutyTime1s")
    var dutyTime1s: String? = null, // 진료시간 (월요일)S

    @set:JsonProperty("dutyTime2s")
    var dutyTime2s: String? = null, // 진료시간 (화요일)S

    @set:JsonProperty("dutyTime3s")
    var dutyTime3s: String? = null, // 진료시간 (수요일)S

    @set:JsonProperty("dutyTime4s")
    var dutyTime4s: String? = null, // 진료시간 (목요일)S

    @set:JsonProperty("dutyTime5s")
    var dutyTime5s: String? = null, // 진료시간 (금요일)S

    @set:JsonProperty("dutyTime6s")
    var dutyTime6s: String? = null, // 진료시간 (토요일)S

    @set:JsonProperty("dutyTime7s")
    var dutyTime7s: String? = null, // 진료시간 (일요일)S

    @set:JsonProperty("dutyTime8s")
    var dutyTime8s: String? = null, // 진료시간 (공휴일)S

    @set:JsonProperty("MKioskTy25")
    var MKioskTy25: String? = null, // 응급실 (Emergency gate keeper)

    @set:JsonProperty("MKioskTy1")
    var MKioskTy1: String? = null, // 뇌출혈수술

    @set:JsonProperty("MKioskTy2")
    var MKioskTy2: String? = null, // 뇌경색의재관류

    @set:JsonProperty("MKioskTy3")
    var MKioskTy3: String? = null, // 심근경색의재관류

    @set:JsonProperty("MKioskTy4")
    var MKioskTy4: String? = null, // 복부손상의 수술

    @set:JsonProperty("MKioskTy5")
    var MKioskTy5: String? = null, // 사지접합의수술

    @set:JsonProperty("MKioskTy6")
    var MKioskTy6: String? = null, // 응급내시경

    @set:JsonProperty("MKioskTy7")
    var MKioskTy7: String? = null, // 응급투석

    @set:JsonProperty("MKioskTy8")
    var MKioskTy8: String? = null, // 조산산모

    @set:JsonProperty("MKioskTy9")
    var MKioskTy9: String? = null, // 정신질환자

    @set:JsonProperty("MKioskTy10")
    var MKioskTy10: String? = null, // 신생아

    @set:JsonProperty("MKioskTy11")
    var MKioskTy11: String? = null, // 중증화상

    @set:JsonProperty("wgs84Lon")
    var wgs84Lon: Double? = null, // 병원경도

    @set:JsonProperty("wgs84Lat")
    var wgs84Lat: Double? = null, // 병원위도

    @set:JsonProperty("dgidIdName")
    var dgidIdName: String? = null, // 진료과목

    @set:JsonProperty("hpbdn")
    var hpbdn: Int? = null, // 병상수

    @set:JsonProperty("hpccuyn")
    var hpccuyn: Int? = null, // 흉부중환자실

    @set:JsonProperty("hpcuyn")
    var hpcuyn: Int? = null, // 신경중환자실

    @set:JsonProperty("hperyn")
    var hperyn: Int? = null, // 응급실

    @set:JsonProperty("hpgryn")
    var hpgryn: Int? = null, // 입원실

    @set:JsonProperty("hpicuyn")
    var hpicuyn: Int? = null, // 일반중환자실

    @set:JsonProperty("hpnccuyn")
    var hpnccuyn: Int? = null, // 신생아중환자실

    @set:JsonProperty("hpopyn")
    var hpopyn: Int? = null // 수술실
)

fun convertXmlToEmergencyMedicalInstitutionBasicInfoResult(xmlString: String): EmergencyMedicalInstitutionBasicInfoResult {
    val xmlMapper = XmlMapper(JacksonXmlModule().apply {
        setDefaultUseWrapper(false)
    }).registerKotlinModule()
        .configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true)
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

    return xmlMapper.readValue(xmlString, EmergencyMedicalInstitutionBasicInfoResult::class.java)
}


//API의 쿼리 클래스 만들기
class EmergencyMedicalInstitutionBasicInfoQuery(
    var HPID:String?,
    var pageNo:Int?,
    var numOfRows:Int?
)
