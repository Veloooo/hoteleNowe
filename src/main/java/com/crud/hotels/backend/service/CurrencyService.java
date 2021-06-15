package com.crud.hotels.backend.service;

import com.posadskiy.currencyconverter.CurrencyConverter;
import com.posadskiy.currencyconverter.config.ConfigBuilder;
import org.springframework.stereotype.Service;

import java.util.Currency;

@Service
public class CurrencyService {

    public static final String OPEN_EXCHANGE_RATES = "7a1caa978bd045929026435bdfc05891";

    CurrencyConverter converter = new CurrencyConverter(
            new ConfigBuilder()
                    .openExchangeRatesApiKey(OPEN_EXCHANGE_RATES)
                    .build()
    );

    public double convertPriceToCurrency(Currency sourceCurrency, Currency targetCurrency) {
        return converter.rate(sourceCurrency.getCurrencyCode(), targetCurrency.getCurrencyCode());
    }

    public double getValueInOtherCurrency(String sourceCurrencyCode, String destinationCurrencyCode, double sourceCurrencyAmount) {
        return sourceCurrencyAmount / converter.rate(sourceCurrencyCode, destinationCurrencyCode);
    }

}
