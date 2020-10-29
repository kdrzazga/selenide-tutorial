package org.kd;

import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class HerokuAppTest {

    public static final String HEROKUAPP_URL = "https://the-internet.herokuapp.com";

    public static void main(String[] args) {
        var helloSelenide = new HerokuAppTest();
        helloSelenide.simpleCheckboxTest();
        helloSelenide.simpleDropdownTestOption1();
        helloSelenide.simpleDropdownTestDisabledOption();
    }

    public void googleSearch() {
        String url = "https://www.google.com";
        Selenide.open(url);
        //WebDriver driver = WebDriverFactory.createChromeDriver();
        //driver.get(url);

        Selenide.Wait();

        var iframe = $(By.xpath("//iframe"));
        iframe.getWrappedDriver().switchTo();

        $(By.xpath("//*[text() = 'Ich stimme zu']")).click();
        $("#submit").click();
        $(".loading_progress").should(disappear); // Waits until element disappears
        $("#username").shouldHave(text("Hello, Johny!")); // Waits until element gets text
        closeWebDriver();
    }

    public void simpleCheckboxTest() {
        Selenide.open(HEROKUAPP_URL + "/checkboxes");
        var checkboxes = $$(By.xpath("//form[@id='checkboxes']/input"));
        checkboxes.iterator().forEachRemaining(WebElement::click);

        checkboxes.get(0).shouldBe(selected);
        checkboxes.get(1).shouldNotBe(selected);

        closeWebDriver();
    }

    public void simpleDropdownTestOption1() {
        open(HEROKUAPP_URL + "/dropdown");
        var options = $$(By.xpath("//select[@id='dropdown']/option"));
        options.get(1).click();

        options.get(0).shouldNotBe(selected);
        options.get(1).shouldBe(selected);
        options.get(2).shouldNotBe(selected);

        closeWebDriver();
    }

    public void simpleDropdownTestDisabledOption() {
        open(HEROKUAPP_URL + "/dropdown");
        var options = $$(By.xpath("//select[@id='dropdown']/option"));
        options.shouldHaveSize(3);

        options.get(1).click();
        options.get(0).click();

        options.get(0).shouldNotBe(selected);
        options.get(1).shouldBe(selected);
        options.get(2).shouldNotBe(selected);

        closeWebDriver();
    }

}
