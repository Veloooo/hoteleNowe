package com.crud.hotels.backend.weather;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.annotation.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "type",
        "format"
})
@Generated("jsonschema2pojo")
public class DtTxt {

    @JsonProperty("type")
    private String type;
    @JsonProperty("format")
    private String format;

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    public DtTxt withType(String type) {
        this.type = type;
        return this;
    }

    @JsonProperty("format")
    public String getFormat() {
        return format;
    }

    @JsonProperty("format")
    public void setFormat(String format) {
        this.format = format;
    }

    public DtTxt withFormat(String format) {
        this.format = format;
        return this;
    }

}
