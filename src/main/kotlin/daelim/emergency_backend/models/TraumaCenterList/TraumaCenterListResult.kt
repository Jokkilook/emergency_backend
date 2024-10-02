package daelim.emergency_backend.models

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonRootName
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.MapperFeature
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import daelim.emergency_backend.models.AvailavleBedInfo.Header

@JsonRootName("response")
data class TraumaCenterListResult(
    @set:JsonProperty("header")
    var header:Header?,

    @set:JsonProperty("body")
    var body:TraumaCenterListResultBody?,
)

@JsonRootName("body")
data class TraumaCenterListResultBody(
    @set:JsonProperty("items")
    var items:TraumaCenterListResultItems?,

    @set:JsonProperty("numOfRows")//한 페이지 결과수
    var numOfRows:Int?,

    @set:JsonProperty("pageNo")//페이지번호
    var pageNo:Int?,

    @set:JsonProperty("totalCount")//총 결과수
    var totalCount:Int?,
)

@JsonRootName("items")
data class TraumaCenterListResultItems(
    @set:JsonProperty("item")
    var item:List<TraumaCenterList>?,
)

@JsonRootName("item")
data class TraumaCenterList(
    @set:JsonProperty("rnum")
    var rnum:Int?, // 일련번호

    @set:JsonProperty("hpid")
    var hpid:String?, // 기관 ID

    @set:JsonProperty("phpid")
    var phpid:String?, // 기관 ID (OLD)

    @set:JsonProperty("dutyEmcls")
    var dutyEmcls:String?, // 응급의료기관분류

    @set:JsonProperty("dutyEmclsName")
    var dutyEmclsName:String?, // 응급의료기관분류명

    @set:JsonProperty("dutyAddr")
    var dutyAddr:String?, // 주소

    @set:JsonProperty("dutyName")
    var dutyName:String?, // 기관명

    @set:JsonProperty("dutyTel1")
    var dutyTel1:String?, // 대표전화1

    @set:JsonProperty("dutyTel3")
    var dutyTel3:String?, // 응급실전화

    @set:JsonProperty("wgs84Lon")
    var wgs84Lon:String?, // 병원경도

    @set:JsonProperty("wgs84Lat")
    var wgs84Lat:String? // 병원위도
)

fun convertXmlToTraumaCenterListResult(xmlString: String): TraumaCenterListResult {
    val xmlMapper = XmlMapper(JacksonXmlModule().apply {
        setDefaultUseWrapper(false)
    }).registerKotlinModule()
        .configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true)
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

    return xmlMapper.readValue(xmlString, TraumaCenterListResult::class.java)
}

