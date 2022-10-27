package ru.aliyev.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.aliyev.openweather.model.OpenWeatherModel;
import ru.aliyev.openweather.parser.OpenWeatherParser;
import ru.aliyev.openweather.request.OpenWeatherRequest;
import ru.aliyev.controllers.StateController;
import ru.aliyev.tomorrow.model.TomorrowWeatherModel;
import ru.aliyev.tomorrow.parser.TomorrowWeatherParser;
import ru.aliyev.tomorrow.request.TomorrowWeatherRequest;
import ru.aliyev.weather_model.WeatherData;

@Configuration
public class AppConfig {
    @Bean
    public TomorrowWeatherParser tomorrowWeatherParser() {
        return new TomorrowWeatherParser();
    }

    @Bean
    public OpenWeatherParser openWeatherParser() {
        return new OpenWeatherParser();
    }

    @Bean
    public WeatherData weatherData() {
        return new WeatherData(
                null,
                null,
                null,
                null,
                null,
                null
        );
    }

    @Bean
    public TomorrowWeatherModel tomorrowWeatherModel() {
        return new TomorrowWeatherModel(weatherData());
    }

    @Bean
    public OpenWeatherModel openWeatherModel() {
        return new OpenWeatherModel(weatherData());
    }

    @Bean
    public TomorrowWeatherRequest tomorrowWeatherRequest() {
        return new TomorrowWeatherRequest();
    }

    @Bean
    public OpenWeatherRequest openWeatherRequest() {
        return new OpenWeatherRequest();
    }

    @Bean
    public StateController tomorrowStateController() {
        return new StateController(tomorrowWeatherRequest(), tomorrowWeatherModel(), tomorrowWeatherParser());
    }

    @Bean
    public StateController openWeatherStateController() {
        return new StateController(openWeatherRequest(), openWeatherModel(), openWeatherParser());
    }
}
