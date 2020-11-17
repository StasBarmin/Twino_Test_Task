package barmin.twino.loans.core.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.convert.converter.Converter
import java.math.BigDecimal

@Configuration
class CommonConfig {

    @Bean
    fun string2BigDecimalConverter() = Converter<String, BigDecimal> { BigDecimal(it) }
}