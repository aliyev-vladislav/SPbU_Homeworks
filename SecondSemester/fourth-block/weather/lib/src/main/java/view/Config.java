package view;

import controller.StateController;
import openweather.model.OpenWeatherModel;
import openweather.parser.OpenWeatherParser;
import openweather.request.OpenWeatherRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tomorrow.model.TomorrowWeatherModel;
import tomorrow.parser.TomorrowWeatherParser;
import tomorrow.request.TomorrowWeatherRequest;
import weather_model.WeatherData;

import java.util.ArrayList;

@Configuration
public class Config {
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
        return new TomorrowWeatherRequest(new ArrayList<>() {{
            add("airTemperature");
            add("realFeelTemp");
            add("cloudCover");
            add("pressure");
            add("humidity");
            add("windSpeed");
        }});
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

    @Bean
    public WeatherView tomorrowView() {
        return new WeatherView(tomorrowWeatherModel());
    }

    @Bean
    public WeatherView openWeatherView() {
        return new WeatherView(openWeatherModel());
    }
}
