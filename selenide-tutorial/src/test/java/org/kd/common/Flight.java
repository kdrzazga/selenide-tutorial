package org.kd.common;

import java.util.Date;

public class Flight {
    protected final String departureAirport;
    protected final String arrivalAirport;
    protected final Date departureDate;
    protected final Date returnDate;
    protected final int passengerNumber;

    public Flight(String departureAirport, String arrivalAirport, Date departureDate, Date returnDate, int passengerNumber) {
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.departureDate = departureDate;
        this.returnDate = returnDate;
        this.passengerNumber = passengerNumber;
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
}
