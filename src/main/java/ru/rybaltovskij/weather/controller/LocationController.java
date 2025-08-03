package ru.rybaltovskij.weather.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.rybaltovskij.weather.dto.LocationRequestDto;
import ru.rybaltovskij.weather.model.Location;
import ru.rybaltovskij.weather.service.LocationService;

@Controller
@RequestMapping("/location")
public class LocationController {

    @Autowired
    private LocationService locationService;

    @PostMapping
    public String addLocation(@ModelAttribute("location") LocationRequestDto location) {
        locationService.saveLocationForUser(location);
        return "redirect:/";
    }

    @PostMapping
    @RequestMapping("/delete")
    public String deleteLocation(@ModelAttribute("location") LocationRequestDto location) {
        locationService.deleteLocationForUser(location);
        return "redirect:/";
    }
}
