package m1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class UploadFile {

    public static void main(String[] args) {
        // Set ChromeDriver path
        System.setProperty("webdriver.chrome.driver", "C:\\selenium\\chromedriver.exe");

        // Initialize WebDriver
        WebDriver driver = new ChromeDriver();

        // Navigate to the login page
        driver.get("https://demo.dealsdray.com");

        // Enter credentials and login
        WebElement usernameInput = driver.findElement(By.xpath("/html/body/div[1]/div/div/div/div[2]/div/form/div[1]/div/div/input"));
        WebElement passwordInput = driver.findElement(By.xpath("/html/body/div[1]/div/div/div/div[2]/div/form/div[2]/div/div/input"));
        WebElement loginButton = driver.findElement(By.xpath("/html/body/div[1]/div/div/div/div[2]/div/form/div[3]/div/button"));

        usernameInput.sendKeys("prexo.mis@dealsdray.com");
        passwordInput.sendKeys("prexo.mis@dealsdray.com");
        loginButton.click();

        // Wait for dashboard page to load
        waitForPageLoad(driver);

        // Click on the order button
        WebElement orderButton = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div/div/div[2]/div/div[1]/div"));
        orderButton.click();

        // Wait for order page to load
        waitForPageLoad(driver);

        // Click on the add order button
        WebElement addOrderButton = driver.findElement(By.xpath("//*[@id='root']/div/div/div[2]/div/div/div[2]/div[2]/button"));
        addOrderButton.click();

        // Wait for add order page to load
        waitForPageLoad(driver);

        // Perform file upload
        WebElement fileInput = driver.findElement(By.xpath("//*[@id='mui-26']"));
        String filePath = "C:\\Users\\Ashad\\Downloads\\demo-data.xlsx";
        fileInput.sendKeys(filePath);

        // Assuming there's a submit button after file upload, click it
        WebElement submitButton = driver.findElement(By.xpath("//*[@id='root']/div/div/div[2]/div/div/div[2]/div[3]/button"));
        submitButton.click();

        // Wait for upload success message
        waitForUploadSuccess(driver);

        // Close the browser
        driver.quit();
    }

    // Helper method to wait for page load
    private static void waitForPageLoad(WebDriver driver) {
        // You can implement a more robust mechanism for waiting
        try {
            Thread.sleep(2000); // Wait for 2 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Helper method to wait for upload success message
    private static void waitForUploadSuccess(WebDriver driver) {
        // You can implement a more robust mechanism for waiting
        try {
            Thread.sleep(5000); // Wait for 5 seconds (adjust as necessary)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}