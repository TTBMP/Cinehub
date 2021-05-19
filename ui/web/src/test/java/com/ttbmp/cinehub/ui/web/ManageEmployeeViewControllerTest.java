package com.ttbmp.cinehub.ui.web;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.concurrent.TimeUnit;

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
    void setUp() throws InterruptedException {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.get("http://localhost:8080/");
        driver.manage().addCookie(new Cookie("session", "MANAGER"));
        driver.findElement(By.linkText("Manage employee shift")).click();
        driver.findElement(By.id("date")).sendKeys( LocalDate.now().format(DateTimeFormatter.ofPattern("ddMMyyyy")) );
        driver.findElement(By.id("search_shift_cinema")).click();
        employee = driver.findElement(By.xpath("/html/body/div[2]/div/table/tbody/tr[1]/td[1]/div/div/div[2]/p[1]"));
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    @Test
    void searchCinema() {
        assertEquals(
                employee.getText(),
                "Fabio Buracchi"
        );
    }
}
