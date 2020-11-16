package org.kd.lot;

import org.kd.common.Flight;

import java.util.Date;

public class LotFlight extends Flight {

    private final String cabinClass;

    public LotFlight(String departureAirport, String arrivalAirport, Date departureDate, Date returnDate
            , int passengerNumber, String cabinClass) {
        super(departureAirport, arrivalAirport, departureDate, returnDate, passengerNumber);
        this.cabinClass = cabinClass;
    }

    public String getCabinClass() {
        return cabinClass;
    }
}
