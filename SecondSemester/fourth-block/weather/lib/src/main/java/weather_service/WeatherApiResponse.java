package weather_service;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;

public class WeatherApiResponse {
    public String response(WeatherApiRequest request) {
        HttpClient client = HttpClient.newHttpClient();
        try {
            HttpResponse<String> response = client.send(
                    request.createRequest(),
                    HttpResponse.BodyHandlers.ofString()
            );
            return response.body();
        } catch (IOException | InterruptedException exc) {
            return null;
        }
    }
}
