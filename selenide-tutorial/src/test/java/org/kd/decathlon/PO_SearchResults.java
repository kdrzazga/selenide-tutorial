package org.kd.decathlon;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.kd.common.BasePage;
import org.openqa.selenium.By;

import java.util.List;

import static com.codeborne.selenide.Selenide.$$;

public class PO_SearchResults extends BasePage {

    public PO_SearchResults() {
        super("");
    }

    @Step
    public void clickProduct(int index){
        getFoundProducts().get(index).click();
    }

    public List<SelenideElement> getFoundProducts(){
        return $$(By.tagName("article"));
    }

    @Override
    public void navigate() {
        System.err.println("No URL");
    }
}
