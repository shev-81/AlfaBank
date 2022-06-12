package com.example.alfabank;

import com.example.alfabank.dto.ExchangeRatesDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class FullServerRunTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void fullRestTest() {
        ExchangeRatesDto сurrencies = restTemplate.getForObject("/api/v1/RUB", ExchangeRatesDto.class);
        assertThat(сurrencies).isNotNull();
    }
}
