package org.kd;

import org.kd.common.Flight;
import org.kd.ryanair.PO_RyanAirMain;
import org.kd.ryanair.PO_TripPage;
import org.kd.ryanair.RyanAirTestDataFactory;

import static com.codeborne.selenide.Selenide.sleep;

public class RyanAirTest {

    public static void main(String[] args) {
        RyanAirTest test = new RyanAirTest();
        test.testBookingFlight(new RyanAirTestDataFactory().createLondonBarcelonaFlight());
    }

    private void testBookingFlight(Flight flight) {
        PO_RyanAirMain mainPage = new PO_RyanAirMain();
        mainPage.chooseLocals("Great Britain");
        mainPage.acceptCookies();
        PO_TripPage tripPage = mainPage.enterFlight(flight);

        //PO_TripPage tripPage = new PO_TripPage();
        tripPage.selectFlight("regular");
        tripPage.selectReturnFlight("regular");
        tripPage.clickLogInLater();
        sleep(3000);
    }
}
