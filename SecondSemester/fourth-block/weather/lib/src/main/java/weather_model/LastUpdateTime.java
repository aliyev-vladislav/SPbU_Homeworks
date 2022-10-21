package weather_model;

import java.util.Date;

public class LastUpdateTime {
    private final Date date;

    public LastUpdateTime() {
        date = new Date();
        System.out.printf("Data on %td.%<tm.%<tY %<tR", date);
    }

    public Date getDate() {
        return date;
    }
}
