package org.kd;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.util.List;
import java.util.Map;

import static com.codeborne.selenide.Selenide.*;

public class SeleniumEasyTest {

    public static final String SELENIUM_EASY = "https://www.seleniumeasy.com/test";

    public static void main(String[] args) {
        var test = new SeleniumEasyTest();
        test.testDualListBox();
    }

    private void testDualListBox() {
        open(SELENIUM_EASY + "/bootstrap-dual-list-box-demo.html");

        var testDataMap = Map.of(
                "d", List.of("bootstrap-duallist", "Dapibus ac facilisis in")
                , "ac ", List.of("Dapibus ac facilisis in", "Porta ac consectetur ac")
                , "us ", List.of("Dapibus ac facilisis in", "Morbi leo risus")
        );

        testDataMap.forEach(
                (entry, values) -> {
                    typeInSearchDualList(entry);
                    $$x("//div[@class = 'well text-right']/ul[@class = 'list-group']/li")
                            .stream()
                            .map(SelenideElement::getText)
                            .filter(text -> !text.isEmpty())
                            .forEach(elementText -> assertTrue(elementText, values));
                }
        );
    }

    private void typeInSearchDualList(String entry) {
        var searchDualList = $(By.name("SearchDualList"));
        searchDualList.clear();
        searchDualList.sendKeys(entry);
        searchDualList.pressEnter();
    }

    private void assertTrue(String elementText, List<String> values) {
        if (!values.contains(elementText)) System.err.println(elementText + " - error");
        else System.out.println(elementText + " - OK");
    }
}
