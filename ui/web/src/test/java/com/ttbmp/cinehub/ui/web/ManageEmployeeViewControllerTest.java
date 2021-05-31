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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
    void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.get("http://localhost:8080/");
        driver.manage().addCookie(new Cookie("session", "5KClU7hbNgedJAwLuF9eFVl6Qzz2"));
        driver.findElement(By.linkText("Manage employee shift")).click();
        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"select_date\"]")));
        driver.findElement(By.xpath("//*[@id=\"select_date\"]")).sendKeys(LocalDate.now().format(DateTimeFormatter.ofPattern("ddMMyyyy")));
        driver.findElement(By.xpath("//*[@id=\"search_shift_cinema\"]")).click();
        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"ppgJVL8wS9bdjWxCxs6bll2K0Xs1\"]")));
        employee = driver.findElement(By.xpath("//*[@id=\"ppgJVL8wS9bdjWxCxs6bll2K0Xs1\"]"));
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    @Test
    void searchEmployee() {
        //assertTrue(true);
        assertEquals(employee.getText(), "Jeff Bezos\n" + "Role : Projectionist");
    }

}
