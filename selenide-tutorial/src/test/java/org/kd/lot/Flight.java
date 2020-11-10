package org.kd.lot;

import java.util.Date;

//@Getter
//@AllArgsConstructor
public class Flight {

    private final String departureAirport;
    private final String arrivalAirport;
    private final Date departureDate;
    private final Date returnDate;
    private final int passengerNumber;
    private final String cabinClass;

    public Flight(String departureAirport, String arrivalAirport, Date departureDate, Date returnDate, int passengerNumber, String cabinClass) {
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.departureDate = departureDate;
        this.returnDate = returnDate;
        this.passengerNumber = passengerNumber;
        this.cabinClass = cabinClass;
    }

    public String getDepartureAirport() {
        return departureAirport;
    }

    public String getArrivalAirport() {
        return arrivalAirport;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public int getPassengerNumber() {
        return passengerNumber;
    }

    public String getCabinClass() {
        return cabinClass;
    }
}
