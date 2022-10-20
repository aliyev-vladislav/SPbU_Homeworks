package time;

import java.util.Date;

public class LastUpdateTime {
    private Date date;

    public LastUpdateTime() {
        date = new Date();
        System.out.printf("Data on %td.%<tm.%<tY %<tR", date);
    }

    public Date getDate() {
        return date;
    }
}
