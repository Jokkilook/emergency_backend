package daelim.emergency_backend.models.AvailavleBedInfo

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonRootName
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper

// ** 데이터 클래스 Xml 파싱과 함께 정의하는 방법 **
// 반환되는 Xml 구조에 따라 똑같은 구조로 데이터 클래스를 만들어줘야함
//
// <response>
//   <header>
//      <resultCode></resultCode>
//      <resultMsg></resultMsg>
//   </header>
//   <body>
//       <items>
//           <item><item>
//           <item><item>
//           <item><item>
//       </items>
//       <numOfRows></numOfRows>
//       <pageNo></pageNo>
//       <totalCount></totalCount>
//   </body>
// <response>
//
// 위와 같은 형식으로 반환되기 때문에 일단 데이터를 구분 지을 수 있는 이름으로 클래스를 만들고, (ex. AvailableBedInfoResult)
// @JsonRootName 어노테이션을 사용해 "response"를 명시해줌으로서 response 태그의 값을 받는 다는 것을 알려야 함.
// @Set:JsonProperty 어노테이션으로 명시된 이름의 태그 값을 변수에 할당한다고 알려준다.
// 대부분 위와 같은 형식이니 아래 정의된 클래스를 참고하여 다른 클래스도 변환하면 됨.
// items 부분이 까다로우니 자세히 봐야함.
// 이 데이터 클래스의 경우 response에 해당하는 AvailableBedInfoResult 클래스 안에
// header와 body 태그 데이터를 담아줄 변수(변수는 모두 var 로 선언한다.)를 놓고,
// header는 다시 header 클래스를 생성하여 태그 안의 내용인 resultCode, resultMsg 변수를 놨음.
// 실질적인 내용이 들어가는 AvailableBedInfoResult 의 body 변수에는 다시 Body 클래스를 생성.
// body 태그에는 item을 요소로 갖는 items 태그와, 기타 등등 3가지가 있음.
// 기타 3가지는 그냥 태그 이름을 명시해주면 되지만, items 태그는 item이 여러개이기 때문에 다름.
// 만들어야 할 클래스는 총 4개이다. ( ~~는 본인이 담당한 데이터의 이름)
// 클래스의 이름은 아래 형식으로 정의한다.
// 1. ~~Result 클래스
// 2. ~~Body 클래스
// 3. ~~Items 클래스
// 4. ~~클래스 (item 클래스)
// Header 클래스는 구조가 동일해서, 따로 만들지 않고
// AvailableBedInfoResult.kt 의 Header를 임포트해서 사용하면 된다.
// 우선 요소인 item (응급의료정보) 클래스 (여기선 AvailableBedInfo)를 만들어준 후, (4번 클래스)
// 오직 item(AvailableBedInfo)의 리스트를 변수로 하나 갖는 Items 클래스를 생성한다. (3번 클래스)
// 그리고 Body 클래스의 items 변수의 자료형을 Items 클래스로 지정하고, @set:JsonProperty("items")를 해주면 된다. (2번 클래스)
// 마지막으로 header와 body를 변수로 갖는 ~~Result 클래스를 생성해주면 된다. JsonProperty는 "response" 로 (1번 클래스)
// 헷갈리면 이 데이터 클래스를 차근차근 살펴보는 걸 추천함.

@JsonRootName("response")
data class AvailableBedInfoResult(

    @set:JsonProperty("header")
    var header:Header?,

    @set:JsonProperty("body")
    var body:AvailableBedInfoBody?,
)

@JsonRootName("Header")
data class Header(
    @set:JsonProperty("resultCode")
    var resultCode:String?,
    @set:JsonProperty("resultMsg")
    var resultMsg:String?
)

@JsonRootName("body")
data class AvailableBedInfoBody(

    @JacksonXmlElementWrapper(useWrapping = false, localName = "items")
    @set:JsonProperty("items")
    var items:AvailableBedInfoItems?,
    @set:JsonProperty("numOfRows")
    var numOfRows:String?,
    @set:JsonProperty("pageNo")
    var pageNo:String?,
    @set:JsonProperty("totalCount")
    var totalCount:String?
)

