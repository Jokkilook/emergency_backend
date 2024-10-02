package daelim.emergency_backend.models.EmergencyAndSevereCaseMessage

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonRootName
import daelim.emergency_backend.models.AvailavleBedInfo.Header

data class EmergencyAndSevereCaseMessageResult(
    @set:JsonProperty("header")
    var header: Header?,

    @set:JsonProperty("body")
    var body: EmergencyAndSevereCaseMessageBody?,
)

@JsonRootName("body")
data class EmergencyAndSevereCaseMessageBody(
    @set:JsonProperty("items")
    var items: EmergencyAndSevereCaseMessageItems?,

    @set:JsonProperty("numOfRows")//한 페이지 결과수
    var numOfRows:Int?,

    @set:JsonProperty("pageNo")//페이지번호
    var pageNo:Int?,

    @set:JsonProperty("totalCount")//총 결과수
    var totalCount:Int?,
)

@JsonRootName("items")
data class EmergencyAndSevereCaseMessageItems(
    @set:JsonProperty("item")
    var item:List<EmergencyAndSevereCaseMessage>?,
)

@JsonRootName("item")
data class EmergencyAndSevereCaseMessage(
    @set:JsonProperty("rnum") var rnum: Int, //일련번호
    @set:JsonProperty("dutyAddr") var dutyAddr: String, //기관주소
    @set:JsonProperty("dutyName") var dutyName: String, //기관명
    @set:JsonProperty("emcOrgCod") var emcOrgCod: String, //기관코드(기관ID)
    @set:JsonProperty("hpid") var hpid: String, //기관코드
    @set:JsonProperty("symBlkMsq") var symBlkMsq: String, //전달메세지
    @set:JsonProperty("symBlkMsqTyp") var symBlkMsqTyp: String, //메세지구분
    @set:JsonProperty("symTypCod") var symTypCod: String, //중층징환구분
    @set:JsonProperty("symTypMag") var symTypMag: String, //중증질환명
    @set:JsonProperty("synOutDspYon") var synOutDspYon: Boolean, //중증질환 표출 구분 (Y:해제, N:차단)
    @set:JsonProperty("sysOutDspMth") var sysOutDspMth: Boolean, //표출 차단구분 (Y:자동, N:수동)
    @set:JsonProperty("symBlkSttDtm") var symBlkSttDtm: Int, //차단시작
    @set:JsonProperty("sysBlkEndDtm") var sysBlkEndDtm: Int, //차단종료
)