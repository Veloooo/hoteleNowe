
package com.crud.hotels.backend.weather;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.annotation.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "all"
})
@Generated("jsonschema2pojo")
public class Properties__3 {

    @JsonProperty("all")
    private All all;

    @JsonProperty("all")
    public All getAll() {
        return all;
    }

    @JsonProperty("all")
    public void setAll(All all) {
        this.all = all;
    }

    public Properties__3 withAll(All all) {
        this.all = all;
        return this;
    }

}
