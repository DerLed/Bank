package ru.lebedev.bank.converter;

import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.lebedev.bank.domain.card.CardService;
import ru.lebedev.bank.domain.card.dto.CardDTO;

@Component
@RequiredArgsConstructor
public class LongCardConverter implements Converter<String, CardDTO> {
    private final CardService cardService;

    @Override
    public CardDTO convert(String source) {
        return cardService.findById(Long.parseLong(source)).orElseThrow();
    }

}
