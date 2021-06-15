package com.crud.hotels.backend.weather;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.annotation.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "type",
        "items"
})
@Generated("jsonschema2pojo")
public class Weather {

    @JsonProperty("id")
    private String id;

    @JsonProperty("main")
    private String main;

    @JsonProperty("description")
    private String description;

    @JsonProperty("icon")
    private String icon;


    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    public Weather withId(String id) {
        this.id = id;
        return this;
    }

}
