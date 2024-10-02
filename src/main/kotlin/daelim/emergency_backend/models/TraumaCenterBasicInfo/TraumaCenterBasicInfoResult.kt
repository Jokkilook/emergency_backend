package daelim.emergency_backend.models.TraumaCenterBasicInfo

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonRootName
import daelim.emergency_backend.models.AvailavleBedInfo.Header

@JsonRootName("response")
data class TraumaCenterBasicInfoResult(
    @set:JsonProperty("header")
    var header: Header?,

    @set:JsonProperty("body")
    var body: TraumaCenterBasicInfoBody?,
)

@JsonRootName("body")
data class TraumaCenterBasicInfoBody(
    @set:JsonProperty("items")
    var items: TraumaCenterBasicInfoItems?,

    @set:JsonProperty("numOfRows")//한 페이지 결과수
    var numOfRows:Int?,

    @set:JsonProperty("pageNo")//페이지번호
    var pageNo:Int?,

    @set:JsonProperty("totalCount")//총 결과수
    var totalCount:Int?,
)

@JsonRootName("items")
data class TraumaCenterBasicInfoItems(
    @set:JsonProperty("item")
    var item:List<TraumaCenterBasicInfo>?,
)

@JsonRootName("item")
data class TraumaCenterBasicInfo(
    @set:JsonProperty("hpid") var hpid: String, //기관ID
    @set:JsonProperty("dutyName") var dutyName: String, //기관명
    @set:JsonProperty("postCdn1") var postCdn1: Int, //우편번호1
    @set:JsonProperty("postCdn2") var postCdn2: Int, //우편번호2
    @set:JsonProperty("dutyAddr") var dutyAddr: String, //주소
    @set:JsonProperty("dutyTel1") var dutyTel1: String, //대표전화1
    @set:JsonProperty("dutyTel3") var dutyTel3: String, //응급실전화
    @set:JsonProperty("hvec") var hvec: Int, //응급실
    @set:JsonProperty("hvoc") var hvoc: Int, //수술실
    @set:JsonProperty("hvcc") var hvcc: Int, //신경수술실
    @set:JsonProperty("hvccc") var hvccc: Int, //흉부중환자
    @set:JsonProperty("hvicc") var hvicc: Int, //일반중환자
    @set:JsonProperty("hvgc") var hvgc: Int, //입원실
    @set:JsonProperty("dutyHayn") var dutyHayn: Boolean, //입원실가능여부(1/2)
    @set:JsonProperty("dutyHano") var dutyHano: Int, //병상수
    @set:JsonProperty("dutyInf") var dutyInf: String, //기관설명상세
    @set:JsonProperty("dutyMapimg") var dutyMapimg: String, //간이약도
    @set:JsonProperty("dutyEryn") var dutyEryn: Boolean, //응급실운영여부(1/2)
    @set:JsonProperty("dutyTime1c") var dutyTime1c: Int, //진료시간(월요일)C
    @set:JsonProperty("dutyTime2c") var dutyTime2c: Int, //진료시간(화요일)C
    @set:JsonProperty("dutyTime3c") var dutyTime3c: Int, //진료시간(수요일)C
    @set:JsonProperty("dutyTime4c") var dutyTime4c: Int, //진료시간(목요일)C
    @set:JsonProperty("dutyTime5c") var dutyTime5c: Int, //진료시간(금요일)C
    @set:JsonProperty("dutyTime6c") var dutyTime6c: Int, //진료시간(토요일)C
    @set:JsonProperty("dutyTime7c") var dutyTime7c: Int, //진료시간(일요일)C
    @set:JsonProperty("dutyTime8c") var dutyTime8c: Int, //진료시간(공휴일)C
    @set:JsonProperty("dutyTime1s") var dutyTime1s: Int, //진료시간(월요일)S
    @set:JsonProperty("dutyTime2s") var dutyTime2s: Int, //진료시간(화요일)S
    @set:JsonProperty("dutyTime3s") var dutyTime3s: Int, //진료시간(수요일)S
    @set:JsonProperty("dutyTime4s") var dutyTime4s: Int, //진료시간(목요일)S
    @set:JsonProperty("dutyTime5s") var dutyTime5s: Int, //진료시간(금요일)S
    @set:JsonProperty("dutyTime6s") var dutyTime6s: Int, //진료시간(토요일)S
    @set:JsonProperty("dutyTime7s") var dutyTime7s: Int, //진료시간(일요일)S
    @set:JsonProperty("dutyTime8s") var dutyTime8s: Int, //진료시간(공휴일)S
    @set:JsonProperty("MKioskTy25") var MKioskTy25: Boolean, //응급실(Emergency gate keeper) <- Y : 가능 N : 불가능
    @set:JsonProperty("MKioskTy1") var MKioskTy1: Boolean, //뇌추혈수술 <- Y : 가능 N : 불가능
    @set:JsonProperty("MKioskTy2") var MKioskTy2: Boolean, //뇌경색의재관류 <- Y : 가능 N : 불가능
    @set:JsonProperty("MKioskTy3") var MKioskTy3: Boolean, //심근경색의재관류 <- Y : 가능 N : 불가능
    @set:JsonProperty("MKioskTy4") var MKioskTy4: Boolean, //복부손상의수술 <- Y : 가능 N : 불가능
    @set:JsonProperty("MKioskTy5") var MKioskTy5: Boolean, //사지접합의수술 <- Y : 가능 N : 불가능
    @set:JsonProperty("MKioskTy6") var MKioskTy6: Boolean, //응급내시경 <- Y : 가능 N : 불가능
    @set:JsonProperty("MKioskTy7") var MKioskTy7: Boolean, //응급투석 <- Y : 가능 N : 불가능
    @set:JsonProperty("MKioskTy8") var MKioskTy8: Boolean, //조산산모 <- Y : 가능 N : 불가능
    @set:JsonProperty("MKioskTy9") var MKioskTy9: Boolean, //정신질환자 <- Y : 가능 N : 불가능
    @set:JsonProperty("MKioskTy10") var MKioskTy10: Boolean, //신생아 <- Y : 가능 N : 불가능
    @set:JsonProperty("MKioskTy11") var MKioskTy11: Boolean, //중증화상 <- Y : 가능 N : 불가능
    @set:JsonProperty("wgs84Lon") var wgs84Lon: Double, //병원경도
    @set:JsonProperty("wgs84Lat") var wgs84Lat: Double, //병원위도
    @set:JsonProperty("dgidldName") var dgidldName: String, //진료과목
    @set:JsonProperty("hpbdn") var hpbdn: Int, //병상수
    @set:JsonProperty("hpccuyn") var hpccuyn: Int, //흉부중환자실
    @set:JsonProperty("hpcuyn") var hpcuyn: Int, //신경중환자실 ---변수명 잘못됨 수정해야 함---
    @set:JsonProperty("hperyn") var hperyn: Int, //응급실
    @set:JsonProperty("hpgryn") var hpgryn: Int, //입원실
    @set:JsonProperty("hpiuyn") var hpiuyn: Int, //입반중환자실
    @set:JsonProperty("hpnicuyn") var hpnicuyn: Int, //신생아중환자실
    @set:JsonProperty("hpopuyn") var hpopuyn: Int, //수술실
)

fun convertXmlToTraumaCenterBasicInfoResult(xmlString: String): TraumaCenterBasicInfoResult {
    val xmlMapper = XmlMapper(JacksonXmlModule().apply {
        setDefaultUseWrapper(false)
    }).registerKotlinModule()
        .configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true)
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

    return xmlMapper.readValue(xmlString, TraumaCenterBasicInfoResult::class.java)
}

class TraumaCenterBasicInfoQuery{
    var HPID:String?, //기관ID
    var QN:String?, //기관명
    var pageNo:Int,
    var numOfRows:Int
}