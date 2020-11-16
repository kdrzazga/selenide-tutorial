package org.kd.ryanair;

import com.codeborne.selenide.ClickOptions;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.kd.common.BasePage;
import org.kd.common.Flight;
import org.kd.ryanair.exception.NotAbleToReturnSameMonth;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class PO_RyanAirMain extends BasePage {
    public PO_RyanAirMain() {
        super("https://www.ryanair.com/");
        open(url);
    }

    public void acceptCookies(){
        ElementsCollection acceptCookiesButton = $$("button[data-ref='cookie.accept-all']");
        if (!acceptCookiesButton.isEmpty())
            acceptCookiesButton.get(0).click();
    }

    public void chooseLocals(String location) {
        $("button[aria-label='Open market switcher']").click();
        $(withText(location)).click(ClickOptions.usingDefaultMethod());
    }

    public PO_TripPage enterFlight(Flight flight) {
        enterAirport($("input[placeholder = 'Departure']"), flight.getDepartureAirport());
        enterAirport($("input[placeholder = 'Destination']"), flight.getArrivalAirport());

        selectFirstAvailableDates();
        enterAdultPassengersNumber(flight.getPassengerNumber());
        $("button[aria-label='Search']").click();
        return new PO_TripPage();
    }

    private void enterAdultPassengersNumber(int passengerNumber) {
        ElementsCollection plusesList = $$x("//div[@class='counter__button-wrapper--enabled']/ry-counter-button[@data-ref='counter.counter__increment']");
        while (readPassengersNumber().getAdults() < passengerNumber) {
            plusesList.get(0).parent().click();
        }
    }

    private PassengersNumber readPassengersNumber() {
        ElementsCollection numbers = $$("div[data-ref='counter.counter__value']");
        if (numbers.size() != 4) throw new RuntimeException("Wrong passengers");

        PassengersNumber result = new PassengersNumber();
        result.setAdults(Integer.parseInt(numbers.get(0).getText()));
        result.setTeens(Integer.parseInt(numbers.get(1).getText()));
        result.setChildren(Integer.parseInt(numbers.get(2).getText()));
        result.setInfants(Integer.parseInt(numbers.get(3).getText()));

        return result;
    }

    private void selectFirstAvailableDates() {
        SelenideElement arrowRight = $("div[data-ref='calendar-btn-next-month']");
        int maxNumberOfMonthsToCheck = 12;

        while (maxNumberOfMonthsToCheck > 0 && $$("div[class='calendar-body__cell']").isEmpty()) {
            arrowRight.click();
            maxNumberOfMonthsToCheck--;
        }
        ElementsCollection availableDays = $$("div[class='calendar-body__cell']");
        availableDays.get(0).click();

        if (availableDays.size() >= 2)
            availableDays.get(1).click();
        else
            throw new NotAbleToReturnSameMonth();
    }

    private void enterAirport(SelenideElement airportTextbox, String airport) {
        airportTextbox.click();
        airportTextbox.clear();
        airportTextbox.sendKeys(airport);
        airportTextbox.pressTab();
    }
}
