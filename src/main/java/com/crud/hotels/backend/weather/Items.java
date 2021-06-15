package com.crud.hotels.backend.weather;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.annotation.Generated;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "type",
        "properties"
})
@Generated("jsonschema2pojo")
public class Items {

    @JsonProperty("type")
    private String type;
    @JsonProperty("properties")
    private Properties__1 properties;
    @JsonProperty("dt")
    private Long dt;

    @JsonProperty("main")
    private Main main;
    @JsonProperty("weather")
    private List<Weather> weather;
    @JsonProperty("clouds")
    private Clouds clouds;
    @JsonProperty("wind")
    private Wind wind;
    @JsonProperty("visibility")
    private Long visibility;
    @JsonProperty("pop")
    private Long pop;
    @JsonProperty("sys")
    private Sys sys;
    @JsonProperty("dt_txt")
    private String dtTxt;

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    public Items withType(String type) {
        this.type = type;
        return this;
    }

    @JsonProperty("properties")
    public Properties__1 getProperties() {
        return properties;
    }

    @JsonProperty("properties")
    public void setProperties(Properties__1 properties) {
        this.properties = properties;
    }

    public Items withProperties(Properties__1 properties) {
        this.properties = properties;
        return this;
    }

    @JsonProperty("dt")
    public Long getDt() {
        return dt;
    }

    @JsonProperty("dt")
    public void setDt(Long dt) {
        this.dt = dt;
    }

    public Items withDt(Long dt) {
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

    public Items withDt(Main main) {
        this.main = main;
        return this;
    }


    @JsonProperty("weather")
    public List<Weather> getWeather() {
        return weather;
    }

    @JsonProperty("weather")
    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public Items withWeather(List<Weather> weather) {
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

    public Items withClouds(Clouds clouds) {
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

    public Items withWind(Wind wind) {
        this.wind = wind;
        return this;
    }


    @JsonProperty("visibility")
    public Long getVisibility() {
        return visibility;
    }

    @JsonProperty("visibility")
    public void setVisibility(Long visibility) {
        this.visibility = visibility;
    }

    public Items withVisibility(Long visibility) {
        this.visibility = visibility;
        return this;
    }


    @JsonProperty("pop")
    public Long getPop() {
        return pop;
    }

    @JsonProperty("pop")
    public void setPop(Long pop) {
        this.pop = pop;
    }

    public Items withPop(Long pop) {
        this.pop = pop;
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

    public Items withSys(Sys sys) {
        this.sys = sys;
        return this;
    }


    @JsonProperty("dt_txt")
    public String getDtTxt() {
        return dtTxt;
    }

    @JsonProperty("dt_txt")
    public void setDtTxt(String dtTxt) {
        this.dtTxt = dtTxt;
    }

    public Items withDt(String dtTxt) {
        this.dtTxt = dtTxt;
        return this;
    }
}
