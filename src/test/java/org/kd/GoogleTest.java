package org.kd;

import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.*;

public class GoogleTest {

    private final String url = "https://www.google.com";
    private final String I_AGREE_TEXT = "Ich stimme zu";
    private final String SEARCH_PHRASE = "Vodka and escorts";

    public static void main(String[] args) {
        var test = new GoogleTest();
        test.googleSearch();
    }

    public void googleSearch() {
        Selenide.open(url);
        acceptConsent();
        searchFor(SEARCH_PHRASE);

        var searchResults = $$(By.className("rc"));
        searchResults.stream()
                .map(WebElement::getText)
                .map(textBlock -> textBlock.substring(0, textBlock.indexOf("\n")))
                .collect(Collectors.toList())
                .forEach(title -> assertContains(title.toLowerCase(), "vodka")); // TODO real assertion here

        closeWebDriver();
    }

    private void assertContains(String fullText, String text) {
        if (!fullText.contains(text))
            System.err.println("Missing text " + text + " in " + fullText);
    }

    private void searchFor(String phrase) {
        var searchTextbox = $(By.name("q"));
        searchTextbox.click();
        searchTextbox.sendKeys(phrase);
        searchTextbox.pressEnter();//TODO: change to click button 'Google Search'
    }

    private void acceptConsent() {
        Selenide.Wait();
        var iframe = $x("//iframe");
        iframe.getWrappedDriver().switchTo().frame(iframe);

        $x("//*[text() = '" + I_AGREE_TEXT + "']").click();// $(By.xpath("//*[contains(text(), 'Ich stimme zu')]"))
    }
}
