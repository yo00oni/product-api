package kr.yooni.product.common.config

import org.springframework.context.annotation.Configuration

@Configuration
class FilterConfig {

//    @Bean
//    @ConfigurationProperties(prefix = "api.filter.whitelist")
//    fun whiteList(): List<String> {
//        return ArrayList()
//    }
//    @Bean
//    fun messageConvertFilter(messageSourceAccessor: MessageSourceAccessor, whiteList: List<String>): FilterRegistrationBean<Filter> {
//        val bean: FilterRegistrationBean<Filter> = FilterRegistrationBean<Filter>()
//        bean.filter = MessageConvertFilter(messageSourceAccessor)
//        bean.order = 1
//        bean.addUrlPatterns(*whiteList.toTypedArray())
//
//        return bean
//    }
}
