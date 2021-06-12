
package com.crud.hotels.backend.weather;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.annotation.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "cod",
    "message",
    "cnt",
    "list",
    "city"
})
@Generated("jsonschema2pojo")
public class Properties {

    @JsonProperty("cod")
    private Cod cod;
    @JsonProperty("message")
    private Message message;
    @JsonProperty("cnt")
    private Cnt cnt;
    @JsonProperty("list")
    private List list;
    @JsonProperty("city")
    private City city;

    @JsonProperty("cod")
    public Cod getCod() {
        return cod;
    }

    @JsonProperty("cod")
    public void setCod(Cod cod) {
        this.cod = cod;
    }

    public Properties withCod(Cod cod) {
        this.cod = cod;
        return this;
    }

    @JsonProperty("message")
    public Message getMessage() {
        return message;
    }

    @JsonProperty("message")
    public void setMessage(Message message) {
        this.message = message;
    }

    public Properties withMessage(Message message) {
        this.message = message;
        return this;
    }

    @JsonProperty("cnt")
    public Cnt getCnt() {
        return cnt;
    }

    @JsonProperty("cnt")
    public void setCnt(Cnt cnt) {
        this.cnt = cnt;
    }

    public Properties withCnt(Cnt cnt) {
        this.cnt = cnt;
        return this;
    }

    @JsonProperty("list")
    public List getList() {
        return list;
    }

    @JsonProperty("list")
    public void setList(List list) {
        this.list = list;
    }

    public Properties withList(List list) {
        this.list = list;
        return this;
    }

    @JsonProperty("city")
    public City getCity() {
        return city;
    }

    @JsonProperty("city")
    public void setCity(City city) {
        this.city = city;
    }

    public Properties withCity(City city) {
        this.city = city;
        return this;
    }

}
