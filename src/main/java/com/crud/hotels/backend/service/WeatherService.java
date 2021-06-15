package com.crud.hotels.backend.service;

import com.crud.hotels.backend.weather.Items;
import com.crud.hotels.backend.weather.WeatherInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.neovisionaries.i18n.CountryCode;
import lombok.Getter;
import lombok.Setter;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import javax.naming.ldap.HasControls;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static com.neovisionaries.i18n.CountryCode.findByName;

@Service
@Getter
@Setter
@ConfigurationProperties(prefix = "weather.service")
public class WeatherService {

    OkHttpClient client = new OkHttpClient();
    ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    public static final String HEADER_API_KEY = "x-rapidapi-key";
    public static final String HEADER_API_KEY_VALUE = "06694d39e1mshd44c23226bb4bd3p1c42b0jsnd2a23b9b94c8";
    public static final String HEADER_API_HOST = "x-rapidapi-host";
    public static final String HEADER_API_HOST_VALUE = "community-open-weather-map.p.rapidapi.com";
    public static final String API_FORECAST_URL = "https://community-open-weather-map.p.rapidapi.com/forecast?q=";


    private Map<String, WeatherInfo> cachedTempInfo = new HashMap<>();


    public WeatherInfo forecastFor5Days(String city, String countryCode) {
        if(cachedTempInfo.containsKey(city))
            return cachedTempInfo.get(city);

        Request request = new Request.Builder()
                .url(API_FORECAST_URL + city + "," + countryCode)
                .get()
                .addHeader(HEADER_API_HOST, HEADER_API_HOST_VALUE)
                .addHeader(HEADER_API_KEY, HEADER_API_KEY_VALUE)
                .build();

        WeatherInfo info = null;

        try {
            info = execute(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Objects.requireNonNull(info).setList(info.getList().stream()
                .map(this::mapToCelcius)
                .collect(Collectors.toList()));

        cachedTempInfo.put(city, info);
        return info;
    }

    private Items mapToCelcius(Items items) {
        items.getMain().setFeelsLike(items.getMain().getFeelsLike() - 273.1);
        items.getMain().setTemp(items.getMain().getTemp() - 273.1);
        items.getMain().setTempMax(items.getMain().getTempMax() - 273.1);
        items.getMain().setTempMin(items.getMain().getTempMin() - 273.1);
        return items;
    }

    public List<Items> getDataForCityForInterval(String city, String countryCode, LocalDate dateFrom, LocalDate dateTo) {
        WeatherInfo info = forecastFor5Days(city, countryCode);

        return info.getList().stream()
                .filter(w ->
                        LocalDate.ofEpochDay(Instant.ofEpochSecond(w.getDt()).getEpochSecond()).getDayOfYear() >= dateFrom.getDayOfYear() &&
                        LocalDate.ofEpochDay(Instant.ofEpochSecond(w.getDt()).getEpochSecond()).getDayOfYear() <= dateTo.getDayOfYear()
                )
                .collect(Collectors.toList());
    }

    public Double getAverageTemperatureForRoomInGivenDates(String country, String city, LocalDate dateFromValue, LocalDate dateToValue) {
        return getDataForCityForInterval(city, CountryCode.findByName(country).get(0).getName() , dateFromValue, dateToValue).stream()
                .mapToDouble(sum -> sum.getMain().getTemp())
                .summaryStatistics().getAverage();
    }


    public String getDataPrettyPrinted(WeatherInfo info) {
        String objectPrettyPrinted = null;
        try {
            objectPrettyPrinted = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(info);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return objectPrettyPrinted;
    }

    private WeatherInfo execute(Request request) throws IOException {
        return mapper.readValue(client.newCall(request).execute().body().string(), WeatherInfo.class);
    }

}
