package com.wysokinski.CurrencyExchangeFeign.service;

import com.wysokinski.CurrencyExchangeFeign.DTO.CodeName;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CodeNameEnumConverter implements Converter<String, CodeName> {
    @Override
    public CodeName convert(String source) {
        return CodeName.valueOf(CodeName.class,source.toUpperCase());
    }
}
