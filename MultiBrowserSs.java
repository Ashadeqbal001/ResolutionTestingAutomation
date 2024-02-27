package m1;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.OutputType;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MultiBrowserSs {
    public static void main(String[] args) {
        // Test with Chrome
        testWithBrowser("Chrome");

        // Test with Firefox
        testWithBrowser("Firefox");

        // Test with Safari
        testWithBrowser("Safari");
    }

    public static void testWithBrowser(String browserName) {
        WebDriver driver = null;

        try {
            // Initialize WebDriver based on browserName
            switch (browserName) {
                case "Chrome":
                    // Set ChromeDriver path and initialize ChromeDriver
                    System.setProperty("webdriver.chrome.driver", "C:\\selenium\\chromedriver.exe");
                    driver = new ChromeDriver();
                    break;
                
                case "Firefox":
                    // Initialize SafariDriver
                	System.setProperty("webdriver.firefox.driver", "C:\\selenium\\geckodriver.exe");
                    driver = new FirefoxDriver();
                    break;
                    
                    case "safari":
                        // Initialize SafariDriver
                    	System.setProperty("webdriver.edge.driver","D:\\selenium latest version\\msedgedriver.exe");
                	driver = new EdgeDriver();
                        break;
                    default:
                        System.out.println("Unsupported browser: " + browserName);
                        return;
                        
                    
                
            }

            // Navigate to a website
            driver.get("https://www.getcalley.com/how-calley-auto-dialer-app-works");

            // Take screenshots for desktop and mobile resolutions
            takeScreenshots(driver, browserName, "Desktop", new String[]{"1920x1080", "1366x768", "1536x864"});
            takeScreenshots(driver, browserName, "Mobile", new String[]{"360x640", "414x896", "375x667"});

            // Other test steps...
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the browser
            if (driver != null) {
                driver.quit();
            }
        }
    }

    public static void takeScreenshots(WebDriver driver, String browserName, String deviceType, String[] resolutions) {
        for (String resolution : resolutions) {
            // Get current date and time
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
            String currentDateTime = dateFormat.format(new Date());

            // Get device name
            String deviceName = deviceType;

            try {
                // Take screenshot
                File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

                // Create folder path
                String folderPath = "screenshots/" + deviceName + "/" + resolution + "/";
                File folder = new File(folderPath);
                if (!folder.exists()) {
                    folder.mkdirs();
                }

                // Create file name with date and time
                String fileName = folderPath + "Screenshot-" + browserName + "-" + currentDateTime + ".png";

                // Save screenshot
                FileUtils.copyFile(screenshotFile, new File(fileName));
                System.out.println("Screenshot saved successfully at: " + fileName);
            } catch (Exception e) {
                System.out.println("Failed to save screenshot: " + e.getMessage());
            }
        }
    }
}

