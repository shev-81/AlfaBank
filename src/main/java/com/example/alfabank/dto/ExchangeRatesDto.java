package com.example.alfabank.dto;

import java.util.LinkedHashMap;

public class ExchangeRatesDto {
    LinkedHashMap<String,Double> rates;

    public ExchangeRatesDto() {
    }

    public LinkedHashMap<String, Double> getRates() {
        return rates;
    }

    public void setRates(LinkedHashMap<String, Double> rates) {
        this.rates = rates;
    }

}
