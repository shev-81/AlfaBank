package com.example.alfabank;

import com.example.alfabank.dto.ExchangeRatesDto;
import com.example.alfabank.dto.ResponseDto;
import com.example.alfabank.interfaces.ClientFeignOpenExchange;
import com.example.alfabank.services.ExchangeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.LinkedHashMap;

@SpringBootTest
public class ExchangeServiceTest {

    @Autowired
    private ExchangeService excService;

    @Value("${integrations.secret.gif_id}")
    String secretGif;

    @Mock
    private ClientFeignOpenExchange clientExchangeRate;;

    @Test
    public void testExchange() {
        ExchangeRatesDto exchangeRatesDto = new ExchangeRatesDto();
        LinkedHashMap <String, Double> map = new LinkedHashMap<>();
        map.put("RUB", 61.02);
        map.put("EUR", 0.93);
        exchangeRatesDto.setRates(map);
        Base64.Encoder encoder = Base64.getEncoder();
        String secretGifKey = new String(encoder.encode(secretGif.getBytes(StandardCharsets.UTF_8)), StandardCharsets.UTF_8);

        Mockito.doReturn(exchangeRatesDto).when(clientExchangeRate).find("2022-06-08","secret");

        ResponseDto rates = excService.сurrencyIs("RUB");
        Assertions.assertTrue(rates.getKey().equals(secretGifKey));
    }
}
