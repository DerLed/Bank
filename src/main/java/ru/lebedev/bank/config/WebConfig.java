package ru.lebedev.bank.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ru.lebedev.bank.converter.*;
import ru.lebedev.bank.formatter.CheckingAccountDTOFormatter;


@EnableWebMvc
@Configuration
@ComponentScan("ru.lebedev")
@RequiredArgsConstructor
    public class WebConfig implements WebMvcConfigurer {
    private final LongAccountPlanConverter accountPlanConverter;
    private final LongCheckingAccountConverter checkingAccountConverter;
    private final LongAccountConverter accountConverter;
    private final CheckingAccountDTOFormatter accountFormatter;
    private final LongCardConverter longCardConverter;


    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(accountPlanConverter);
        registry.addConverter(checkingAccountConverter);
        registry.addConverter(accountConverter);
        registry.addConverter(longCardConverter);
        registry.addFormatter(accountFormatter);

    }
}

