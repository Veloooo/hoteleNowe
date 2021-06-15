package com.crud.hotels.service;

import com.crud.hotels.backend.service.WeatherService;
import com.crud.hotels.backend.weather.Items;
import com.crud.hotels.backend.weather.WeatherInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.TimeZone;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class WeatherServiceTest {

    @InjectMocks
    private WeatherService weatherService;

    @Test
    public void shouldReturnAnything() {
        String data = weatherService.getDataPrettyPrinted(weatherService.forecastFor5Days("LONDON", "UK"));
        assertThat(data).isNotBlank();
        System.out.println(data);
    }

    @Test
    public void shouldReturnDataForCityForGivenDay() {
        List<Items> data = weatherService.getDataForCityForInterval("Krakow", "PL", LocalDate.now(), LocalDate.now().plusDays(5));
        assertThat(data.stream().allMatch(d -> LocalDateTime.ofInstant(Instant.ofEpochSecond(d.getDt()),
                TimeZone.getDefault().toZoneId()).getDayOfYear() == LocalDateTime.now().getDayOfYear())).isTrue();
    }

    @Test
    public void shouldBeFilteredByDays() {
        WeatherInfo info = weatherService.forecastFor5Days("Krakow", "PL");
        List<Items> data = weatherService.getDataForCityForDay("Krakow", "PL", LocalDateTime.now().plusDays(1).withHour(0));
        assertThat(info.getList().size() / 5).isEqualTo(data.size());
    }

}
