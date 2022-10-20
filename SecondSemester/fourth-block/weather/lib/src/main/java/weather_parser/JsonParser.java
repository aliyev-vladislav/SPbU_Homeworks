package weather_parser;

import org.json.simple.parser.ParseException;

import java.util.Map;

public interface JsonParser {
    Map<String, String> parse(String json) throws ParseException;
}
