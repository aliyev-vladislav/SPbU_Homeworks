package controller;

import org.json.simple.parser.ParseException;
import weather_model.WeatherModel;
import weather_parser.JsonParser;
import weather_service.WeatherApiRequest;
import weather_service.WeatherApiResponse;

public class StateController {
    private WeatherApiRequest request;
    private WeatherModel model;
    private JsonParser parser;

    public StateController(
            WeatherApiRequest request,
            WeatherModel model,
            JsonParser parser
    )
    {
        this.request = request;
        this.model = model;
        this.parser = parser;
    }

    public void updateState() {
        WeatherApiResponse dataRequest = new WeatherApiResponse();
        try {
            model.updateDate(parser.parse(dataRequest.response(request)));
        } catch (ParseException e) {
            model.updateDate(null);
        }
    }
}
