package ru.lebedev.bank.config;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ru.lebedev.bank.converter.LongAccountPlanConverter;

import java.util.List;

@Configuration
@RequiredArgsConstructor
    public class WebConfig implements WebMvcConfigurer {
    private final LongAccountPlanConverter accountPlanConverter;

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(accountPlanConverter);
    }
}

