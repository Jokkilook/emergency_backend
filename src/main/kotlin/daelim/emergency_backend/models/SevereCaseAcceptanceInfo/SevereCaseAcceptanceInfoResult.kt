package daelim.emergency_backend.models.SevereCaseAcceptanceInfo

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonRootName
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import daelim.emergency_backend.models.AvailavleBedInfo.Header

@JsonRootName("response")
data class SevereCaseAcceptanceInfoResult(
    @JsonProperty("header")
    var header:Header?,
    @JsonProperty("body")
    var body:SevereCaseAcceptanceInfoBody?

)

@JsonRootName("body")
data class SevereCaseAcceptanceInfoBody(
    @JacksonXmlElementWrapper(useWrapping = false, localName = "items")
    @set:JsonProperty("items")
    var items: SevereCaseAcceptanceInfoItems?,
    @set:JsonProperty("numOfRows")
    var numOfRows:String?,
    @set:JsonProperty("pageNo")
    var pageNo:String?,
    @set:JsonProperty("totalCount")
    var totalCount:String?
)

@JsonRootName("items")
data class SevereCaseAcceptanceInfoItems(
    @JsonProperty("item")
    var items:List<SevereCaseAcceptanceInfo>?
)

@JsonRootName("item")
data class SevereCaseAcceptanceInfo(
    @JsonProperty("dutyName") var dutyName:String?, //기관명
    @JsonProperty("hpid") var hpid:String?,//기관ID
    @JsonProperty("mkioskty28") var mkioskty28:String?, //응급실 가용 여부
    @JsonProperty("mkioskty1") var mkioskty1:String?, //재관류중재술 심근경색 가용 여부
    @JsonProperty("mkioskty2") var mkioskty2:String?, //재관류중재술 뇌경색 가용 여부
    @JsonProperty("mkioskty3") var mkioskty3:String?, //뇌출혈수술 거미막하출혈 가용 여부
    @JsonProperty("mkioskty4") var mkioskty4:String?, //뇌출혈수술 거미막하출혈 외 가용 여부
    @JsonProperty("mkioskty5") var mkioskty5:String?, //대동맥응급 흉부 가용 여부
    @JsonProperty("mkioskty6") var mkioskty6:String?, //대동맥응급 복부 가용 여부
    @JsonProperty("mkioskty7") var mkioskty7:String?, //담낭담관질환 담낭질환 가용 여부
    @JsonProperty("mkioskty8") var mkioskty8:String?, //담낭담관질환 담도포함질환 가용 여부
    @JsonProperty("mkioskty9") var mkioskty9:String?, //복부응급수술 비외상 가용 여부
    @JsonProperty("mkioskty10") var mkioskty10:String?, //장중첩/폐색 영유아 가용 여부
    @JsonProperty("mkioskty11") var mkioskty11:String?, //응급내시경 성인 위장관 가용 여부
    @JsonProperty("mkioskty12") var mkioskty12:String?, //응급내시경 영유아 위장관 가용 여부
    @JsonProperty("mkioskty13") var mkioskty13:String?, //응급내시경 성인 기관지 가용 여부
    @JsonProperty("mkioskty14") var mkioskty14:String?, //응급내시경 영유아 기관지 가용 여부
    @JsonProperty("mkioskty15") var mkioskty15:String?, //저출생체중아 집중치료 가용 여부
    @JsonProperty("mkioskty16") var mkioskty16:String?, //산부인과응급 분만 가용 여부
    @JsonProperty("mkioskty17") var mkioskty17:String?, //산부인과응급 산과수술 가용 여부
    @JsonProperty("mkioskty18") var mkioskty18:String?, //산부인과응급 부인과수술 가용 여부
    @JsonProperty("mkioskty19") var mkioskty19:String?, //중증화상 전문치료 가용 여부
    @JsonProperty("mkioskty20") var mkioskty20:String?, //사지접합 수족지접합 가용 여부
    @JsonProperty("mkioskty21") var mkioskty21:String?, //사지접합 수족지접합 외 가용 여부
    @JsonProperty("mkioskty22") var mkioskty22:String?, //응급투석 HD 가용 여부
    @JsonProperty("mkioskty23") var mkioskty23:String?, //응급투석 CRRT 가용 여부
    @JsonProperty("mkioskty24") var mkioskty24:String?, //정신과적응급 폐쇄병동입원 가용 여부
    @JsonProperty("mkioskty25") var mkioskty25:String?, //안과적수술 응급 가용 여부
    @JsonProperty("mkioskty26") var mkioskty26:String?, //영상의학혈관중재 성인 가용 여부
    @JsonProperty("mkioskty27") var mkioskty27:String?, //영상의학혈관중애 영유아 가용 여부
    @JsonProperty("M_KOISK_TY10_MSG") var M_KOISK_TY10_MSG:String?, //장중첩/폐색(영유아) 가능연령
    @JsonProperty("M_KOISK_TY12_MSG") var M_KOISK_TY12_MSG:String?, //위장관 응급내시경(영유아) 가능연령
    @JsonProperty("M_KOISK_TY14_MSG") var M_KOISK_TY14_MSG:String?, //기관지 응급내시경(영유아) 가능연령
    @JsonProperty("M_KOISK_TY15_MSG") var M_KOISK_TY15_MSG:String?, //저출생 체중아 가능연령
    @JsonProperty("M_KOISK_TY27_MSG") var M_KOISK_TY27_MSG:String?, //영상의학 혈관 중재적 시술(영유아) 가능연령
)