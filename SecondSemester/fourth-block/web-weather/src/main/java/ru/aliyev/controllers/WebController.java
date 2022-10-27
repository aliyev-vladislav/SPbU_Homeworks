package ru.aliyev.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/weather")
public class WebController {
    private StateController tomorrowStateController;
    private StateController openWeatherStateController;

    public WebController(StateController tomorrowStateController,
                         StateController openWeatherStateController) {
        this.tomorrowStateController = tomorrowStateController;
        this.openWeatherStateController = openWeatherStateController;
    }

    @GetMapping
    public String showMainPage() {
        return "weather";
    }

    @GetMapping("/tomorrow")
    public String showTomorrowWeather(Model model) {
        tomorrowStateController.updateState();

        model.addAllAttributes(tomorrowStateController.getModel().getDataMap());

        return "tomorrow";
    }

    @GetMapping("/openweather")
    public String showOpenWeather(Model model) {
        openWeatherStateController.updateState();

        model.addAllAttributes(openWeatherStateController.getModel().getDataMap());

        return "openweather";
    }
}
