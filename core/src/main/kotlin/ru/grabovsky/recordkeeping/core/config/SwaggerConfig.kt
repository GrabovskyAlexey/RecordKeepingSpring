package ru.grabovsky.recordkeeping.core.config

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType
import io.swagger.v3.oas.annotations.security.SecurityScheme
import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.servers.Server
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * Config for generate OpenApi Spring documentation
 *
 * @author GrabovskyAlexey
 * @date 30.12.2023
 */
@Configuration
@SecurityScheme(
    name = "JWTAuth",
    type = SecuritySchemeType.HTTP,
    bearerFormat = "JWT",
    scheme = "bearer"
)
class SwaggerConfig {
    @Bean
    fun apiInfo(): OpenAPI {
        return OpenAPI()
            .components(Components())
            .info(
                Info()
                    .title("Record Keeping")
                    .description("Приложение для учета входящей и исходящей корреспонденции")
                    .version("1.0.0")
            )
            .addServersItem(Server().url("http://localhost:8080").description("dev"))
    }
}