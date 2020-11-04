package org.kd;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.kd.lot.Flight;
import org.kd.lot.LotTestDataFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.codeborne.selenide.Selenide.*;

public class LotTest {

    private final String url = "https://www.lot.com/pl/en/";

    public static void main(String[] args) {
        LotTest test = new LotTest();
        test.searchFlight(new LotTestDataFactory().createBejingShenzhenFlight());
    }

    private void searchFlight(Flight testData) {
        Configuration.startMaximized = true;
        open(url);

        enterAirports(testData.getDepartureAirport(), testData.getArrivalAirport());
        enterDepartureDate(convertDate(testData.getDepartureDate()));
        enterReturnDate(convertDate(testData.getReturnDate()));
        enterPassengerCount(testData.getPassengerNumber());
        enterTicketClass(testData.getCabinClass());

        clickSearchButton();

        //TODO assert title()
    }

    private void clickSearchButton() {
        $x("//button[@type='submit' and span/text()='Search' and not(contains(@style,'displayed:false'))]")
                .click();
    }

    private void enterTicketClass(String cabinClass) {
        $("#select2-ticketClass-container").click();
        getFocusedElement().sendKeys(cabinClass);
        getFocusedElement().sendKeys(Keys.ENTER);
    }

    private void enterPassengerCount(int count) {
        $(By.id("passenger-switch")).click();
        getFocusedElement().sendKeys(Integer.valueOf(count).toString());
        getFocusedElement().sendKeys(Keys.ENTER);
    }

    private void enterReturnDate(String dateAsText) {
        $("#returnDate-display").click();
        getFocusedElement().sendKeys(dateAsText);
        getFocusedElement().sendKeys(Keys.ENTER);
    }

    private void enterDepartureDate(String dateAsText) {
        $(By.id("departureDate-display")).click();
        getFocusedElement().sendKeys(dateAsText);
        getFocusedElement().sendKeys(Keys.ENTER);
    }

    private void enterAirports(String departureAirport, String arrivalAirport) {
        delay(1000);
        SelenideElement arrivalAirportTextbox = $x("//span/span[text()='Choose or enter arrival airport']");
        arrivalAirportTextbox.click();
        getFocusedElement().click();
        getFocusedElement().sendKeys(arrivalAirport);
        getFocusedElement().sendKeys(Keys.ENTER);

        SelenideElement departureAirportElement = $("#departureAirport-label");
        departureAirportElement.click();
        getFocusedElement().sendKeys(departureAirport);
        getFocusedElement().sendKeys(Keys.ENTER);

        delay(1000);
    }

    private void delay(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private String convertDate(Date dateToConvert) {
        return new SimpleDateFormat("dd.MM.yyyy")
                .format(dateToConvert);
    }
}
