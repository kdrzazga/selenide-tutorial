package org.kd.lot;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.util.Date;

import static com.codeborne.selenide.Selenide.*;

public class PO_FlightSearch {
    public final String url = "https://www.lot.com/pl/en/";
    private final Lib lib = new Lib();

    public PO_FlightSearch() {
        Configuration.startMaximized = true;
    }

    public void searchFlight(Flight testData) {

        open(url);

        enterAirports(testData.getDepartureAirport(), testData.getArrivalAirport());
        enterDepartureDate(lib.convertDate(testData.getDepartureDate()));
        enterReturnDate(lib.convertDate(testData.getReturnDate()));
        enterPassengerCount(testData.getPassengerNumber());
        enterTicketClass(testData.getCabinClass());

        clickSearchButton();

        //TODO assert title()
        lib.delay(3000);
    }

    public void clickSearchButton() {
        $x("//button[@type='submit' and span/text()='Search' and not(contains(@style,'displayed:false'))]")
                .click();
    }

    public void enterTicketClass(String cabinClass) {
        $("#select2-ticketClass-container").click();
        getFocusedElement().sendKeys(cabinClass);
        getFocusedElement().sendKeys(Keys.ENTER);
    }

    public void enterPassengerCount(int count) {
        $(By.id("passenger-switch")).click();
        getFocusedElement().sendKeys(Integer.valueOf(count).toString());
        getFocusedElement().sendKeys(Keys.ENTER);
    }

    public void enterReturnDate(String dateAsText) {
        $("#returnDate-display").click();
        getFocusedElement().sendKeys(dateAsText);
        getFocusedElement().sendKeys(Keys.ENTER);
    }

    public void enterDepartureDate(String dateAsText) {
        $(By.id("departureDate-display")).click();
        getFocusedElement().sendKeys(dateAsText);
        getFocusedElement().sendKeys(Keys.ENTER);
    }

    public void enterAirports(String departureAirport, String arrivalAirport) {
        lib.delay(1000);
        SelenideElement arrivalAirportTextbox = $x("//span/span[text()='Choose or enter arrival airport']");
        arrivalAirportTextbox.click();
        getFocusedElement().click();
        getFocusedElement().sendKeys(arrivalAirport);
        getFocusedElement().sendKeys(Keys.ENTER);

        SelenideElement departureAirportElement = $("#departureAirport-label");
        departureAirportElement.click();
        getFocusedElement().sendKeys(departureAirport);
        getFocusedElement().sendKeys(Keys.ENTER);

        lib.delay(1000);
    }
}