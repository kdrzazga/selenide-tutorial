package org.kd.decathlon;

import io.qameta.allure.Step;
import org.kd.common.BasePage;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.*;

public class PO_Main extends BasePage {

    public PO_Main() {
        super("https://www.decathlon.pl/");
        open(url);
    }

    @Step
    public void searchForProduct(String product) {
        $("input[name='Ntt']").click();
        getFocusedElement().sendKeys(product + Keys.ENTER);
    }
}
