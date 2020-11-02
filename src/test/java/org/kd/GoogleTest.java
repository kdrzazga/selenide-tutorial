package org.kd;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.*;

public class GoogleTest {

    private final String url = "https://www.google.com";
    private final String I_AGREE_TEXT = "Ich stimme zu";
    private final String SEARCH_PHRASE = "Vodka and escorts";

    public static void main(String[] args) {
        GoogleTest test = new GoogleTest();
        test.googleSearch();
    }

    @Story("US1 - Google Search")
    public void googleSearch() {
        open(url);
        acceptConsent();
        searchFor(SEARCH_PHRASE);

        ElementsCollection searchResults = $$(By.className("rc"));
        searchResults.stream()
                .map(WebElement::getText)
                .map(textBlock -> textBlock.substring(0, textBlock.indexOf("\n")))
                .collect(Collectors.toList())
                .forEach(title -> assertContains(title.toLowerCase(), "vodka")); // TODO real assertion here

        tearDown();
    }

    private void assertContains(String fullText, String text) {
        if (!fullText.contains(text))
            System.err.println("Missing text " + text + " in " + fullText);
    }

    @Step
    private void searchFor(String phrase) {
        SelenideElement searchTextbox = $(By.name("q"));
        searchTextbox.click();
        searchTextbox.sendKeys(phrase);
        searchTextbox.pressEnter();//TODO: change to click button 'Google Search'
    }

    @Step
    private void acceptConsent() {
        SelenideElement iframe = $x("//iframe");
        iframe.getWrappedDriver().switchTo().frame(iframe);

        $x("//*[text() = '" + I_AGREE_TEXT + "']").click();
    }

    private void tearDown() {
        closeWebDriver();
    }
}
