package org.kd.decathlon;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.kd.common.BasePage;
import org.openqa.selenium.By;

import java.util.Arrays;
import java.util.List;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.sleep;

public class PO_ProductPage extends BasePage {
    public PO_ProductPage() {
        super("");
    }

    public String getProductHeader() {
        return $x("//div[@id='conversion-zone']")
                .findElement(By.tagName("h1"))
                .getText();
    }

    @Step
    public void clickCheckAvailability() {
        String partialText = "w sklepie";
        $x("//section[@class='ovr-stock-info']/div[contains(text(), '" + partialText + "')]").click();

    }

    @Step
    public List<String> enterCity(String city) {
        String caption = "Wpisz miasto";
        SelenideElement cityTextbox = $x("//input[@placeholder = '" + caption + "']");
        cityTextbox.waitUntil(Condition.visible, 4000);
        cityTextbox.click();

        cityTextbox.sendKeys(city);
        SelenideElement citiesContainer = $x("//div[@class='store-selection']");
        sleep(500);
        //citiesContainer.waitUntil(Condition.appears, 4000);
        String citiesBlock = citiesContainer.getText();
        return Arrays.asList(citiesBlock.split("\n"));
    }

    @Override
    public void navigate() {
        System.err.println("No URL");
    }
}
