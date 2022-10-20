package openweather.request;

import weather_service.WeatherApiRequest;

import java.net.URI;
import java.net.http.HttpRequest;
import java.time.Duration;

public class OpenWeatherRequest implements WeatherApiRequest {
    private static final String OPENWEATHER_URL = "https://api.openweathermap.org/data/2.5/weather";
    private static final String OPENWEATHER_API_KEY = "3e2c884ed21f9ad0ec19f4ec1685606a";
    private static final String OPENWEATHER_UNITS = "metric";

    @Override
    public HttpRequest createRequest() {
        return HttpRequest.newBuilder()
                .uri(URI.create(
                        OPENWEATHER_URL
                                + "?q=" + CITY_NAME
                                + "&appid=" + OPENWEATHER_API_KEY
                                + "&units=" + OPENWEATHER_UNITS
                ))
                .GET()
                .timeout(Duration.ofSeconds(10))
                .build();
    }
}
