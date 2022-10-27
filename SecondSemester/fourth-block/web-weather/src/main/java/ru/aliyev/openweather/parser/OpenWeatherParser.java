package ru.aliyev.openweather.parser;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import ru.aliyev.weather_parser.JsonParser;

import java.util.HashMap;
import java.util.Map;

public class OpenWeatherParser implements JsonParser {

    @Override
    public Map<String, String> parse(String json) throws ParseException {
        if (json == null) {
            return null;
        }
        JSONObject jsonObj = (JSONObject) new JSONParser().parse(json);
        return new HashMap<>() {{
            put("airTemperature", convertJson("main.temp", jsonObj));
            put("realFeelTemp", convertJson("main.feels_like", jsonObj));
            put("cloudCover", convertJson("clouds.all", jsonObj));
            put("pressure", convertJson("main.pressure", jsonObj));
            put("humidity", convertJson("main.humidity", jsonObj));
            put("windSpeed", convertJson("wind.speed", jsonObj));
        }};
    }

    private String convertJson(String typeData, JSONObject jsonObject) {
        String[] splitParameters = typeData.split("\\.");
        JSONObject jsonParameter = (JSONObject) jsonObject.get(splitParameters[0]);

        if (jsonParameter == null)
            return null;
        return jsonParameter.get(splitParameters[1]).toString();
    }
}