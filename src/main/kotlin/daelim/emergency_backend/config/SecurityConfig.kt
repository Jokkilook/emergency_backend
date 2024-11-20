package daelim.emergency_backend.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@Configuration
@EnableWebSecurity
class SecurityConfig() {
    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        return http
            .formLogin { it.disable() }
            .httpBasic { it.disable() }
            .csrf { it.disable() }
            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .cors { it.configurationSource(corsConfigurationSource()) } // CORS 관련 설정
            .authorizeHttpRequests {
                it
                    .requestMatchers("/v1/app/**")//여기에 여러개 넣음
                    .anonymous()
//                    .requestMatchers("/api/member/**").hasRole("MEMBER")
                    .anyRequest().permitAll()
            }
            .build()
    }

    @Value("\${OUTBOUND_URL}")
    lateinit var outboundUrl: String

    // CORS 관련 설정
    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val config = CorsConfiguration()
        config.allowedOrigins = listOf("https://prod-1066673057371.asia-northeast3.run.app", "http://localhost:8080") // frontend url
        config.allowedMethods = listOf("GET")
//        config.allowedHeaders = listOf("*")
        config.allowedHeaders = mutableListOf("Content-Type", "Authorization")
        config.allowCredentials = true
        config.maxAge = 3600L

        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/v1/app/**", config)
        source.registerCorsConfiguration("/swagger-ui/**", config);
        return source
    }
}