package org.kd;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.kd.common.Lib;
import org.kd.common.TestDataFactory;
import org.kd.pom.POM_LoginPage;
import org.kd.pom.POM_Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

public class SeleniumTest {

    WebDriver driver;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get("https://demo.nopcommerce.com/");
        driver.manage().window().maximize();
    }

    @Test(priority = 1)
    @Severity(SeverityLevel.TRIVIAL)
    @Description("Test if logo is present")
    @Story("US1 : Logo")
    public void testLogoPresence() {
        boolean displayStatus = driver.findElement(By.xpath("//div[@class='header-logo']//a//img")).isDisplayed();
        assertTrue(displayStatus);
    }

    @Test(priority = 2)
    @Severity(SeverityLevel.NORMAL)
    @Story("US2 : Login")
    public void loginTest() {
        POM_Page page = new POM_Page(this.driver);
        POM_LoginPage loginPage = page.clickLoginButton();
        loginPage.loginAs(new TestDataFactory().createCredentials());
        if (loginPage.getAfterLoginMessage().isEmpty())
            fail("Message of Login error not found");
    }

    @Test(priority = 3)
    @Story("US3 : Registration")
    public void registrationTest() {
        fail("Not implemented yet");
    }

    @AfterClass
    public void tearDown() {
        driver.close();
    }
}
