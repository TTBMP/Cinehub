package com.ttbmp.cinehub.ui.web;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import static com.ttbmp.cinehub.ui.web.SeleniumHelper.clickAndWaitPageToLoad;
import static com.ttbmp.cinehub.ui.web.SeleniumHelper.waitPageToLoad;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ManageEmployeeViewControllerTest {

    public WebElement employee;
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
    void foo(){
        loginAsManager();
        var manageEmployeeTab = driver.findElement(By.xpath("//*[@id=\"navbarSupportedContent\"]/ul/li[2]/a"));
        clickAndWaitPageToLoad(driver, manageEmployeeTab);
        var searchShiftButton = driver.findElement(By.xpath("//*[@id=\"search_shift_cinema\"]"));
        clickAndWaitPageToLoad(driver, searchShiftButton);

        var assignUsherButton = driver.findElement(By.xpath("//*[@id=\"assignUshShift\"]"));
       // ((JavascriptExecutor) driver).executeScript("return window.scrollTo(0, " + assignUsherButton.getLocation().y + ");");
       // ((JavascriptExecutor) driver).executeScript("console.log(\"pippo\");");
        Actions actions = new Actions(driver);
        actions.moveToElement(assignUsherButton).build().perform();
        assignUsherButton.click();
       // clickAndWaitPageToLoad(driver,assignUsherButton);
        driver.findElement(By.xpath("//*[@id=\"date\"]")).sendKeys(LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        driver.findElement(By.xpath("//*[@id=\"start_time\"]")).sendKeys("12:00");
        driver.findElement(By.xpath("//*[@id=\"end_time\"]")).sendKeys("10:00");
        driver.findElement(By.xpath("/html/body/main/div/div[2]/form/div/div[7]/a/button")).click();
        employee = driver.findElement(By.xpath("/html/body/main/div/div/p"));
    }

    private void loginAsManager() {
        var loginButton = driver.findElement(By.xpath("//*[@id=\"navbarSupportedContent\"]/form/input"));
        clickAndWaitPageToLoad(driver,loginButton);
        driver.findElement(By.xpath("//*[@id=\"floatingInput\"]")).sendKeys("mz@cinehub.com");
        driver.findElement(By.xpath("//*[@id=\"floatingPassword\"]")).sendKeys("123");
        var signInButton = driver.findElement(By.xpath("/html/body/main/form/button"));
        clickAndWaitPageToLoad(driver,signInButton);
    }
}
