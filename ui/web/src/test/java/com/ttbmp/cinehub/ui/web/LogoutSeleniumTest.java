package com.ttbmp.cinehub.ui.web;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
class LogoutSeleniumTest {

    private WebDriver driver;

    @BeforeAll
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.get("http://localhost:8080/");
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    @Test
    void logoutButtonWithoutAuthentication_isNotPresent() {
        var button = driver.findElement(By.xpath("//*[@id=\"navbarSupportedContent\"]/form/input"));
        assertNotEquals("Logout", button.getAttribute("value"));
    }

    @Test
    void logoutButtonWithAuthentication_isPresent() {
        loginAsProjectionist();
        var button = driver.findElement(By.xpath("//*[@id=\"navbarSupportedContent\"]/form/input"));
        assertNotEquals("Logout", button.getAttribute("value"));
    }

    @Test
    void logoutWithAuthentication_closeSession() {
        loginAsProjectionist();
        var logoutButton = driver.findElement(By.xpath("//*[@id=\"navbarSupportedContent\"]/form/input"));
        logoutButton.click();
        assertFalse(driver.manage().getCookies().stream().anyMatch(cookie -> cookie.getName().equals("session")));
    }

    private void loginAsProjectionist() {
        var loginButton = driver.findElement(By.xpath("//*[@id=\"navbarSupportedContent\"]/form[1]/input"));
        loginButton.click();
        var emailInput = driver.findElement(By.xpath("//*[@id=\"floatingInput\"]"));
        var passwordInput = driver.findElement(By.xpath("//*[@id=\"floatingPassword\"]"));
        var sigInButton = driver.findElement(By.xpath("/html/body/main/form/button"));
        new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(sigInButton));
        new Actions(driver).click(emailInput).sendKeys("fb@cinehub.com").perform();
        new Actions(driver).click(passwordInput).sendKeys("asdfghjkl").perform();
        sigInButton.click();
    }

}
