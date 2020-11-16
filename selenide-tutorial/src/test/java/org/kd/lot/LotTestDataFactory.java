package org.kd.lot;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class LotTestDataFactory {

    public LotFlight createBejingShenzhenFlight() {
        Date today = Calendar.getInstance().getTime();

        LocalDateTime nextWeek = today.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime()
                .plusDays(7);

        return new LotFlight("Beijing", "Shenzhen"
                , today, Date.from(nextWeek.atZone(ZoneId.systemDefault()).toInstant())
                , 1, "economy");
    }

    public LotFlight createKrakowWarsawFlight() {
        Date today = Calendar.getInstance().getTime();

        LocalDateTime nextWeek = today.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime()
                .plusDays(3);

        return new LotFlight("Krakow", "Warsaw"
                , today, Date.from(nextWeek.atZone(ZoneId.systemDefault()).toInstant())
                , 1, "LOT Business Class");
    }
}
