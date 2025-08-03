package ru.rybaltovskij.weather.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LocationRequestDto {
    private String name;
    private BigDecimal longitude;
    private BigDecimal latitude;
}
