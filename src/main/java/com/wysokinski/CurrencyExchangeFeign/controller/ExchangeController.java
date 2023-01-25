package com.wysokinski.CurrencyExchangeFeign.controller;

import com.wysokinski.CurrencyExchangeFeign.DAO.ExchangeRate;
import com.wysokinski.CurrencyExchangeFeign.service.CodeNameEnumConverter;
import com.wysokinski.CurrencyExchangeFeign.service.ExchangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;


@RestController
@RequiredArgsConstructor
public class ExchangeController {
    private final ExchangeService exchangeService;
    private final CodeNameEnumConverter codeName;
//    @RequestMapping(value = "/getExchangeRates/{stringName}",method = RequestMethod.GET)
//    public ExchangeRate getExchangeRatesList(@PathVariable("stringName") String stringName){
//        return exchangeService.getExchangesRates(codeName.convert(stringName));
//    }
    @RequestMapping(value = "/getExchangeRates/{from}/{to}/{amount}",method = RequestMethod.GET)
    public String exchange(@PathVariable("from") String from, @PathVariable("to") String to, @PathVariable("amount")BigDecimal amount){
        return exchangeService.exchangeCurrency(codeName.convert(from),codeName.convert(to),amount);
    }

}
