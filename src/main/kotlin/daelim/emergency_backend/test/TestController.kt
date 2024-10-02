package daelim.emergency_backend.test

import daelim.emergency_backend.models.AvailavleBedInfo.AvailableBedInfoResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
class TestController(val testService: TestService) {

    @GetMapping("/test")
    fun test():Mono<AvailableBedInfoResult>{
        return testService.getTest("/getEgytLcinfoInqire")
    }
}