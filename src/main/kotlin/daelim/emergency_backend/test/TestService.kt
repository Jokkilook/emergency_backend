package daelim.emergency_backend.test

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.MapperFeature
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import daelim.emergency_backend.models.AvailavleBedInfo.AvailableBedInfoResult
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.util.UriComponentsBuilder
import reactor.core.publisher.Mono
import java.net.URI

@Service
class TestService(val webClient: WebClient) {

    val serviceKey = "YXCUlt2omoo9wIHweuRa2AwH00oXWywq3Up%2F6DVims6C8XED7Xcyn4SR3WaU83G73CP3%2FupnkVWkJnbDvVa%2B%2Bg%3D%3D"
    //val serviceKey = "YXCUlt2omoo9wIHweuRa2AwH00oXWywq3Up/6DVims6C8XED7Xcyn4SR3WaU83G73CP3/upnkVWkJnbDvVa++g=="
    fun getTest(url:String):Mono<AvailableBedInfoResult>{

        val response = webClient.get()
            .uri{
                it.path(url)
                .queryParam("serviceKey",serviceKey)
                .build()
            }
            .retrieve()
            .bodyToMono(String::class.java)
            .map { xmlString ->
                print(xmlString)
                convertXmlToAvailableBedInfoResult(xmlString) }

        return  response
    }

    private fun convertXmlToAvailableBedInfoResult(xmlString: String): AvailableBedInfoResult {
        val xmlMapper = XmlMapper(JacksonXmlModule().apply {
            setDefaultUseWrapper(false)
        }).registerKotlinModule()
            .configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

        return xmlMapper.readValue(xmlString, AvailableBedInfoResult::class.java)
    }
}