package org.kd;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Condition.selected;
import static com.codeborne.selenide.Selenide.*;

public class HerokuAppTest {

    public static final String HEROKUAPP_URL = "https://the-internet.herokuapp.com";

    public static void main(String[] args) {
        HerokuAppTest helloSelenide = new HerokuAppTest();
        helloSelenide.simpleCheckboxTest();
        helloSelenide.simpleDropdownTestOption1();
        helloSelenide.simpleDropdownTestDisabledOption();
    }

    public void simpleCheckboxTest() {
        Selenide.open(HEROKUAPP_URL + "/checkboxes");
        ElementsCollection checkboxes = $$x("//form[@id='checkboxes']/input");
        checkboxes.iterator().forEachRemaining(WebElement::click);

        checkboxes.get(0).shouldBe(selected);
        checkboxes.get(1).shouldNotBe(selected);

        closeWebDriver();
    }

    public void simpleDropdownTestOption1() {
        open(HEROKUAPP_URL + "/dropdown");
        ElementsCollection options = $$x("//select[@id='dropdown']/option");
        options.get(1).click();

        options.get(0).shouldNotBe(selected);
        options.get(1).shouldBe(selected);
        options.get(2).shouldNotBe(selected);

        closeWebDriver();
    }

    public void simpleDropdownTestDisabledOption() {
        open(HEROKUAPP_URL + "/dropdown");
        ElementsCollection options = $$x("//select[@id='dropdown']/option");
        options.shouldHaveSize(3);

        options.get(1).click();
        options.get(0).click();

        options.get(0).shouldNotBe(selected);
        options.get(1).shouldBe(selected);
        options.get(2).shouldNotBe(selected);

        closeWebDriver();
    }
}
