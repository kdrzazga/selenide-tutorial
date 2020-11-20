package org.kd.pom;

import org.apache.http.auth.UsernamePasswordCredentials;
import org.kd.common.BasePage;
import org.kd.common.Lib;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Optional;

public class POM_LoginPage extends BasePage {

    public POM_LoginPage(WebDriver driver) {
        super(driver, "https://demo.nopcommerce.com/login");
    }

    public void loginAs(UsernamePasswordCredentials credentials) {
        WebElement textbox = driver.findElement(By.cssSelector("input[class='email']"));
        enterText(textbox, credentials.getUserName());

        WebElement passwdInput = driver.findElement(By.cssSelector("input[id='Password']"));
        enterText(passwdInput, credentials.getPassword());

        driver.findElement(By.cssSelector(("input[value='Log in']"))).click();
    }

    public String getAfterLoginMessage() {
        WebElement message = new Lib().find(this.driver, By.xpath("//div[contains(text(), 'Login was unsuccessful')]"));

        return Optional.ofNullable(message)
                .map(WebElement::getText)
                .orElse("");
    }

    private void enterText(WebElement usernameInput, String text) {
        usernameInput.click();
        usernameInput.clear();
        usernameInput.sendKeys(text);
    }
}
