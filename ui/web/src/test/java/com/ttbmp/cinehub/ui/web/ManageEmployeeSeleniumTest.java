package com.ttbmp.cinehub.ui.web;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import static com.ttbmp.cinehub.ui.web.SeleniumHelper.clickAndWaitPageToLoad;
import static com.ttbmp.cinehub.ui.web.SeleniumHelper.waitPageToLoad;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * @author Massimo Mazzetti
 */
@SpringBootTest
class ManageEmployeeSeleniumTest {

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
        driver.manage().deleteAllCookies();
        driver.get("http://localhost:8080/");
        waitPageToLoad(driver);
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    @Test
    void tableLoaded() {
        loginAsManager();
        var manageEmployeeTab = driver.findElement(By.xpath("//*[@id=\"navbarSupportedContent\"]/ul/li[2]/a"));
        clickAndWaitPageToLoad(driver, manageEmployeeTab);
        var isPresent = driver.findElements(By.xpath("/html/body/main/div/div[2]/div[1]/table")).size() > 0;
        assertFalse(isPresent);
    }

    private void loginAsManager() {
        var loginButton = driver.findElement(By.xpath("//*[@id=\"navbarSupportedContent\"]/form/input"));
        clickAndWaitPageToLoad(driver, loginButton);
        driver.findElement(By.xpath("//*[@id=\"floatingInput\"]")).sendKeys("mz@cinehub.com");
        driver.findElement(By.xpath("//*[@id=\"floatingPassword\"]")).sendKeys("123");
        var signInButton = driver.findElement(By.xpath("/html/body/main/form/button"));
        clickAndWaitPageToLoad(driver, signInButton);
    }
}
