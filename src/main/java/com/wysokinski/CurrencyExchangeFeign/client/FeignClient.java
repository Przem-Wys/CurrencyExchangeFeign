package com.wysokinski.CurrencyExchangeFeign.client;

import com.wysokinski.CurrencyExchangeFeign.DAO.ExchangeRate;
import com.wysokinski.CurrencyExchangeFeign.DTO.CodeName;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Component
@org.springframework.cloud.openfeign.FeignClient(url = "${api.nbp.pl.exchangeRates}", name = "exchange")
public interface FeignClient {
    @RequestMapping(method = RequestMethod.GET,value = "{codeName}")
    ExchangeRate readExchangeRates(@PathVariable CodeName codeName);

}
