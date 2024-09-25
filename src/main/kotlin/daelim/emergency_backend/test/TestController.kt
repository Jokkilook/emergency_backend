package daelim.emergency_backend.test

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController {

    @GetMapping("/test")
    fun Test():ResponseEntity<TestModel> {

        val response:TestModel = TestModel(1,"TEST")

        return ResponseEntity.ok().body(response)
    }
}