package com.crud.hotels.backend.weather;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.annotation.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "lat",
        "lon"
})
@Generated("jsonschema2pojo")
public class Properties__7 {

    @JsonProperty("lat")
    private Lat lat;
    @JsonProperty("lon")
    private Lon lon;

    @JsonProperty("lat")
    public Lat getLat() {
        return lat;
    }

    @JsonProperty("lat")
    public void setLat(Lat lat) {
        this.lat = lat;
    }

    public Properties__7 withLat(Lat lat) {
        this.lat = lat;
        return this;
    }

    @JsonProperty("lon")
    public Lon getLon() {
        return lon;
    }

    @JsonProperty("lon")
    public void setLon(Lon lon) {
        this.lon = lon;
    }

    public Properties__7 withLon(Lon lon) {
        this.lon = lon;
        return this;
    }

}
