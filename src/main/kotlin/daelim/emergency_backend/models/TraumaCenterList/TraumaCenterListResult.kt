package daelim.emergency_backend.models

@JsonRootName("response")
data class TraumaCenterListResult(
    @set:JsonProperty("header")
    var header:Header?,

    @set:JsonProperty("body")
    var body:Body?,
)


@JsonRootName("header")
data class Header(
    @set:JsonProperty("resultCode")//결과코드
    var resultCode:String?,

    @set:JsonProperty("resultMsg")//결과메시지
    var resultMsg:String?,
)

@JsonRootName("body")
data class Body(
    @set:JsonProperty("items")
    var items:Items?,

    @set:JsonProperty("numOfRows")//한 페이지 결과수
    var numOfRows:Int?,

    @set:JsonProperty("pageNo")//페이지번호
    var pageNo:Int?,

    @set:JsonProperty("totalCount")//총 결과수
    var totalCount:Int?,
)

@JsonRootName("items")
data class Items(
    @set:JsonProperty("item")
    var item:List<TraumaCenterList>?,
)

@JsonRootName("item")
data class Item(
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

