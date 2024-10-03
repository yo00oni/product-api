package kr.yooni.product.common.config

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpHeaders

@Configuration
class SpringDocsConfig {
    // /swagger-ui/index.html#/
    @Bean
    fun openAPI(): OpenAPI {
        val bearerAuth: SecurityScheme = SecurityScheme()
            .type(SecurityScheme.Type.HTTP)
            .scheme(SCHEME)
            .`in`(SecurityScheme.In.HEADER)
            .name(HttpHeaders.AUTHORIZATION)

        val addSecurityItem = SecurityRequirement()
        addSecurityItem.addList(SECURITY_SCHEMES_KEY)

        return OpenAPI()
            .components(Components().addSecuritySchemes(SECURITY_SCHEMES_KEY, bearerAuth))
            .addSecurityItem(addSecurityItem)
    }

    companion object {
        private const val SCHEME = "bearer"
        private const val SECURITY_SCHEMES_KEY = "authCode"
    }
}
