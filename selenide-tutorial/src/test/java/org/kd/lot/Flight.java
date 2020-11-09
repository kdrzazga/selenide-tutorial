package org.kd.lot;

import java.util.Date;

//@Getter
//@AllArgsConstructor
public class Flight {

    private String departureAirport;
    private String arrivalAirport;
    private Date departureDate;
    private Date returnDate;
    private int passengerNumber;
    private String cabinClass;

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
