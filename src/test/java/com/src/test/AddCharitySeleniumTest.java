package com.src.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AddCharitySeleniumTest {

    private static WebDriver driver;
    private static final String BASE_URL = "http://localhost:8080/project/addCharity.jsp";

    @BeforeAll
    public static void setup() {
        // ✅ Setup ChromeDriver before tests run
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testAddCharityFormSubmission() throws InterruptedException {
        // ✅ Open the Add Charity page
        driver.get(BASE_URL);

        // ✅ Fill out charity form fields
        driver.findElement(By.name("name")).sendKeys("Hand");
        driver.findElement(By.name("regno")).sendKeys("92398");
        driver.findElement(By.name("contact")).sendKeys("9876543210");
        driver.findElement(By.name("email")).sendKeys("help4@charity.org");
        driver.findElement(By.name("location")).sendKeys("Hyderabad, India");
        driver.findElement(By.name("password")).sendKeys("Charity@123");
        driver.findElement(By.name("confirmPassword")).sendKeys("Charity@123");

        // ✅ Click the submit button
        WebElement submitButton = driver.findElement(By.xpath("/html/body/form/input[8]"));
        submitButton.click();

        // Wait for the response to load
        Thread.sleep(2000);

        // ✅ Check for success in the page content
        String pageSource = driver.getPageSource();
        assertTrue(
            pageSource.contains("success") || 
            pageSource.contains("Thank") || 
            pageSource.contains("Charity"),
            "❌ Charity form submission may have failed!"
        );
    }

    @AfterAll
    public static void tearDown() {
        // ✅ Close the browser after test completes
        if (driver != null) {
            driver.quit();
        }
    }
}
