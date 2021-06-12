
package com.crud.hotels.backend.weather;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.annotation.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "dt",
    "main",
    "weather",
    "clouds",
    "wind",
    "sys",
    "dt_txt"
})
@Generated("jsonschema2pojo")
public class Properties__1 {

    @JsonProperty("dt")
    private Dt dt;
    @JsonProperty("main")
    private Main main;
    @JsonProperty("weather")
    private Weather weather;
    @JsonProperty("clouds")
    private Clouds clouds;
    @JsonProperty("wind")
    private Wind wind;
    @JsonProperty("sys")
    private Sys sys;
    @JsonProperty("dt_txt")
    private DtTxt dtTxt;

    @JsonProperty("dt")
    public Dt getDt() {
        return dt;
    }

    @JsonProperty("dt")
    public void setDt(Dt dt) {
        this.dt = dt;
    }

    public Properties__1 withDt(Dt dt) {
        this.dt = dt;
        return this;
    }

    @JsonProperty("main")
    public Main getMain() {
        return main;
    }

    @JsonProperty("main")
    public void setMain(Main main) {
        this.main = main;
    }

    public Properties__1 withMain(Main main) {
        this.main = main;
        return this;
    }

    @JsonProperty("weather")
    public Weather getWeather() {
        return weather;
    }

    @JsonProperty("weather")
    public void setWeather(Weather weather) {
        this.weather = weather;
    }

    public Properties__1 withWeather(Weather weather) {
        this.weather = weather;
        return this;
    }

    @JsonProperty("clouds")
    public Clouds getClouds() {
        return clouds;
    }

    @JsonProperty("clouds")
    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public Properties__1 withClouds(Clouds clouds) {
        this.clouds = clouds;
        return this;
    }

    @JsonProperty("wind")
    public Wind getWind() {
        return wind;
    }

    @JsonProperty("wind")
    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Properties__1 withWind(Wind wind) {
        this.wind = wind;
        return this;
    }

    @JsonProperty("sys")
    public Sys getSys() {
        return sys;
    }

    @JsonProperty("sys")
    public void setSys(Sys sys) {
        this.sys = sys;
    }

    public Properties__1 withSys(Sys sys) {
        this.sys = sys;
        return this;
    }

    @JsonProperty("dt_txt")
    public DtTxt getDtTxt() {
        return dtTxt;
    }

    @JsonProperty("dt_txt")
    public void setDtTxt(DtTxt dtTxt) {
        this.dtTxt = dtTxt;
    }

    public Properties__1 withDtTxt(DtTxt dtTxt) {
        this.dtTxt = dtTxt;
        return this;
    }

}
