package ru.rybaltovskij.weather.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class OpenWeatherCityResponseDto {

    private double longitude;
    private double latitude;

    private String description;
    private String icon;

    private double temperature;
    private double feelsLikeTemperature;

    private int humidity;

    private String country;

    private String city;
}
