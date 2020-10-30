package org.kd;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.codeborne.selenide.Selenide.*;

public class SeleniumEasyTest {

    public static final String SELENIUM_EASY = "https://www.seleniumeasy.com/test";

    public static void main(String[] args) {
        SeleniumEasyTest test = new SeleniumEasyTest();
        test.testDualListBox();
    }

    private void testDualListBox() {
        open(SELENIUM_EASY + "/bootstrap-dual-list-box-demo.html");

        Map<String, List<String>> testDataMap = new HashMap<>();
        testDataMap.put("d", Arrays.asList("bootstrap-duallist", "Dapibus ac facilisis in"));
        testDataMap.put("ac ", Arrays.asList("Dapibus ac facilisis in", "Porta ac consectetur ac"));
        testDataMap.put("us ", Arrays.asList("Dapibus ac facilisis in", "Morbi leo risus"));

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
