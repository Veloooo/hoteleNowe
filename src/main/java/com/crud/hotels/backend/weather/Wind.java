package com.crud.hotels.backend.weather;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.annotation.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "type",
        "properties"
})
@Generated("jsonschema2pojo")
public class Wind {

    @JsonProperty("speed")
    private Double speed;


    @JsonProperty("speed")
    public Double getSpeed() {
        return speed;
    }

    @JsonProperty("speed")
    public void setSpeed(Double properties) {
        this.speed = speed;
    }

    public Wind withSpeed(Double speed) {
        this.speed = speed;
        return this;
    }

}
