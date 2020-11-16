package org.kd.lot;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.kd.common.BasePage;
import org.kd.common.Lib;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.junit.Test;

import java.util.Objects;

import static com.codeborne.selenide.Selenide.*;

public class PO_Lot_FlightSearch extends BasePage {
    private final Lib lib = new Lib();

    public PO_Lot_FlightSearch() {
        super("https://www.lot.com/pl/en/");
    }

    @Severity(SeverityLevel.NORMAL)
    @Story("{empty}")
    @Description("")
    @Test
    public void searchFlight(LotFlight testData) {

        open(url);

        enterAirports(testData.getDepartureAirport(), testData.getArrivalAirport());
        enterDepartureDate(lib.convertDate(testData.getDepartureDate()));
        enterReturnDate(lib.convertDate(testData.getReturnDate()));
        enterPassengerCount(testData.getPassengerNumber());
        enterTicketClass(testData.getCabinClass());

        clickSearchButton();

        System.out.println("Navigated to " + title());
        //TODO assert title()
        sleep(3000);
    }

    public void clickSearchButton() {
        $x("//button[@type='submit' and span/text()='Search' and not(contains(@style,'displayed:false'))]")
                .click();
    }

    public void enterTicketClass(String cabinClass) {
        $("#select2-ticketClass-container").click();
        Objects.requireNonNull(getFocusedElement()).sendKeys(cabinClass);
        getFocusedElement().sendKeys(Keys.ENTER);
    }

    public void enterPassengerCount(int count) {
        $(By.id("passenger-switch")).click();
        Objects.requireNonNull(getFocusedElement()).sendKeys(Integer.valueOf(count).toString());
        getFocusedElement().sendKeys(Keys.ENTER);
    }

    public void enterReturnDate(String dateAsText) {
        $("#returnDate-display").click();
        Objects.requireNonNull(getFocusedElement()).sendKeys(dateAsText);
        getFocusedElement().sendKeys(Keys.ENTER);
    }

    public void enterDepartureDate(String dateAsText) {
        $(By.id("departureDate-display")).click();
        Objects.requireNonNull(getFocusedElement()).sendKeys(dateAsText);
        getFocusedElement().sendKeys(Keys.ENTER);
    }

    public void enterAirports(String departureAirport, String arrivalAirport) {
        sleep(1000);
        SelenideElement arrivalAirportTextbox = $x("//span/span[text()='Choose or enter arrival airport']");
        //SelenideElement arrivalAirportTextbox = $(withText("Choose or enter arrival airport"));

        arrivalAirportTextbox.click();
        Objects.requireNonNull(getFocusedElement()).click();
        getFocusedElement().sendKeys(arrivalAirport);
        getFocusedElement().sendKeys(Keys.ENTER);

        SelenideElement departureAirportElement = $("#departureAirport-label");
        departureAirportElement.click();
        getFocusedElement().sendKeys(departureAirport);
        getFocusedElement().sendKeys(Keys.ENTER);

        sleep(1000);
    }
}