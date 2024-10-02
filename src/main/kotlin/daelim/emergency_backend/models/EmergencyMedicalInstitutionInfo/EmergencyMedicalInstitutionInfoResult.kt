package daelim.emergency_backend.models.EmergencyMedicalInstitutionInfo

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonRootName
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.MapperFeature
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import daelim.emergency_backend.models.AvailavleBedInfo.Header

//응급의료기관 목록정보 조회
@JsonRootName("response")
data class EmergencyMedicalInstitutionInfoResult(
    @JsonProperty("header")
    var haeder:Header?,
    @JsonProperty("body")
    var body:EmergencyMedicalInstitutionInfoBody?
)

@JsonRootName("body")
data class EmergencyMedicalInstitutionInfoBody(
    @JacksonXmlElementWrapper(useWrapping = false, localName = "items")
    @set:JsonProperty("items")
    var items: EmergencyMedicalInstitutionInfoItems?,
    @set:JsonProperty("numOfRows")
    var numOfRows:String?,
    @set:JsonProperty("pageNo")
    var pageNo:String?,
    @set:JsonProperty("totalCount")
    var totalCount:String?
)

@JsonRootName("items")
data class EmergencyMedicalInstitutionInfoItems(
    @JsonProperty("item")
    var items:List<EmergencyMedicalInstitutionInfo>?
)

@JsonRootName("item")
data class EmergencyMedicalInstitutionInfo(
    @set:JsonProperty("rnum") var rnum:String?, //아이템 번호
    @set:JsonProperty("hpid") var hpid:String?, //기관ID
    @set:JsonProperty("phpid") var phpid:String?, //구 기관ID(old)
    @set:JsonProperty("dutyEmcls") var dutyEmcls:String?, //응급의료기관분류코드
    @set:JsonProperty("dutyEmclsName") var dutyEmclsName:String?, //응급의료기관분류명
    @set:JsonProperty("dutyAddr") var dutyAddr:String?, //주소
    @set:JsonProperty("dutyName") var dutyName:String?, //기관명
    @set:JsonProperty("dutyTel1") var dutyTel1:String?, //대표전화1
    @set:JsonProperty("dutyTel3") var dutyTel3:String?, //응급실전화
    @set:JsonProperty("wgs84Lon") var wgs84Lon:String?, //병원경도
    @set:JsonProperty("wgs84Lat") var wgs84Lat:String? //병원 위도
)

fun convertXmlToEmergencyMedicalInstitutionInfoResult(xmlString: String): EmergencyMedicalInstitutionInfoResult {
    val xmlMapper = XmlMapper(JacksonXmlModule().apply {
        setDefaultUseWrapper(false)
    }).registerKotlinModule()
        .configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true)
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

    return xmlMapper.readValue(xmlString, EmergencyMedicalInstitutionInfoResult::class.java)
}

class EmergencyMedicalInstitutionInfoQuery(
    var Q0 : String, //시도
    var Q1 : String, //시군구
    var QT : String, // 진료요일
    var Qz : Int?, // 기관분류
    var QD : Int?, // 진료과목
    var QN : String, // 기관명
    var ORD: Int?, //순서
    var pageNo : Int?, //페이지 번호
    var numOfRows : Int? //목록 건수
)
