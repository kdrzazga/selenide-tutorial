package org.kd.lot;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class LotTestDataFactory {

    public Flight createBejingShenzhenFlight() {
        Date today = Calendar.getInstance().getTime();

        LocalDateTime nextWeek = today.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime()
                .plusDays(7);

        return new Flight("Beijing", "Shenzhen", today, Date.from(nextWeek.atZone(ZoneId.systemDefault())
                .toInstant()), 1, "economy");
    }
}
