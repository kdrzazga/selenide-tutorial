package org.kd.lot;

import org.kd.common.BasePage;

import static com.codeborne.selenide.Selenide.title;

public class PO_FlightBook extends BasePage {
    public PO_FlightBook() {
        super("");
    }

    public void confirmFlightChoice() {
        System.out.println("Navigated to " + title());

    }

    @Override
    public void navigate() {
        System.err.println("No URL");
    }
}
