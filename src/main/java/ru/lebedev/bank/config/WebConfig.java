package ru.lebedev.bank.config;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//import ru.lebedev.bank.converter.LongAccountConverter;
//import ru.lebedev.bank.converter.LongAccountConverter;
import ru.lebedev.bank.converter.LongAccountPlanConverter;
//import ru.lebedev.bank.converter.LongCardPlanConverter;
//import ru.lebedev.bank.converter.StringToTypeAccountConverter;
import ru.lebedev.bank.converter.LongCheckingAccountConverter;
import ru.lebedev.bank.domain.account.dto.AccountCreateDTO;
//import ru.lebedev.bank.formatter.AccountDTOFormatter;
import ru.lebedev.bank.validator.AccountCreateDTOValidator;


import java.util.List;

@EnableWebMvc
@Configuration
@ComponentScan("ru.lebedev")
@RequiredArgsConstructor
    public class WebConfig implements WebMvcConfigurer {
    private final LongAccountPlanConverter accountPlanConverter;
    private final LongCheckingAccountConverter checkingAccountConverter;
//    private final LongAccountConverter accountConverter;
//    private final AccountDTOFormatter accountFormatter;
//    private final StringToTypeAccountConverter stringToTypeAccountConverter;
//    private final LongCardPlanConverter longCardPlanConverter;


    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(accountPlanConverter);
        registry.addConverter(checkingAccountConverter);
//        registry.addConverter(accountConverter);
//        registry.addConverter(stringToTypeAccountConverter);
//        registry.addConverter(longCardPlanConverter);
//        registry.addFormatter(accountFormatter);

    }
}

