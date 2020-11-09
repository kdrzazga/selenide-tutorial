package org.kd.decathlon;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.kd.common.BasePage;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class PO_Main extends BasePage {

    public PO_Main() {
        super("https://www.decathlon.pl/");
        open(url);
        Configuration.startMaximized = true;
    }

    public void searchForProduct(String product) {
        SelenideElement input = $("input[name='Ntt']");
        input.click();
        input.sendKeys(product + Keys.ENTER);

    }
}
