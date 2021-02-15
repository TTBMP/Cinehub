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

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Massimo Mazzetti
 */

@SpringBootTest
class ManageEmployeeShiftTest {

    public WebElement message;
    private WebDriver driver;

    @BeforeAll
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://localhost:8080/manage_employee_shift");
        WebElement datep = driver.findElement(By.xpath("//*[@id=\"date\"]"));
        datep.sendKeys("20022021");
        driver.findElement(By.xpath("//*[@id=\"search\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"27\"]")).click();
        message = driver.findElement(By.xpath("//*[@id=\"day\"]"));
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    @Test
    void search() {
        assertEquals("2021-02-21", message.getText());
    }
}