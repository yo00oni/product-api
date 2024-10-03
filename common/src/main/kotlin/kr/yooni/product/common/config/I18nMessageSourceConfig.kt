package kr.yooni.product.common.config

import org.springframework.context.MessageSource
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.support.MessageSourceAccessor
import org.springframework.context.support.ReloadableResourceBundleMessageSource
import org.springframework.web.servlet.LocaleResolver
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.springframework.web.servlet.i18n.CookieLocaleResolver
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor
import java.util.Locale

@Configuration
class I18nMessageSourceConfig() : WebMvcConfigurer {

    @Bean
    fun messageSource(): MessageSource {
        val messageSource = ReloadableResourceBundleMessageSource()
        // todo yml 파일로 뺄것
        messageSource.addBasenames("classpath:i18n/message")
        messageSource.setDefaultEncoding("UTF-8")
        messageSource.setCacheSeconds(60)
        return messageSource
    }

    @Bean
    fun messageSourceAccessor(): MessageSourceAccessor {
        return MessageSourceAccessor(messageSource())
    }

    @Bean
    fun localeResolver(): LocaleResolver {
        val cookieLocaleResolver = CookieLocaleResolver("language")
        cookieLocaleResolver.setDefaultLocale(Locale.KOREAN)
//        cookieLocaleResolver.setCookieName("language")
        return cookieLocaleResolver
    }
    // = AcceptHeaderLocaleResolver().apply { defaultLocale = Locale.KOREAN }

    @Bean
    fun localeInterceptor(): LocaleChangeInterceptor = LocaleChangeInterceptor().apply {
        this.paramName = "lang"
    }

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(localeInterceptor())
    }
}
