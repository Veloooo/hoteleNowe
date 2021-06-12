
package com.crud.hotels.backend.weather;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.annotation.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "name",
    "coord",
    "country",
    "population",
    "timezone",
    "sunrise",
    "sunset"
})
@Generated("jsonschema2pojo")
public class Properties__6 {

    @JsonProperty("id")
    private Id id;
    @JsonProperty("name")
    private Name name;
    @JsonProperty("coord")
    private Coord coord;
    @JsonProperty("country")
    private Country country;
    @JsonProperty("population")
    private Population population;
    @JsonProperty("timezone")
    private Timezone timezone;
    @JsonProperty("sunrise")
    private Sunrise sunrise;
    @JsonProperty("sunset")
    private Sunset sunset;

    @JsonProperty("id")
    public Id getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Id id) {
        this.id = id;
    }

    public Properties__6 withId(Id id) {
        this.id = id;
        return this;
    }

    @JsonProperty("name")
    public Name getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(Name name) {
        this.name = name;
    }

    public Properties__6 withName(Name name) {
        this.name = name;
        return this;
    }

    @JsonProperty("coord")
    public Coord getCoord() {
        return coord;
    }

    @JsonProperty("coord")
    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public Properties__6 withCoord(Coord coord) {
        this.coord = coord;
        return this;
    }

    @JsonProperty("country")
    public Country getCountry() {
        return country;
    }

    @JsonProperty("country")
    public void setCountry(Country country) {
        this.country = country;
    }

    public Properties__6 withCountry(Country country) {
        this.country = country;
        return this;
    }

    @JsonProperty("population")
    public Population getPopulation() {
        return population;
    }

    @JsonProperty("population")
    public void setPopulation(Population population) {
        this.population = population;
    }

    public Properties__6 withPopulation(Population population) {
        this.population = population;
        return this;
    }

    @JsonProperty("timezone")
    public Timezone getTimezone() {
        return timezone;
    }

    @JsonProperty("timezone")
    public void setTimezone(Timezone timezone) {
        this.timezone = timezone;
    }

    public Properties__6 withTimezone(Timezone timezone) {
        this.timezone = timezone;
        return this;
    }

    @JsonProperty("sunrise")
    public Sunrise getSunrise() {
        return sunrise;
    }

    @JsonProperty("sunrise")
    public void setSunrise(Sunrise sunrise) {
        this.sunrise = sunrise;
    }

    public Properties__6 withSunrise(Sunrise sunrise) {
        this.sunrise = sunrise;
        return this;
    }

    @JsonProperty("sunset")
    public Sunset getSunset() {
        return sunset;
    }

    @JsonProperty("sunset")
    public void setSunset(Sunset sunset) {
        this.sunset = sunset;
    }

    public Properties__6 withSunset(Sunset sunset) {
        this.sunset = sunset;
        return this;
    }

}
