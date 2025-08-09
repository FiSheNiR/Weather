package ru.rybaltovskij.weather.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rybaltovskij.weather.context.RequestContextHolder;
import ru.rybaltovskij.weather.dto.LocationRequestDto;
import ru.rybaltovskij.weather.dto.OpenWeatherCityResponseDto;
import ru.rybaltovskij.weather.model.Location;
import ru.rybaltovskij.weather.model.User;
import ru.rybaltovskij.weather.repository.LocationRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LocationService {

    private final LocationRepository locationRepository;
    private final OpenWeatherService openWeatherService;
    private  final SessionService sessionService;

    public List<OpenWeatherCityResponseDto> getAllUserLocations() {
        UUID sessionId = RequestContextHolder.getSessionId();
        User user = sessionService.getUserBySession(sessionId);
        List<Location> locations = locationRepository.findAllByUserId(user);
        List<OpenWeatherCityResponseDto> weather = new ArrayList<>();
        for (Location location : locations) {
            weather.add(openWeatherService.getWeatherByCoordinates(location.getLatitude(),location.getLongitude(),location.getName()));
        }
        return weather;
    }

    @Transactional
    public void deleteLocationForUser(LocationRequestDto locationRequestDto) {
        BigDecimal latitude = locationRequestDto.getLatitude();
        BigDecimal longitude = locationRequestDto.getLongitude();
        UUID sessionId = RequestContextHolder.getSessionId();
        User user = sessionService.getUserBySession(sessionId);
        locationRepository.deleteByUserUsernameAndCoordinates(user,latitude,longitude);
    }

    @Transactional
    public void saveLocationForUser(LocationRequestDto locationRequestDto) {
        UUID sessionId = RequestContextHolder.getSessionId();
        User user = sessionService.getUserBySession(sessionId);
        Location location = Location.builder()
                .name(locationRequestDto.getName())
                .userId(user)
                .latitude(locationRequestDto.getLatitude())
                .longitude(locationRequestDto.getLongitude())
                .build();
        locationRepository.save(location);
    }
}
