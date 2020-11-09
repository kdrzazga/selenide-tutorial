package org.kd.decathlon;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.kd.common.BasePage;
import org.kd.common.Lib;

import java.util.Arrays;
import java.util.List;

import static com.codeborne.selenide.Selenide.$x;

public class PO_ProductPage extends BasePage {
    public PO_ProductPage() {
        super("");
    }

    public String getProductHeader() {
        return $x("//div[@id='conversion-zone']/h1").getText();
    }

    public void clickCheckAvailability() {
        String partialText = "w sklepie";
        $x("//section[@class='ovr-stock-info']/div[contains(text(), '" + partialText + "')]").click();

    }

    public List<String> enterCity(String city) {
        String caption = "Wpisz miasto";
        SelenideElement cityTextbox = $x("//input[@placeholder = '" + caption + "']");
        cityTextbox.shouldBe(Condition.visible);
        cityTextbox.click();

        cityTextbox.sendKeys(city);
        SelenideElement citiesContainer = $x("//div[@class='store-selection']");
        new Lib().delay(500);
        citiesContainer.shouldBe(Condition.visible);
        String citiesBlock = citiesContainer.getText();
        return Arrays.asList(citiesBlock.split("\n"));
    }
}
