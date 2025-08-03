package ru.rybaltovskij.weather.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
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
}
