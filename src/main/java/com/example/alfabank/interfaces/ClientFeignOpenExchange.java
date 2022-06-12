package com.example.alfabank.interfaces;

import com.example.alfabank.dto.ExchangeRatesDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "openExchange", url = "${integrations.openexchangerates-service.url}")
public interface ClientFeignOpenExchange {
    @RequestMapping(method = RequestMethod.GET, value = "/{datestr}.json?app_id={secret}")
    ExchangeRatesDto find(@PathVariable("datestr") String datestr, @PathVariable("secret") String secret);
}
