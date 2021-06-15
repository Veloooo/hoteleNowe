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
public class List {

    @JsonProperty("type")
    private String type;
    @JsonProperty("items")
    private Items items;

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    public List withType(String type) {
        this.type = type;
        return this;
    }

    @JsonProperty("items")
    public Items getItems() {
        return items;
    }

    @JsonProperty("items")
    public void setItems(Items items) {
        this.items = items;
    }

    public List withItems(Items items) {
        this.items = items;
        return this;
    }

}
