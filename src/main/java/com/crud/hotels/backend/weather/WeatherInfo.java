
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
public class WeatherInfo {

    @JsonProperty("cod")
    private String cod;
    @JsonProperty("message")
    private String message;
    @JsonProperty("type")
    private String type;
    @JsonProperty("cnt")
    private Integer cnt;
    @JsonProperty("list")
    private List<Items> list;
    @JsonProperty("properties")
    private Properties properties;

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    public WeatherInfo withType(String type) {
        this.type = type;
        return this;
    }

    @JsonProperty("properties")
    public Properties getProperties() {
        return properties;
    }

    @JsonProperty("properties")
    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public WeatherInfo withProperties(Properties properties) {
        this.properties = properties;
        return this;
    }

    @JsonProperty("cod")
    public String getCod() {
        return cod;
    }

    @JsonProperty("cod")
    public void setCod(String cod) {
        this.cod = cod;
    }

    public WeatherInfo withCod(String cod) {
        this.cod = cod;
        return this;
    }
    @JsonProperty("message")
    public String getMessage() {
        return message;
    }

    @JsonProperty("message")
    public void setMessage(String message) {
        this.message = message;
    }

    public WeatherInfo withMessage(String message) {
        this.message = message;
        return this;
    }

    @JsonProperty("cnt")
    public Integer getCnt() {
        return cnt;
    }

    @JsonProperty("cnt")
    public void setCnt(Integer cnt) {
        this.cnt = cnt;
    }

    public WeatherInfo withCnt(Integer cnt) {
        this.cnt = cnt;
        return this;
    }

    @JsonProperty("list")
    public List<Items> getList() {
        return list;
    }

    @JsonProperty("list")
    public void setList(List<Items> list) {
        this.list = list;
    }

    public WeatherInfo withList(List<Items> list) {
        this.list = list;
        return this;
    }

}
