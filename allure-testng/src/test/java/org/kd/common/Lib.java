package org.kd.common;

import org.openqa.selenium.*;

public class Lib {

    public WebElement find(WebDriver driver, By by) {
        try {
            return driver.findElement(by);
        } catch (NoSuchElementException | TimeoutException ex) {
            return null;
        }
    }

    public void sleep(int miliseconds) {
        try {
            Thread.sleep(miliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
