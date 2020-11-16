package org.kd;

import org.kd.lot.LotTestDataFactory;
import org.kd.lot.PO_FlightBook;
import org.kd.lot.PO_Lot_FlightSearch;

public class LotTest {

    public static void main(String[] args) {
        PO_Lot_FlightSearch lotBookingPage = new PO_Lot_FlightSearch();
        PO_FlightBook flightBookPage = new PO_FlightBook();

        lotBookingPage.searchFlight(new LotTestDataFactory().createKrakowWarsawFlight());
        flightBookPage.confirmFlightChoice();
    }

}
