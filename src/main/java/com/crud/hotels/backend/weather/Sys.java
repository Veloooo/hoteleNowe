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
public class Sys {

    @JsonProperty("type")
    private String type;
    @JsonProperty("properties")
    private Properties__5 properties;

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    public Sys withType(String type) {
        this.type = type;
        return this;
    }

    @JsonProperty("properties")
    public Properties__5 getProperties() {
        return properties;
    }

    @JsonProperty("properties")
    public void setProperties(Properties__5 properties) {
        this.properties = properties;
    }

    public Sys withProperties(Properties__5 properties) {
        this.properties = properties;
        return this;
    }

}