@JsonRootName("items")
data class AvailableBedInfoItems(
    @set:JsonProperty("item")
    var item:List<AvailableBedInfo>?
)

@JsonRootName("item")
data class AvailableBedInfo(
    @set:JsonProperty("rmum") var rmum:String?, //일련번호
    @set:JsonProperty("hpid") var hpid:String?, //기관코드
    @set:JsonProperty("phpid") var phpid:String?, //구 기관코드
    @set:JsonProperty("hvidate") var hvidate:String?, //입력일시
    @set:JsonProperty("hvec") var hvec:String?, //일반병상 수
    @set:JsonProperty("hvoc") var hvoc:String?, //수술실 병상 수
    @set:JsonProperty("hvcc") var hvcc:String?, //중환자실 신경과 병상 수
    @set:JsonProperty("hvncc") var hvncc:String?, //중환자실 신생아 병상 수
    @set:JsonProperty("hvccc") var hvccc:String?, //중환자실 흉부외과 병상 수
    @set:JsonProperty("hvicc") var hvicc:String?, //중환자실 일반 입원실 수
    @set:JsonProperty("hvdnm") var hvdnm:String?, //당직의 이름
    @set:JsonProperty("hvctayn") var hvctayn:String?, //CT 가용 여부
    @set:JsonProperty("hvmriayn") var hvmriayn:String?, //MRI 가용 여부
    @set:JsonProperty("hvangioayn") var hvangioayn:String?, //혈관촬영기 가용 여부
    @set:JsonProperty("hvventiayn") var hvventiayn:String?, //인공호흡기 가용 여부
    @set:JsonProperty("hvventisoayn") var hvventisoayn:String?, //인큐베이터 가용 여부
    @set:JsonProperty("hvcrrtayn") var hvcrrtayn:String?, //CRRT 가용 여부
    @set:JsonProperty("hvecmoayn") var hvecmoayn:String?, //ECMO 가용 여부
    @set:JsonProperty("hvoxyayn") var hvoxyayn:String?, //고압 산소 치료기 가용 여부
    @set:JsonProperty("hvhypoayn") var hvhypoayn:String?, //중심 체온 조절 유도기 가용 여부
    @set:JsonProperty("hvamyn") var hvamyn:String?, //구급차가용여부
    @set:JsonProperty("hv1") var hv1:String?, //응급실 당직의 직통 연락처
    @set:JsonProperty("hv2") var hv2:String?, //내과 중환자실 수
    @set:JsonProperty("hv3") var hv3:String?, //외과 중환자실 수
    @set:JsonProperty("hv4") var hv4:String?, //외과입원실(정형외과) 수
    @set:JsonProperty("hv5") var hv5:String?, //신경과입원실 수
    @set:JsonProperty("hv6") var hv6:String?, //신경외과 중환자실 수
    @set:JsonProperty("hv7") var hv7:String?, //약물중환자 수
    @set:JsonProperty("hv8") var hv8:String?, //화상 중환자실 수
    @set:JsonProperty("hv9") var hv9:String?, //외상 중환자실 수
    @set:JsonProperty("hv10") var hv10:String?, //소아 가능 여부
    @set:JsonProperty("hv11") var hv11:String?, //인큐베이터(보육기) 가용 여부@field:Element(name="")
    @set:JsonProperty("hv12") var hv12:String?, //솓아당직의 직통연락처
    @set:JsonProperty("hv13") var hv13:String?, //격리진료구역 음압격리병상 수
    @set:JsonProperty("hv14") var hv14:String?, //격리진료구역 일반격리병상 수
    @set:JsonProperty("hv15") var hv15:String?, //소아음악격리 수
    @set:JsonProperty("hv16") var hv16:String?, //소아일반격리 수
    @set:JsonProperty("hv17") var hv17:String?, //응급전용 중환자실 음압격리 수
    @set:JsonProperty("hv18") var hv18:String?, //응급전용 중환자실 일반격리 수
    @set:JsonProperty("hv19") var hv19:String?, //응급전용 입원실 음압격리 수
    @set:JsonProperty("hv21") var hv21:String?, //응급전용 입원실 일반격리 수
    @set:JsonProperty("hv22") var hv22:String?, //감염병 전담병상 중환자실 내 음압격리병상 수
    @set:JsonProperty("hv23") var hv23:String?, //감염병 전담병상 중환사실 수
    @set:JsonProperty("hv24") var hv24:String?, //감염 중증 병상 수
    @set:JsonProperty("hv25") var hv25:String?, //감염 준-중증 병상 수
    @set:JsonProperty("hv26") var hv26:String?, //감염 중등증 병상 수
    @set:JsonProperty("hv27") var hv27:String?, //코호트 격리 수
    @set:JsonProperty("hv28") var hv28:String?, //소아 수
    @set:JsonProperty("hv29") var hv29:String?, //응급실 음압 격리 병상 수
    @set:JsonProperty("hv30") var hv30:String?, //응급실 일반 격리 병상 수
    @set:JsonProperty("hv31") var hv31:String?, //응급전용 중환자실 수
    @set:JsonProperty("hv32") var hv32:String?, //중환자실 소아 수
    @set:JsonProperty("hv33") var hv33:String?, //응급전용 소아중환자실 수
    @set:JsonProperty("hv34") var hv34:String?, //중환자실 심장내과 수
    @set:JsonProperty("hv35") var hv35:String?, //중환자실 음압격리 수
    @set:JsonProperty("hv36") var hv36:String?, //응급전용 입원실 수
    @set:JsonProperty("hv37") var hv37:String?, //응급전용 소아입원실 수
    @set:JsonProperty("hv38") var hv38:String?, //외상전용 입원실 수
    @set:JsonProperty("hv39") var hv39:String?, //외상전용 수술실 수
    @set:JsonProperty("hv40") var hv40:String?, //정신과 폐쇄병동 입원실 수
    @set:JsonProperty("hv41") var hv41:String?, //음압격리 입원실 수
    @set:JsonProperty("hv42") var hv42:String?, //분만실 수
    @set:JsonProperty("hv43") var hv43:String?, //화상전용처치실 수
    @set:JsonProperty("dutyName") var dutyName:String?, //기관명
    @set:JsonProperty("dutyTel3") var dutyTel3:String?, //응급실 전화
    @set:JsonProperty("hvs01") var hvs01:String?, //일반 기준 수
    @set:JsonProperty("hvs02") var hvs02:String?, //소아 기준 수
    @set:JsonProperty("hvs03") var hvs03:String?, //응급실 음압 격리 병상 기준 수
    @set:JsonProperty("hvs04") var hvs04:String?, //응급실 일반 격리 병상 기준 수
    @set:JsonProperty("hvs05") var hvs05:String?, //응급전용 중환자실 기준 수
    @set:JsonProperty("hvs06") var hvs06:String?, //중환자실 내과 기준 수
    @set:JsonProperty("hvs07") var hvs07:String?, //중환자실 외과 기준 수
    @set:JsonProperty("hvs08") var hvs08:String?, //중환자실 신생아 기준 수
    @set:JsonProperty("hvs09") var hvs09:String?, //중환자실 소아 기준 수
    @set:JsonProperty("hvs10") var hvs10:String?, //응급전용 소아중환자실 기준 수
    @set:JsonProperty("hvs11") var hvs11:String?, //중환자실 신경과 기준 수
    @set:JsonProperty("hvs12") var hvs12:String?, //중환자실 신경외과 기준 수
    @set:JsonProperty("hvs13") var hvs13:String?, //중환자실 화상 기준 수
    @set:JsonProperty("hvs14") var hvs14:String?, //중환자실 외상 기준 수
    @set:JsonProperty("hvs15") var hvs15:String?, //중환자실 심장내과 기준 수
    @set:JsonProperty("hvs16") var hvs16:String?, //중환자실 흉부외과 기준 수
    @set:JsonProperty("hvs17") var hvs17:String?, //중환자실 일반 기준 수
    @set:JsonProperty("hvs18") var hvs18:String?, //중환자실  음압격리 기준 수
    @set:JsonProperty("hvs19") var hvs19:String?, //응급전용 입원실 기준 수
    @set:JsonProperty("hvs20") var hvs20:String?, //응급전용 소아입원실 기준 수
    @set:JsonProperty("hvs21") var hvs21:String?, //외상전용 입원실 기준 수
    @set:JsonProperty("hvs22") var hvs22:String?, //수술실 기준 수
    @set:JsonProperty("hvs23") var hvs23:String?, //외상전용 수술실 기준 수
    @set:JsonProperty("hvs24") var hvs24:String?, //정신과 폐쇄병동 입원실 기준 수
    @set:JsonProperty("hvs25") var hvs25:String?, //음압격리 기준 수
    @set:JsonProperty("hvs26") var hvs26:String?, //분만실 기준 수
    @set:JsonProperty("hvs27") var hvs27:String?, //CT 기준 수
    @set:JsonProperty("hvs28") var hvs28:String?, //MRI 기준 수
    @set:JsonProperty("hvs29") var hvs29:String?, //혈관 촬영기 기준 수
    @set:JsonProperty("hvs30") var hvs30:String?, //인공호흡기 일반 기준 수
    @set:JsonProperty("hvs31") var hvs31:String?, //인공호흡기 조산아 기준 수
    @set:JsonProperty("hvs32") var hvs32:String?, //인큐베이터 기준 수
    @set:JsonProperty("hvs33") var hvs33:String?, //CRRT 기준 수
    @set:JsonProperty("hvs34") var hvs34:String?, //ECMO기준 수
    @set:JsonProperty("hvs35") var hvs35:String?, //중심체온조절유도기 기준 수
    @set:JsonProperty("hvs36") var hvs36:String?, //화상전용처치실 기준 수
    @set:JsonProperty("hvs37") var hvs37:String?, //고압산소치료기 기준 수
    @set:JsonProperty("hvs38") var hvs38:String?, //일반 입원실 기준 수
    @set:JsonProperty("hvs46") var hvs46:String?, //격리진료구역 음압격리 기준 수
    @set:JsonProperty("hvs47") var hvs47:String?, //격리진료구역 일반격리 기준 수
    @set:JsonProperty("hvs48") var hvs48:String?, //소아음압격리 기준 수
    @set:JsonProperty("hvs49") var hvs49:String?, //소아일반격리 기준 수
    @set:JsonProperty("hvs50") var hvs50:String?, //응급전용 중환자실 음압격리 기준 수
    @set:JsonProperty("hvs51") var hvs51:String?, //응급전용 중환자시 일반격리 기준 수
    @set:JsonProperty("hvs52") var hvs52:String?, //응급전용 입원실 음압격리 기준 수
    @set:JsonProperty("hvs53") var hvs53:String?, //응급전용 입원실 일반격리 기준 수
    @set:JsonProperty("hvs54") var hvs54:String?, //감염병 전담병상 중환자실 기준 수
    @set:JsonProperty("hvs55") var hvs55:String?, //감염병 전담병상 중환자실 내 음압격리 병상 기준 수
    @set:JsonProperty("hvs56") var hvs56:String?, //감염 중증 병상 기준 수
    @set:JsonProperty("hvs57") var hvs57:String?, //감염 준-중증 병상 기준 수
    @set:JsonProperty("hvs58") var hvs58:String?, //감염 중등증 병상 기준 수
    @set:JsonProperty("hvs59") var hvs59:String?, //코호트 격리 기준 수
)

//API의 쿼리 클래스 만들기
class AvailableBedInfoQuery(
    var STAGE1:String,
    var STAGE2:String,
    var pageNo:Int?,
    var numOfRows:Int?
)