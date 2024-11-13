package daelim.emergency_backend.test

import daelim.emergency_backend.model.datagokr.convertXmlToAvailableBedInfoResult
import daelim.emergency_backend.model.datagokr.convertXmlToEmergencyAndSevereCaseMessageResult
import daelim.emergency_backend.model.datagokr.convertXmlToEmergencyMedicalInstitutionInfoResult
import daelim.emergency_backend.model.datagokr.convertXmlToSevereCaseAcceptanceInfoResult
import daelim.emergency_backend.model.datagokr.convertXmlToTraumaCenterBasicInfoResult
import daelim.emergency_backend.model.datagokr.convertXmlToTraumaCenterLocationResult
import daelim.emergency_backend.model.datagokr.convertXmlToEmergencyMedicalInstitutionBasicInfoResult
import daelim.emergency_backend.model.datagokr.convertXmlToEmergencyMedicalInstitutionLocationResult
import daelim.emergency_backend.model.datagokr.convertXmlToTraumaCenterListResult
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
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