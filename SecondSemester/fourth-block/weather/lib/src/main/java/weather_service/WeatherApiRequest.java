package weather_service;

import java.net.http.HttpRequest;

public interface WeatherApiRequest {
    public final String CITY_NAME = "Peterhof";
    public HttpRequest createRequest();
}
