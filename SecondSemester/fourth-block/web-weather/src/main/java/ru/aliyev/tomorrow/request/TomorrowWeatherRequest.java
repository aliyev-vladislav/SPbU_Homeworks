package ru.aliyev.tomorrow.request;

import ru.aliyev.weather_service.WeatherApiRequest;

import java.net.URI;
import java.net.http.HttpRequest;
import java.time.Duration;

public class TomorrowWeatherRequest implements WeatherApiRequest {
    private static final String TOMORROW_WEATHER_URL =
            "https://api.tomorrow.io/v4/timelines" +
                    "?location=59.881225,29.906777" +
                    "&fields=temperature," +
                    "temperatureApparent," +
                    "cloudCover," +
                    "pressureSurfaceLevel," +
                    "humidity," +
                    "windSpeed" +
                    "&timesteps=current";
    private static final String TOMORROW_WEATHER_API_KEY = "ZWZZSak7eRZITQ6eMS2wcP9lLTYBAiI2";
    private static final String TOMORROW_WEATHER_UNIT = "metric";
//    private final ArrayList<String> params;

//    public TomorrowWeatherRequest(ArrayList<String> params) {
//        this.params = params;
//    }
    @Override
    public HttpRequest createRequest() {
        return HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(
                        TOMORROW_WEATHER_URL
                                + "&units=" + TOMORROW_WEATHER_UNIT
                                + "&apikey=" + TOMORROW_WEATHER_API_KEY
                ))
                .timeout(Duration.ofSeconds(10))
                .build();
    }
}