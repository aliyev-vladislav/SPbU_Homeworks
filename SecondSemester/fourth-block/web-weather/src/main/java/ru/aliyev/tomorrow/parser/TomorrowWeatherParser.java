package ru.aliyev.tomorrow.parser;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import ru.aliyev.weather_parser.JsonParser;

import java.util.HashMap;
import java.util.Map;

public class TomorrowWeatherParser implements JsonParser {
    @Override
    public Map<String, String> parse(String json) throws ParseException {
        if (json == null) {
            return null;
        }
        var jsonObj = (JSONObject) new JSONParser().parse(json);
        return new HashMap<>() {{
            put("airTemperature", convertJson("temperature", jsonObj));
            put("realFeelTemp", convertJson("temperatureApparent", jsonObj));
            put("cloudCover", convertJson("cloudCover", jsonObj));
            put("pressure", convertJson("pressureSurfaceLevel", jsonObj));
            put("humidity", convertJson("humidity", jsonObj));
            put("windSpeed", convertJson("windSpeed", jsonObj));
        }};
    }

    private String convertJson(String typeData, JSONObject jsonObject) {
        var dataObject = (JSONObject) jsonObject.get("data");
        var timeLinesArray =(JSONArray) dataObject.get("timelines");
        var timeLinesObject= (JSONObject) timeLinesArray.get(0);
        var intervalsArray = (JSONArray) timeLinesObject.get("intervals");
        var intervalsObject= (JSONObject) intervalsArray.get(0);
        var valuesObject = (JSONObject) intervalsObject.get("values");
        if (valuesObject == null)
            return null;
        return valuesObject.get(typeData).toString();
    }
}

