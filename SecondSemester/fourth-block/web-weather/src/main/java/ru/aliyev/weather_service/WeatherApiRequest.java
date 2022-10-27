package ru.aliyev.weather_service;

import java.net.http.HttpRequest;

public interface WeatherApiRequest {
    String CITY_NAME = "Peterhof";
    HttpRequest createRequest();
}