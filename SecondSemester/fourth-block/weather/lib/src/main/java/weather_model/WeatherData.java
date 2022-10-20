package weather_model;

public class WeatherData {
    private String airTemperature;
    private String realFeelTemp;
    private String cloudCover;
    private String pressure;
    private String humidity;
    private String windSpeed;

    public WeatherData() {
        this.airTemperature = null;
        this.realFeelTemp = null;
        this.cloudCover = null;
        this.pressure = null;
        this.humidity = null;
        this.windSpeed = null;
    }

    public WeatherData(
            String airTemperature,
            String realFeelTemp,
            String cloudCover,
            String pressure,
            String humidity,
            String windSpeed
    ) {
        this.airTemperature = airTemperature;
        this.realFeelTemp = realFeelTemp;
        this.cloudCover = cloudCover;
        this.pressure = pressure;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
    }

    public String getAirTemperature() {
        return airTemperature;
    }

    public String getRealFeelTemp() {
        return realFeelTemp;
    }

    public String getCloudCover() {
        return cloudCover;
    }

    public String getPressure() {
        return pressure;
    }

    public String getHumidity() {
        return humidity;
    }

    public String getWindSpeed() {
        return windSpeed;
    }
}
