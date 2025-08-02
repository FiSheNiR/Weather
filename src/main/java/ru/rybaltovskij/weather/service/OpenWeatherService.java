package ru.rybaltovskij.weather.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.rybaltovskij.weather.dto.OpenWeatherCityResponseDto;
import ru.rybaltovskij.weather.dto.OpenWeatherGeoResponseDto;

import java.util.Arrays;
import java.util.List;

@Service
public class OpenWeatherService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    private final String apiKey = "8040a69cb01b85428584fe1e808d4aa6";

    public void getGeoByCityName(String city) {
        String url = "http://api.openweathermap.org/geo/1.0/direct?q=" + city + "&limit=5&appid=" + apiKey;
        String jsonResponse = restTemplate.getForObject(url, String.class);
        System.out.println(jsonResponse);
        List<OpenWeatherGeoResponseDto> openWeatherGeoResponseDto = parseOpenWeatherGeoResponse(jsonResponse);
        System.out.println(openWeatherGeoResponseDto);
    }

    public void getWeatherByCoordinates(String latitude, String longitude) {
        String url = "https://api.openweathermap.org/data/2.5/weather?lat=" + latitude + "&lon=" + longitude + "&appid=" + apiKey + "&units=metric";
        String jsonResponse = restTemplate.getForObject(url, String.class);
        System.out.println(jsonResponse);
        OpenWeatherCityResponseDto openWeatherCityResponseDto = parseOpenWeatherCityResponse(jsonResponse);
        System.out.println(openWeatherCityResponseDto);
    }

    private List<OpenWeatherGeoResponseDto> parseOpenWeatherGeoResponse(String response) {
        try {
            return Arrays.asList(objectMapper.readValue(response, OpenWeatherGeoResponseDto[].class));
        } catch (Exception e) {
            throw new RuntimeException("Ошибка десериализации", e);
        }
    }

    private OpenWeatherCityResponseDto parseOpenWeatherCityResponse(String response) {
        JsonNode root = null;
        try {
            root = objectMapper.readTree(response);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Ошибка парсинга JSON", e);
        }

        double longitude = root.path("coord").path("lon").asDouble();
        double latitude = root.path("coord").path("lat").asDouble();

        JsonNode weatherNode = root.path("weather");
        if (weatherNode.isEmpty()) {
            throw new RuntimeException("Нет данных о погоде");
        }
        String description = weatherNode.get(0).path("description").asText();
        String icon = weatherNode.get(0).path("icon").asText();

        double temperature = root.path("main").path("temp").asDouble();
        double feelsLike = root.path("main").path("feels_like").asDouble();
        int humidity = root.path("main").path("humidity").asInt();
        String country = root.path("sys").path("country").asText();
        String city = root.path("name").asText();

        return OpenWeatherCityResponseDto.builder()
                .longitude(longitude)
                .latitude(latitude)
                .description(description)
                .icon(icon)
                .temperature(temperature)
                .feelsLikeTemperature(feelsLike)
                .humidity(humidity)
                .country(country)
                .city(city)
                .build();
    }
}
