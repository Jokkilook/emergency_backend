package daelim.emergency_backend.config

import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig:WebMvcConfigurer {
    override fun extendMessageConverters(converters: MutableList<HttpMessageConverter<*>>) {
        // XML 메시지 컨버터 제거
        converters.removeIf { it.javaClass.name.contains("MappingJackson2XmlHttpMessageConverter") }
    }

    override fun configureMessageConverters(converters: MutableList<HttpMessageConverter<*>>) {
        // JSON 메시지 컨버터 우선순위로 추가
        converters.add(0, MappingJackson2HttpMessageConverter())
    }

    override fun configureContentNegotiation(configurer: ContentNegotiationConfigurer) {
        configurer.defaultContentType(org.springframework.http.MediaType.APPLICATION_JSON) // JSON을 기본 반환
    }
}