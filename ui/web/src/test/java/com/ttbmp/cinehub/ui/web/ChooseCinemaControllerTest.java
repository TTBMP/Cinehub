package com.ttbmp.cinehub.ui.web;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ChooseCinemaControllerTest {


    public WebElement welcomeMessage;
    private WebDriver driver;

    @BeforeAll
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
    }


    @AfterEach
    void tearDown() {
        driver.close();
    }

    @Test
    void search() {
        driver.get("http://localhost:8080/choose_movie");
        driver.manage().window().setSize(new Dimension(1646, 930));
        driver.findElement(By.id("date_picker")).click();
        driver.findElement(By.id("date_picker")).clear();
        driver.findElement(By.id("date_picker")).sendKeys("19/02/2021");
        driver.findElement(By.id("search")).click();
        assertEquals(driver.findElement(By.xpath("/html/body/div/div[2]/div[1]/div/h4[1]")).getText(), "Vote:");

    }
}
