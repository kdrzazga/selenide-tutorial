package org.kd;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.*;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.codeborne.selenide.Selenide.*;

public class SeleniumEasyTest {
//no PageObjectPattern
    public static final String SELENIUM_EASY = "https://www.seleniumeasy.com/test";

    public static void main(String[] args) {
        SeleniumEasyTest test = new SeleniumEasyTest();
        test.testDualListBox();
    }

    @Severity(SeverityLevel.NORMAL)
    @Story("{empty}")
    @Description("")
    @Test
    private void testDualListBox() {
        open(SELENIUM_EASY + "/bootstrap-dual-list-box-demo.html");

        Map<String, List<String>> testDataMap = new HashMap<>();
        testDataMap.put("d", Arrays.asList("bootstrap-duallist", "Dapibus ac facilisis in"));
        testDataMap.put("ac ", Arrays.asList("Dapibus ac facilisis in", "Porta ac consectetur ac"));
        testDataMap.put("us ", Arrays.asList("Dapibus ac facilisis in", "Morbi leo risus"));

        testDataMap.forEach(
                (typedValue, expectedValue) -> {
                    typeInSearchDualList(typedValue);

                    $("div[class = 'well text-right']")
                            .find("ul[class = 'list-group']")
                            .findAll(By.tagName("li"))
                            .filter(Condition.not(Condition.empty))
                            .stream()
                            .map(SelenideElement::getText)
                            .forEach(elementText -> assertTrue(elementText, expectedValue));
                }
        );
    }

    @Step("Enter {0} in Search Dual List")
    private void typeInSearchDualList(String entry) {
        SelenideElement searchDualList = $(By.name("SearchDualList"));
        searchDualList.clear();
        searchDualList.sendKeys(entry);
        searchDualList.pressEnter();
    }

    private void assertTrue(String elementText, List<String> values) {
        if (!values.contains(elementText)) System.err.println(elementText + " - error");
        else System.out.println(elementText + " - OK");
    }
}
