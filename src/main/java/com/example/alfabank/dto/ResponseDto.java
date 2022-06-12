package com.example.alfabank.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Модель объекта ответа")
public class ResponseDto {

    @Schema(description = "Сокращенное название валюты", required = true, example = "RUB")
    private String currencyName;

    @Schema(description = "Курс обмена валюты по отношению к доллару", required = true, example = "61.2232345")
    private Double currencyExchangeRate;

    @Schema(description = "Значение разницы валюты от текущей даты и даты - 1 день", required = true, example = "0.2232345")
    private Double courseValue;

    @Schema(description = "Зашифрованный base64 алгоритмом ключ для доступа к сервису картинок", required = true, example = "gjklLJK09sdklsJH987sfbkdfJNjdf")
    private String key;

    public ResponseDto() {
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public ResponseDto setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
        return this;
    }

    public Double getCurrencyExchangeRate() {
        return currencyExchangeRate;
    }

    public ResponseDto setCurrencyExchangeRate(Double currencyExchangeRate) {
        this.currencyExchangeRate = currencyExchangeRate;
        return this;
    }

    public Double getCourseValue() {
        return courseValue;
    }

    public ResponseDto setCourseValue(Double courseValue) {
        this.courseValue = courseValue;
        return this;
    }

    public String getKey() {
        return key;
    }

    public ResponseDto setKey(String key) {
        this.key = key;
        return this;
    }
}
