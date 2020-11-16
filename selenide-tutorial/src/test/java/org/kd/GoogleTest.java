package org.kd;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.*;
import org.junit.Test;
import org.openqa.selenium.By;

import java.util.stream.Collectors;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class GoogleTest {
    //no PageObjectPattern
    private final String url = "https://www.google.com";
    private final String I_AGREE_TEXT = "Zgadzam si";
    private final String SEARCH_PHRASE = "Vodka and escorts";

    public static void main(String[] args) {
        GoogleTest test = new GoogleTest();
        test.googleSearch();
    }

    @Severity(SeverityLevel.NORMAL)
    @Story("US1 - Google Search")
    @Description("")
    @Test
    public void googleSearch() {
        open(url);
        acceptConsent();
        searchFor(SEARCH_PHRASE);

        ElementsCollection searchResults = $$(By.className("rc"));
        searchResults.stream()
                .map(SelenideElement::getText)
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
        SelenideElement iframe = $(By.tagName("iframe"));
        switchTo().frame(iframe);

        $(withText(I_AGREE_TEXT)).click();

    }

    private void tearDown() {
        closeWebDriver();
    }
}
