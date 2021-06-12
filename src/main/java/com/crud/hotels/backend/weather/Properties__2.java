
package com.crud.hotels.backend.weather;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.annotation.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "temp",
    "temp_min",
    "temp_max",
    "pressure",
    "sea_level",
    "grnd_level",
    "humidity",
    "temp_kf"
})
@Generated("jsonschema2pojo")
public class Properties__2 {

    @JsonProperty("temp")
    private Temp temp;
    @JsonProperty("temp_min")
    private TempMin tempMin;
    @JsonProperty("temp_max")
    private TempMax tempMax;
    @JsonProperty("pressure")
    private Pressure pressure;
    @JsonProperty("sea_level")
    private SeaLevel seaLevel;
    @JsonProperty("grnd_level")
    private GrndLevel grndLevel;
    @JsonProperty("humidity")
    private Humidity humidity;
    @JsonProperty("temp_kf")
    private TempKf tempKf;

    @JsonProperty("temp")
    public Temp getTemp() {
        return temp;
    }

    @JsonProperty("temp")
    public void setTemp(Temp temp) {
        this.temp = temp;
    }

    public Properties__2 withTemp(Temp temp) {
        this.temp = temp;
        return this;
    }

    @JsonProperty("temp_min")
    public TempMin getTempMin() {
        return tempMin;
    }

    @JsonProperty("temp_min")
    public void setTempMin(TempMin tempMin) {
        this.tempMin = tempMin;
    }

    public Properties__2 withTempMin(TempMin tempMin) {
        this.tempMin = tempMin;
        return this;
    }

    @JsonProperty("temp_max")
    public TempMax getTempMax() {
        return tempMax;
    }

    @JsonProperty("temp_max")
    public void setTempMax(TempMax tempMax) {
        this.tempMax = tempMax;
    }

    public Properties__2 withTempMax(TempMax tempMax) {
        this.tempMax = tempMax;
        return this;
    }

    @JsonProperty("pressure")
    public Pressure getPressure() {
        return pressure;
    }

    @JsonProperty("pressure")
    public void setPressure(Pressure pressure) {
        this.pressure = pressure;
    }

    public Properties__2 withPressure(Pressure pressure) {
        this.pressure = pressure;
        return this;
    }

    @JsonProperty("sea_level")
    public SeaLevel getSeaLevel() {
        return seaLevel;
    }

    @JsonProperty("sea_level")
    public void setSeaLevel(SeaLevel seaLevel) {
        this.seaLevel = seaLevel;
    }

    public Properties__2 withSeaLevel(SeaLevel seaLevel) {
        this.seaLevel = seaLevel;
        return this;
    }

    @JsonProperty("grnd_level")
    public GrndLevel getGrndLevel() {
        return grndLevel;
    }

    @JsonProperty("grnd_level")
    public void setGrndLevel(GrndLevel grndLevel) {
        this.grndLevel = grndLevel;
    }

    public Properties__2 withGrndLevel(GrndLevel grndLevel) {
        this.grndLevel = grndLevel;
        return this;
    }

    @JsonProperty("humidity")
    public Humidity getHumidity() {
        return humidity;
    }

    @JsonProperty("humidity")
    public void setHumidity(Humidity humidity) {
        this.humidity = humidity;
    }

    public Properties__2 withHumidity(Humidity humidity) {
        this.humidity = humidity;
        return this;
    }

    @JsonProperty("temp_kf")
    public TempKf getTempKf() {
        return tempKf;
    }

    @JsonProperty("temp_kf")
    public void setTempKf(TempKf tempKf) {
        this.tempKf = tempKf;
    }

    public Properties__2 withTempKf(TempKf tempKf) {
        this.tempKf = tempKf;
        return this;
    }

}
