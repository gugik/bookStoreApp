package org.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Wait {

    private WebDriver driver;

    public Wait(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isElementPresent(WebElement webElement, int timeout) {
        try {
            new FluentWait<>(driver).withTimeout(Duration.ofSeconds(timeout))
                    .pollingEvery(Duration.ofMillis(200))
                    .ignoring(NoSuchElementException.class, StaleElementReferenceException.class)
                    .until(ExpectedConditions.visibilityOf(webElement));
            return true;
        } catch (TimeoutException ignored) {
            return false;
        } finally {
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        }
    }
}
