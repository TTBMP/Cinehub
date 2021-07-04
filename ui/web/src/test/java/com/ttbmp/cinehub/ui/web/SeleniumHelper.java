package com.ttbmp.cinehub.ui.web;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author Fabio Buracchi
 */
public class SeleniumHelper {

    private SeleniumHelper() {}

    public static void waitPageToLoad(WebDriver driver) {
        new WebDriverWait(driver, 10).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }

    public static void clickAndWaitPageToLoad(WebDriver driver, WebElement element) {
        element.click();
        new WebDriverWait(driver, 10).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }

}
