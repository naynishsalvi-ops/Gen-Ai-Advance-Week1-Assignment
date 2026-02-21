import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class LoginTest {

    public static void main(String[] args) {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");

        WebDriver driver = new ChromeDriver(options);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://example.com/login");

        driver.findElement(By.id("username")).sendKeys("testuser");
        driver.findElement(By.id("password")).sendKeys("test@123");

        driver.findElement(By.id("loginBtn")).click();

        String expectedTitle = "Dashboard";
        String actualTitle = driver.getTitle();

        if (actualTitle.contains(expectedTitle)) {
            System.out.println("Login Successful");
        } else {
            System.out.println("Login Failed");
        }

        driver.quit();
    }
}
