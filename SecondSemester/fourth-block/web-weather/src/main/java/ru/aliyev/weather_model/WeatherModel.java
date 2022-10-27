package ru.aliyev.weather_model;

import java.util.HashMap;
import java.util.Map;

public abstract class WeatherModel {
    protected WeatherData data;
    public WeatherModel(WeatherData data) {
        this.data = data;
    }
    public abstract void updateDate(Map<String, String> newDate);

    public Map<String, String> getDataMap() {
        Map<String, String> dataMap = new HashMap<>() {{
            put("airTemperature", dataOrMessage(data.getAirTemperature()));
            put("realFeelTemp", dataOrMessage(data.getRealFeelTemp()));
            put("cloudCover", dataOrMessage(data.getCloudCover()));
            put("pressure", dataOrMessage(data.getPressure()));
            put("humidity", dataOrMessage(data.getHumidity()));
            put("windSpeed", dataOrMessage(data.getWindSpeed()));
        }};
        return dataMap;
    }

    private String dataOrMessage(String item) {
        if (item == null) {
            return "data unavailable";
        }
        return item;
    }
}