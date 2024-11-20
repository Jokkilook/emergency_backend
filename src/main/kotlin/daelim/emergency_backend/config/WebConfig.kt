package daelim.emergency_backend.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig:WebMvcConfigurer {
    override fun configureContentNegotiation(configurer: ContentNegotiationConfigurer) {
        configurer.defaultContentType(org.springframework.http.MediaType.APPLICATION_JSON) // JSON을 기본 반환
    }
}