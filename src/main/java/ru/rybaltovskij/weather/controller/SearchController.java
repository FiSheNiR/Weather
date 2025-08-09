package ru.rybaltovskij.weather.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.rybaltovskij.weather.dto.OpenWeatherGeoResponseDto;
import ru.rybaltovskij.weather.model.Location;
import ru.rybaltovskij.weather.service.OpenWeatherService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/search")
public class SearchController {

    private final OpenWeatherService openWeatherService;

    @GetMapping
    public String search(@RequestParam("location") String location, Model model) {
        List<OpenWeatherGeoResponseDto> locations = openWeatherService.getGeoByCityName(location);
        model.addAttribute("locations", locations);
        return "search";
    }
}
