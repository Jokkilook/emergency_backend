package daelim.emergency_backend.test

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.MapperFeature
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import daelim.emergency_backend.models.AvailavleBedInfo.AvailableBedInfoResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
class TestController(val testService: TestService) {

    val xmlString = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>" +
            "<response>" +
            "    <header>" +
            "        <resultCode>00</resultCode>" +
            "        <resultMsg>NORMAL SERVICE.</resultMsg>" +
            "    </header>" +
            "    <body>" +
            "        <items>" +
            "            <item>" +
            "                <dutyName>연세대학교의과대학강남세브란스병원</dutyName>" +
            "                <dutyTel3>02-2019-3333</dutyTel3>" +
            "                <hpid>A1100015</hpid>" +
            "                <hv10>Y</hv10>" +
            "                <hv11>Y</hv11>" +
            "                <hv28>1</hv28>" +
            "                <hv29>1</hv29>" +
            "                <hv30>2</hv30>" +
            "                <hv34>5</hv34>" +
            "                <hv41>3</hv41>" +
            "                <hv42>Y</hv42>" +
            "                <hv5>Y</hv5>" +
            "                <hv6>5</hv6>" +
            "                <hv7>Y</hv7>" +
            "                <hvamyn>Y</hvamyn>" +
            "                <hvangioayn>Y</hvangioayn>" +
            "                <hvcrrtayn>Y</hvcrrtayn>" +
            "                <hvctayn>Y</hvctayn>" +
            "                <hvec>6</hvec>" +
            "                <hvecmoayn>Y</hvecmoayn>" +
            "                <hvgc>235</hvgc>" +
            "                <hvhypoayn>Y</hvhypoayn>" +
            "                <hvicc>12</hvicc>" +
            "                <hvidate>20240926204223</hvidate>" +
            "                <hvincuayn>Y</hvincuayn>" +
            "                <hvmriayn>Y</hvmriayn>" +
            "                <hvncc>4</hvncc>" +
            "                <hvoc>20</hvoc>" +
            "                <hvoxyayn>N1</hvoxyayn>" +
            "                <hvs01>15</hvs01>n" +
            "                <hvs02>2</hvs02>" +
            "                <hvs03>1</hvs03>" +
            "                <hvs04>2</hvs04>" +
            "                <hvs08>28</hvs08>" +
            "                <hvs12>12</hvs12>" +
            "                <hvs15>8</hvs15>" +
            "                <hvs17>29</hvs17>" +
            "                <hvs22>23</hvs22>" +
            "                <hvs25>5</hvs25>" +
            "                <hvs26>1</hvs26>" +
            "                <hvs27>4</hvs27>" +
            "                <hvs28>3</hvs28>" +
            "                <hvs29>6</hvs29>" +
            "                <hvs30>47</hvs30>" +
            "                <hvs31>10</hvs31>" +
            "                <hvs32>23</hvs32>" +
            "                <hvs33>6</hvs33>" +
            "                <hvs34>3</hvs34>" +
            "                <hvs35>5</hvs35>" +
            "                <hvs38>763</hvs38>" +
            "                <hvventiayn>Y</hvventiayn>" +
            "                <hvventisoayn>Y</hvventisoayn>" +
            "                <phpid>A1100015</phpid>" +
            "                <rnum>1</rnum>" +
            "            </item>" +
            "        </items>" +
            "        <numOfRows>1</numOfRows>" +
            "        <pageNo>1</pageNo>" +
            "        <totalCount>2</totalCount>" +
            "    </body>" +
            "</response>"

    @GetMapping("/test")
    fun test():AvailableBedInfoResult{
       // return testService.getTest("/getEmrrmRltmUsefulSckbdInfoInqire")
        val xmlMapper = XmlMapper(JacksonXmlModule().apply {
            setDefaultUseWrapper(false)
        }).registerKotlinModule()
            .configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

        return xmlMapper.readValue(xmlString, AvailableBedInfoResult::class.java)
    }
}