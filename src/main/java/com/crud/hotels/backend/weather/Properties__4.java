package com.crud.hotels.backend.weather;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.annotation.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "speed",
        "deg"
})
@Generated("jsonschema2pojo")
public class Properties__4 {

    @JsonProperty("speed")
    private Speed speed;
    @JsonProperty("deg")
    private Deg deg;

    @JsonProperty("speed")
    public Speed getSpeed() {
        return speed;
    }

    @JsonProperty("speed")
    public void setSpeed(Speed speed) {
        this.speed = speed;
    }

    public Properties__4 withSpeed(Speed speed) {
        this.speed = speed;
        return this;
    }

    @JsonProperty("deg")
    public Deg getDeg() {
        return deg;
    }

    @JsonProperty("deg")
    public void setDeg(Deg deg) {
        this.deg = deg;
    }

    public Properties__4 withDeg(Deg deg) {
        this.deg = deg;
        return this;
    }

}
