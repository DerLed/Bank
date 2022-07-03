package ru.lebedev.bank.config;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ru.lebedev.bank.converter.LongAccountConverter;
import ru.lebedev.bank.converter.LongAccountPlanConverter;
import ru.lebedev.bank.formatter.AccountFormatter;

import java.util.List;


@Configuration
@ComponentScan("ru.lebedev")
@RequiredArgsConstructor
    public class WebConfig implements WebMvcConfigurer {
    private final LongAccountPlanConverter accountPlanConverter;
    private final LongAccountConverter accountConverter;
    private final AccountFormatter accountFormatter;

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(accountPlanConverter);
        registry.addConverter(accountConverter);
        registry.addFormatter(accountFormatter);
    }
}

