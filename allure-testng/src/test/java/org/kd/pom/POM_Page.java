package org.kd.pom;

import io.qameta.allure.Step;
import org.kd.common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class POM_Page extends BasePage {

    public POM_Page(WebDriver driver) {
        super(driver, "https://demo.nopcommerce.com/");
    }

    @Step("Click Login Button")
    public POM_LoginPage clickLoginButton(){
        driver.findElement(By.xpath("//a[text() = 'Log in']")).click();
        return new POM_LoginPage(driver);
    }
}
