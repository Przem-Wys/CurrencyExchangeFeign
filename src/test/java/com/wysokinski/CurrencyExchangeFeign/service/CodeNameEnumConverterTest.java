package com.wysokinski.CurrencyExchangeFeign.service;

import com.wysokinski.CurrencyExchangeFeign.DTO.CodeName;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CodeNameEnumConverterTest {
    @Autowired
    CodeNameEnumConverter codeNameEnumConverter;
    @Test
    void convertCodeName(){
        Assertions.assertEquals(CodeName.EUR,codeNameEnumConverter.convert("eur"));
    }
    @Test
    void convertShouldReturnException(){
        Assertions.assertThrows(IllegalArgumentException.class,() ->codeNameEnumConverter.convert("eru"));
    }

}