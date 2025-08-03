package ru.rybaltovskij.weather.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenWeatherGeoResponseDto {
    @JsonProperty("name")
    private String cityName;
    @JsonProperty("lat")
    private BigDecimal latitude;
    @JsonProperty("lon")
    private BigDecimal longitude;
    @JsonProperty("country")
    private String country;
    @JsonProperty("state")
    private String state;
}
