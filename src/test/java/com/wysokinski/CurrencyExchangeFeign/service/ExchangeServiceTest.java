package com.wysokinski.CurrencyExchangeFeign.service;

import com.wysokinski.CurrencyExchangeFeign.DAO.ExchangeRate;
import com.wysokinski.CurrencyExchangeFeign.DAO.Rate;
import com.wysokinski.CurrencyExchangeFeign.DTO.CodeName;
import com.wysokinski.CurrencyExchangeFeign.client.FeignClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.Date;

@SpringBootTest
class ExchangeServiceTest {
    @Autowired
    private ExchangeService exchangeService;
    @MockBean
    private FeignClient feignClient;

    @Test
    void correctValue() {
        Mockito.when(feignClient.readExchangeRates(CodeName.EUR))
                .thenReturn(new ExchangeRate("table_name",
                        "currency_name",
                        CodeName.EUR,
                        Collections.singletonList(new Rate("rate_number",
                                new Date(),
                                BigDecimal.valueOf(1.5),
                                BigDecimal.valueOf(0.6)))));

        Mockito.when(feignClient.readExchangeRates(CodeName.USD))
                .thenReturn(new ExchangeRate("table_name",
                        "currency_name",
                        CodeName.USD,
                        Collections.singletonList(new Rate("rate_number",
                                new Date(),
                                BigDecimal.valueOf(1.1),
                                BigDecimal.valueOf(1.2)))));

        Assertions.assertEquals(new BigDecimal(12.25).setScale(2, RoundingMode.HALF_UP), exchangeService
                .exchangeCurrency(CodeName.EUR, CodeName.USD, BigDecimal.TEN));
    }

    @Test
    void shouldReturnNullPointerException() {
        Assertions.assertThrows(NullPointerException.class, () -> exchangeService.exchangeCurrency(CodeName.CAD, CodeName.CAD, BigDecimal.TEN));
    }

    @Test
    void shouldReturnArithmeticalException() {
        Assertions.assertThrows(ArithmeticException.class, () -> exchangeService.exchangeCurrency(CodeName.CAD, CodeName.AUD, BigDecimal.ZERO));
    }

    @Test
    void exchangeToPLN() {
        Mockito.when(feignClient.readExchangeRates(CodeName.EUR))
                .thenReturn(new ExchangeRate("table_name",
                        "currency_name",
                        CodeName.EUR,
                        Collections.singletonList(new Rate("rate_number",
                                new Date(),
                                BigDecimal.valueOf(1.5),
                                BigDecimal.valueOf(1)))));
        Assertions.assertEquals(new BigDecimal(14.70).setScale(2, RoundingMode.HALF_UP)
                , exchangeService.exchangeCurrency(CodeName.EUR, CodeName.PLN, BigDecimal.TEN));

    }

    @Test
    void exchangeFromPLN() {
        Mockito.when(feignClient.readExchangeRates(CodeName.EUR))
                .thenReturn(new ExchangeRate("table_name",
                        "currency_name",
                        CodeName.EUR,
                        Collections.singletonList(new Rate("rate_number",
                                new Date(),
                                BigDecimal.valueOf(1.5),
                                BigDecimal.valueOf(1)))));
        Assertions.assertEquals(new BigDecimal(9.80).setScale(2, RoundingMode.HALF_UP)
                , exchangeService.exchangeCurrency(CodeName.PLN, CodeName.EUR, BigDecimal.TEN));
    }
}