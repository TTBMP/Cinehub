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

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class ViewPersonalScheduleSeleniumTest {

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
    void viewPersonalScheduleWithoutAuthentication_redirectToLogin() {
        driver.get("http://localhost:8080/schedule");
        new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/main/form/button")));
        assertEquals("http://localhost:8080/login", driver.getCurrentUrl());
    }

    @Test
    void viewPersonalScheduleNavLink_isActive() {
        loginAsProjectionist();
        var scheduleTab = driver.findElement(By.xpath("//*[@id=\"navbarSupportedContent\"]/ul/li[2]/a"));
        scheduleTab.click();
        new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(By.id("date-picker")));
        var navLink = driver.findElement(By.xpath("//*[@id=\"navbarSupportedContent\"]/ul/li[2]/a"));
        var classList = Arrays.stream(navLink.getAttribute("class").split(" ")).collect(Collectors.toList());
        assertTrue(classList.contains("active"));
    }

    @Test
    void viewPersonalScheduleDefaultDate_isToday() {
        loginAsProjectionist();
        var scheduleTab = driver.findElement(By.xpath("//*[@id=\"navbarSupportedContent\"]/ul/li[2]/a"));
        scheduleTab.click();
        new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(By.id("date-picker")));
        var datePicker = driver.findElement(By.id("date-picker"));
        assertEquals(
                LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE),
                datePicker.getAttribute("value")
        );
    }

    private void loginAsProjectionist() {
        var loginButton = driver.findElement(By.xpath("//*[@id=\"navbarSupportedContent\"]/form/input"));
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
