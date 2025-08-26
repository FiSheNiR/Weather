package ru.rybaltovskij.weather.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.rybaltovskij.weather.dto.OpenWeatherCityResponseDto;
import ru.rybaltovskij.weather.service.LocationService;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class HomeController {

    private final LocationService locationService;

    @GetMapping
    public String home(Model model) {
        List<OpenWeatherCityResponseDto> weather = locationService.getAllUserLocations();
        model.addAttribute("weatherList", weather);
        return "index";
    }
}
