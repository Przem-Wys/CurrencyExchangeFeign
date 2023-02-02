package com.wysokinski.CurrencyExchangeFeign.service;

import com.wysokinski.CurrencyExchangeFeign.DTO.CodeName;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;



class CodeNameEnumConverterTest {

    private CodeNameEnumConverter codeNameEnumConverter = new CodeNameEnumConverter();

    @Test
    void convertCodeName() {
        Assertions.assertEquals(CodeName.EUR, codeNameEnumConverter.convert("eur"));
    }

    @Test
    void convertShouldReturnException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> codeNameEnumConverter.convert("eru"));
    }

}