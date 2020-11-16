package org.kd.ryanair;

import org.kd.common.Flight;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class RyanAirTestDataFactory {

    public Flight createLondonBarcelonaFlight() {
        Date today = Calendar.getInstance().getTime();

        LocalDateTime nextWeek = today.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime()
                .plusDays(7);

        return new Flight("London Luton", "Barcelona Girona"
                , today, Date.from(nextWeek.atZone(ZoneId.systemDefault()).toInstant())
                , 3);
    }

}
