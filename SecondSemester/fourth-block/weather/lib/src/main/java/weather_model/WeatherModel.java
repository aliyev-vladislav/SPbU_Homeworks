package weather_model;

import java.util.Map;

public abstract class WeatherModel {
    protected WeatherData data;
    public WeatherModel(WeatherData data) {
        this.data = data;
    }
    public abstract void updateDate(Map<String, String> newDate);

    public WeatherData getData() {
        return data;
    }
}

