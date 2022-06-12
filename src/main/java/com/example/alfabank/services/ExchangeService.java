package com.example.alfabank.services;

import com.example.alfabank.dto.ExchangeRatesDto;
import com.example.alfabank.dto.ResponseDto;
import com.example.alfabank.interfaces.ClientFeignOpenExchange;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class ExchangeService {

    private final ClientFeignOpenExchange clientExchangeRate;
    private Base64.Encoder encoder;
    private DateFormat formater;
    private Calendar calendar;
    private ExchangeRatesDto ratesTodayDto;

    @Value("${integrations.secret.exchange_id}")
    private String secretExchange;

    @Value("${integrations.secret.gif_id}")
    private String secretGif;

    @PostConstruct
    public void init (){
        encoder = Base64.getEncoder();
        formater = new SimpleDateFormat("yyyy-MM-dd");
        calendar = Calendar.getInstance();
        ratesTodayDto=null;
    }

    public ResponseDto сurrencyIs(String rate) {
        ratesTodayDto = null;
        ResponseDto responseDto = new ResponseDto();
        responseDto
                .setCourseValue(сurrencyDifferenceBy(rate))
                .setKey(getCodeSecretGifKey())
                .setCurrencyName(rate)
                .setCurrencyExchangeRate(ratesTodayDto.getRates().get(rate));
        return responseDto;
    }

    private Double сurrencyDifferenceBy(String rate) {
        ratesTodayDto = clientExchangeRate.find(getTodayDateString(), secretExchange);
        ExchangeRatesDto ratesYesterdayDto = clientExchangeRate.find(getYesterdayDateString(), secretExchange);
        return ratesTodayDto.getRates().get(rate) - ratesYesterdayDto.getRates().get(rate);
    }

    private String getCodeSecretGifKey() {
        return new String(encoder.encode(secretGif.getBytes(StandardCharsets.UTF_8)), StandardCharsets.UTF_8);
    }

    private Date yesterday() {
        calendar.add(Calendar.DATE, -1);
        return calendar.getTime();
    }

    private String getYesterdayDateString() {
        return formater.format(yesterday());
    }

    private String getTodayDateString() {
        Date date = new Date();
        return formater.format(date);
    }
}


