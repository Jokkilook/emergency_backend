package daelim.emergency_backend.WebClient

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class WebClientConfig {

    val baseURL:String = "http://apis.data.go.kr/B552657/ErmctInfoInqireService"
    val serviceKey = "YXCUlt2omoo9wIHweuRa2AwH00oXWywq3Up%2F6DVims6C8XED7Xcyn4SR3WaU83G73CP3%2FupnkVWkJnbDvVa%2B%2Bg%3D%3D"
    @Bean
    public fun webClient(builder:WebClient.Builder):WebClient{
        return builder
            //.defaultHeader("serviceKey",serviceKey)
            .baseUrl(baseURL)
            .build()
    }
}