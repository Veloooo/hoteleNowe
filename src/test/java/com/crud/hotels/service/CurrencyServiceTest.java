package com.crud.hotels.service;

import com.crud.hotels.backend.service.CurrencyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Currency;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class CurrencyServiceTest {

    @InjectMocks
    private CurrencyService currencyService;


    @Test
    public void shouldReturnAnyhing(){
        double rate = currencyService.convertPriceToCurrency(Currency.getInstance("PLN"), Currency.getInstance("USD"));
        System.out.println(rate);
        assertThat(rate).isGreaterThan(0);
    }

    @Test
    public void shouldBeApprox3(){
        double dollarsAmount = currencyService.getValueInOtherCurrency("PLN", "USD", 10);
        System.out.println(dollarsAmount);
        assertThat(dollarsAmount).isBetween(2.5, 3.5);
    }
}
