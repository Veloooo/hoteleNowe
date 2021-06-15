package com.crud.hotels.backend.weather;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.annotation.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "pod"
})
@Generated("jsonschema2pojo")
public class Properties__5 {

    @JsonProperty("pod")
    private Pod pod;

    @JsonProperty("pod")
    public Pod getPod() {
        return pod;
    }

    @JsonProperty("pod")
    public void setPod(Pod pod) {
        this.pod = pod;
    }

    public Properties__5 withPod(Pod pod) {
        this.pod = pod;
        return this;
    }

}
