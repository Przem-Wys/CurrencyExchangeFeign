package com.wysokinski.CurrencyExchangeFeign.DAO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wysokinski.CurrencyExchangeFeign.DTO.CodeName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rate {

    private String no;
    private Date effectiveDate;
    @JsonProperty("bid")
    private BigDecimal buyPrice;
    @JsonProperty("ask")
    private BigDecimal sellPrice;

}
