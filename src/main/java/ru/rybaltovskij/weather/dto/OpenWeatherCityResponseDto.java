package ru.rybaltovskij.weather.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenWeatherCityResponseDto {

    private BigDecimal longitude;
    private BigDecimal latitude;

    private String description;
    private String icon;

    private double temperature;
    private double feelsLikeTemperature;

    private int humidity;

    private String country;

    private String city;

    @JsonProperty("coord")
    private void unpackCoordinates(JsonNode node) {
        this.longitude = BigDecimal.valueOf(node.get("lon").asDouble());
        this.latitude = BigDecimal.valueOf(node.get("lat").asDouble());
    }

    @JsonProperty("weather")
    private void unpackWeather(JsonNode node) {
        if (node.isEmpty()) {
            throw new RuntimeException("Нет данных о погоде");
        }
        this.description = node.get(0).path("description").asText();
        this.icon = node.get(0).path("icon").asText();
    }

    @JsonProperty("main")
    private void unpackMain(JsonNode node) {
        this.temperature = node.path("temp").asDouble();
        this.feelsLikeTemperature = node.path("feels_like").asDouble();
        this.humidity = node.path("humidity").asInt();
    }

    @JsonProperty("sys")
    private void unpackSys(JsonNode node) {
        this.country = node.path("country").asText();
    }
}
