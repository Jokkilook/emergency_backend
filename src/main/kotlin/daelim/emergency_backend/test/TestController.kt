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

    @GetMapping("/test")
    fun test():Mono<AvailableBedInfoResult>{
        return testService.getTest("/getEmrrmRltmUsefulSckbdInfoInqire")
    }
}