package daelim.emergency_backend.WebClient

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.util.DefaultUriBuilderFactory
import java.net.http.HttpHeaders

@Configuration
class WebClientConfig {

    val baseURL:String = "http://apis.data.go.kr/B552657/ErmctInfoInqireService"

    @Bean
    public fun webClient(builder:WebClient.Builder):WebClient{
        return builder
            //.baseUrl(baseURL)
            .uriBuilderFactory(
                DefaultUriBuilderFactory(baseURL).apply {
                    encodingMode = DefaultUriBuilderFactory.EncodingMode.VALUES_ONLY
                }
            )
            .build()
    }
}