package ru.lebedev.bank.config;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ru.lebedev.bank.converter.LongAccountConverter;
import ru.lebedev.bank.converter.LongAccountPlanConverter;
import ru.lebedev.bank.formatter.AccountDTOFormatter;


import java.util.List;

@EnableWebMvc
@Configuration
@ComponentScan("ru.lebedev")
@RequiredArgsConstructor
    public class WebConfig implements WebMvcConfigurer {
    private final LongAccountPlanConverter accountPlanConverter;
    private final LongAccountConverter accountConverter;
    private final AccountDTOFormatter accountFormatter;

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(accountPlanConverter);
        registry.addConverter(accountConverter);
        registry.addFormatter(accountFormatter);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}

