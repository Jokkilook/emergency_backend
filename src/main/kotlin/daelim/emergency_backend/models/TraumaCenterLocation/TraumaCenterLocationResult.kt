package daelim.emergency_backend.models.TraumaCenterLocation

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonRootName
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import daelim.emergency_backend.models.AvailavleBedInfo.Header

@JsonRootName("response")
data class TraumaCenterLocationResult(
    @set:JsonProperty("header")
    var header:Header?,

    @set:JsonProperty("body")
    var body:TraumaCenterLocationBody?
)

@JsonRootName("body")
data class TraumaCenterLocationBody(
    @JacksonXmlElementWrapper(useWrapping = false, localName = "items")
    @set:JsonProperty("items")
    var items:TraumaCenterLocationItems?,

    @set:JsonProperty("numOfRows")
    var numOfRows:String?,

    @set:JsonProperty("pageNo")
    var pageNo:String?,

    @set:JsonProperty("totalCount")
    var totalCount:String?
)

@JsonRootName("items")
data class TraumaCenterLocationItems(
    @set:JsonProperty("item")
    var item:List<TraumaCenterLocation>?
)

@JsonRootName("item")
data class TraumaCenterLocation(
    @set:JsonProperty("rmum") var rmum:String?, //일련번호
    @set:JsonProperty("cnt") var cnt:String?, //건수
    @set:JsonProperty("distance") var distance:String?, //거리
    @set:JsonProperty("dutyAddr") var dutyAddr:String?, //주소
    @set:JsonProperty("dutyDiv") var dutyDiv:String?, //병원분류
    @set:JsonProperty("dutyDivName") var dutyDivName:String?, //병원분류명
    @set:JsonProperty("dutyFax") var dutFax:String?, //팩스번호
    @set:JsonProperty("dutyName") var dutyName:String?, //기관명
    @set:JsonProperty("dutyTel1") var dutyTel1:String?, // 대표전화1
    @set:JsonProperty("endTime") var endTime:String?, //종료시간
    @set:JsonProperty("hpid") var hpid:String?, //기관ID
    @set:JsonProperty("latitude") var latitude:String?, //병원위도
    @set:JsonProperty("longitude") var longitude:String?, //병원경도
    @set:JsonProperty("startTime") var startTime:String? //시작시간
)