package daelim.emergency_backend.test

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.MapperFeature
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import daelim.emergency_backend.models.AvailavleBedInfo.AvailableBedInfoQuery
import daelim.emergency_backend.models.AvailavleBedInfo.AvailableBedInfoResult
import daelim.emergency_backend.models.AvailavleBedInfo.convertXmlToAvailableBedInfoResult
import daelim.emergency_backend.models.EmergencyAndSevereCaseMessage.convertXmlToEmergencyAndSevereCaseMessageResult
import daelim.emergency_backend.models.EmergencyMedicalInstitutionInfo.convertXmlToEmergencyMedicalInstitutionInfoResult
import daelim.emergency_backend.models.SevereCaseAcceptanceInfo.convertXmlToSevereCaseAcceptanceInfoResult
import daelim.emergency_backend.models.TraumaCenterBasicInfo.convertXmlToTraumaCenterBasicInfoResult
import daelim.emergency_backend.models.TraumaCenterLocation.convertXmlToTraumaCenterLocationResult
import daelim.emergency_backend.models.convertXmlToEmergencyMedicalInstitutionBasicInfoResult
import daelim.emergency_backend.models.convertXmlToEmergencyMedicalInstitutionLocationResult
import daelim.emergency_backend.models.convertXmlToTraumaCenterListResult
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono
import java.net.URLEncoder

@Service
class TestService(val webClient: WebClient) {

    val serviceKey = "YXCUlt2omoo9wIHweuRa2AwH00oXWywq3Up%2F6DVims6C8XED7Xcyn4SR3WaU83G73CP3%2FupnkVWkJnbDvVa%2B%2Bg%3D%3D"

    val logger = LoggerFactory.getLogger(TestService::class.java)
    fun <T> getTest(url:String, query:Map<String,String>):T{

        val stage1 = URLEncoder.encode("경기도", "UTF-8")
        val stage2 = URLEncoder.encode("평택시", "UTF-8")

        val response = webClient.get()
            .uri{
                var builder = it.path(url)

                query.forEach{
                    builder.queryParam(it.key, it.value)
                }

                builder.queryParam("serviceKey",serviceKey)
                .build()
            }
            .retrieve()
            .bodyToMono(String::class.java)
            .map { xmlString ->

                when (url) {
                    "/getEmrrmRltmUsefulSckbdInfoInqire" -> { convertXmlToAvailableBedInfoResult(xmlString) }
                    "/getSrsillDissAceptncPosblInfoInqire" -> { convertXmlToSevereCaseAcceptanceInfoResult(xmlString) }
                    "/getEgytListInfoInqire" -> { convertXmlToEmergencyMedicalInstitutionInfoResult(xmlString) }
                    "/getEgytLcinfoInqire" -> { convertXmlToEmergencyMedicalInstitutionLocationResult(xmlString) }
                    "/getEgytBassInfoInqire" -> { convertXmlToEmergencyMedicalInstitutionBasicInfoResult(xmlString) }
                    "/getStrmListInfoInqire" -> { convertXmlToTraumaCenterListResult(xmlString) }
                    "/getStrmLcinfoInqire" -> { convertXmlToTraumaCenterLocationResult(xmlString) }
                    "/getStrmBassInfoInqire" -> { convertXmlToTraumaCenterBasicInfoResult(xmlString) }
                    "/getEmrrmSrsillDissMsgInqire" -> { convertXmlToEmergencyAndSevereCaseMessageResult(xmlString) }
                    else -> { convertXmlToAvailableBedInfoResult(xmlString) }
                }

            }
        return response.block() as T
    }

}