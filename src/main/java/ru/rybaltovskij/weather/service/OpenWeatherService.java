package ru.rybaltovskij.weather.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ru.rybaltovskij.weather.dto.OpenWeatherCityResponseDto;
import ru.rybaltovskij.weather.dto.OpenWeatherGeoResponseDto;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Service
public class OpenWeatherService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Value("${api.key}")
    private String apiKey;

    public List<OpenWeatherGeoResponseDto> getGeoByCityName(String city) {
        String url = UriComponentsBuilder
                .fromUriString("http://api.openweathermap.org/geo/1.0/direct")
                .queryParam("q", city)
                .queryParam("limit", 5)
                .queryParam("appid", apiKey)
                .toUriString();
        String jsonResponse = restTemplate.getForObject(url, String.class);
        return parseOpenWeatherGeoResponse(jsonResponse);
    }

    public OpenWeatherCityResponseDto getWeatherByCoordinates(BigDecimal latitude, BigDecimal longitude, String city) {
        String url = UriComponentsBuilder
                .fromUriString("https://api.openweathermap.org/data/2.5/weather")
                .queryParam("lat", latitude)
                .queryParam("lon", longitude)
                .queryParam("appid", apiKey)
                .queryParam("units", "metric")
                .toUriString();
        String jsonResponse = restTemplate.getForObject(url, String.class);
        OpenWeatherCityResponseDto openWeatherGeoResponseDto = parseOpenWeatherCityResponseWithMapper(jsonResponse);
        openWeatherGeoResponseDto.setCity(city);
        return openWeatherGeoResponseDto;
    }

    private List<OpenWeatherGeoResponseDto> parseOpenWeatherGeoResponse(String response) {
        try {
            return Arrays.asList(objectMapper.readValue(response, OpenWeatherGeoResponseDto[].class));
        } catch (Exception e) {
            throw new RuntimeException("Ошибка десериализации", e);
        }
    }

    private OpenWeatherCityResponseDto parseOpenWeatherCityResponseWithMapper(String response) {
        try {
            return objectMapper.readValue(response, OpenWeatherCityResponseDto.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
