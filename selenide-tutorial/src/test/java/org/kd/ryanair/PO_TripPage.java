package org.kd.ryanair;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.kd.common.BasePage;

import java.util.Arrays;
import java.util.List;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class PO_TripPage extends BasePage {

    private final List<String> cardTypes = Arrays.asList("value", "regular", "plus", "flexi plus");

    public PO_TripPage() {
        super("https://www.ryanair.com/gb/en/trip/flights/select" +
                "?adults=3&teens=0&children=0&infants=0&dateOut=2021-06-02&dateIn=2021-06-09&isConnectedFlight=false" +
                "&isReturn=true&discount=0&promoCode=&originIata=LTN&destinationIata=GRO&tpAdults=3&tpTeens=0&tpChildren=0" +
                "&tpInfants=0&tpStartDate=2021-06-02&tpEndDate=2021-06-09&tpDiscount=0&tpPromoCode=&tpOriginIata=LTN" +
                "&tpDestinationIata=GRO");
        open(url);
    }

    public void selectFlight(String type) {
        $("div[class='spinner__icon']").should(disappear);

        ElementsCollection flightCard = $$("flight-card");
        flightCard.get(0).waitUntil(appears, 40 * 10000);
        flightCard.get(0).click();
        $$("fare-card").get(cardTypes.indexOf(type)).click();
    }

    public void selectReturnFlight(String type) {
        $$("flight-card").get(1).click();
        $$("fare-card").get(cardTypes.indexOf(type)).click();
    }

    public void clickLogInLater() {
        SelenideElement loginLaterButton = $(byText("Log in later"));
        loginLaterButton.should(appear);
        loginLaterButton.click();
    }
}
