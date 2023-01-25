package com.wysokinski.CurrencyExchangeFeign.DAO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wysokinski.CurrencyExchangeFeign.DTO.CodeName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeRate {

    private  String table;
    private String currency;
    @JsonProperty("code")
    private CodeName codeName;
    private List<Rate> rates;
}
