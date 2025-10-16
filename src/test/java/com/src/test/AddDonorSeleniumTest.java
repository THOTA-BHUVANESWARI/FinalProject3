package com.src.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class AddDonorSeleniumTest {

    public static void main(String[] args) {
        WebDriver driver = null;

        try {
            // Setup ChromeDriver automatically
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().window().maximize();

            // Open the Add Donor page
            String BASE_URL = "http://localhost:8080/project/addDonor.jsp";
            driver.get(BASE_URL);

            // Fill out the form fields
            driver.findElement(By.name("name")).sendKeys("bobby");
            driver.findElement(By.name("email")).sendKeys("alice4@example.com");
            driver.findElement(By.name("phone")).sendKeys("9876543210");
            driver.findElement(By.name("address")).sendKeys("123 Charity Street");
            driver.findElement(By.name("password")).sendKeys("Test@123");
            driver.findElement(By.name("confirmpassword")).sendKeys("Test@123");

            // Click the submit button
            WebElement submitButton = driver.findElement(By.xpath("/html/body/form/input[7]"));
            submitButton.click();

            // Wait for the page to process submission
            Thread.sleep(2000);

            // Check the page source for success indication
            String pageSource = driver.getPageSource();
            if (pageSource.contains("success") || pageSource.contains("Thank") || pageSource.contains("Donor")) {
                System.out.println("✅ Donor form submitted successfully!");
            } else {
                System.out.println("❌ Donor form submission may have failed!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close browser
            if (driver != null) {
                driver.quit();
            }
        }
    }
}
