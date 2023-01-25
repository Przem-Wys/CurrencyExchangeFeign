package com.wysokinski.CurrencyExchangeFeign.service;

import com.wysokinski.CurrencyExchangeFeign.DAO.ExchangeRate;
import com.wysokinski.CurrencyExchangeFeign.DTO.CodeName;
import com.wysokinski.CurrencyExchangeFeign.client.FeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;


@Service
@RequiredArgsConstructor
public class ExchangeService {
    private final FeignClient feignClient;
    private static final BigDecimal COMMISSION_VALUE = BigDecimal.valueOf(0.02);
public String exchangeCurrency(CodeName ownedCurrencyCode, CodeName destinationCurrencyCode, BigDecimal amount){

    if (ownedCurrencyCode == destinationCurrencyCode){
        return "Trying to convert the same currency";
    }
    if (amount.signum()!= 1){
        return "amount value is not positive";
    }
    if (destinationCurrencyCode == CodeName.PLN){

        return "Exchanged "+ amount+" "+ ownedCurrencyCode+ " to "+buyPLN(ownedCurrencyCode,commissionAmount(amount))+" PLN.";
    }else if (ownedCurrencyCode == CodeName.PLN) {
        return "Exchanged "+ amount+" "+ ownedCurrencyCode+ " to "+buyCurrent(destinationCurrencyCode,commissionAmount(amount))+" "+destinationCurrencyCode+".";
    }else {
        return "Exchanged "+ amount+" "+ ownedCurrencyCode+ " to "+buyCurrent(destinationCurrencyCode,buyPLN(ownedCurrencyCode,commissionAmount(amount)))+
                    " "+destinationCurrencyCode+".";
        }
    }
    private BigDecimal buyPLN(CodeName codeName, BigDecimal amount) {
        return amount.multiply(getCurrency(codeName).getRates().get(0).getBuyPrice()).setScale(2,RoundingMode.HALF_UP);
    }
    private BigDecimal buyCurrent (CodeName codeName, BigDecimal amount){
        return amount.divide(getCurrency(codeName).getRates().get(0).getSellPrice(),RoundingMode.HALF_UP).setScale(2,RoundingMode.HALF_UP);
    }
    private ExchangeRate getCurrency (CodeName codeName){
        return feignClient.readExchangeRates(codeName);
        }
    private BigDecimal commissionAmount(BigDecimal amount){

    return amount != null ? amount.multiply(COMMISSION_VALUE.add(BigDecimal.ONE)).setScale(2,RoundingMode.CEILING) : null;
    }
    }
