package ru.aliyev.openweather.model;

import ru.aliyev.weather_model.WeatherData;
import ru.aliyev.weather_model.WeatherModel;

import java.util.Map;

public class OpenWeatherModel extends WeatherModel {

    public OpenWeatherModel(WeatherData data) {
        super(data);
    }

    @Override
    public void updateDate(Map<String, String> newDate) {
        if (newDate == null) {
            return;
        }

        data = new WeatherData(
                newDate.get("airTemperature"),
                newDate.get("realFeelTemp"),
                newDate.get("cloudCover"),
                newDate.get("pressure"),
                newDate.get("humidity"),
                newDate.get("windSpeed")
        );
    }
}