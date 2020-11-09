import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import static org.hamcrest.CoreMatchers.is;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;


public class ProfileTest {
    private WebDriver driver;
    JavascriptExecutor js;

    @Before
    public void setUp() {
        String driverType = System.getenv("DRIVER");

        if (driverType.equals("CHROME")){
            driver = new ChromeDriver();
        } else {
            driver = new FirefoxDriver();
        }
        js = (JavascriptExecutor) driver;
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
        System.setProperty("webdriver.gecko.driver", "/usr/local/bin/geckodriver");
    }

    @After
    public void tearDown(){
        driver.quit();
    }

    @Test
    public void viewProfileTest() throws InterruptedException {
        // Navigate To The URL
        driver.get("https://soundcloud.com/");
        // Configure your Browser
        driver.manage().window().setSize(new Dimension(960, 1053));
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"content\"]/div/div/div[1]/div/div[2]/button[1]")));
        driver.findElement(By.xpath("//*[@id=\"content\"]/div/div/div[1]/div/div[2]/button[1]")).click();
        driver.switchTo().frame(0);

        // click button facebook
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"app\"]/div/div/div/div/div[1]/div[1]/button")));
        driver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div/div/div[1]/div[1]/button")).click();

        ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(tabs2.get(1));

        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.name("email")));
        driver.findElement(By.name("email")).click();
        driver.findElement(By.name("email")).sendKeys("dophin204198@gmail.com");
        driver.findElement(By.name("pass")).sendKeys("");

        driver.findElement(By.name("login")).click();

        // Switch back to original browser (first window)
        driver.switchTo().window(tabs2.get(0));

        //Icon Me
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/header[1]/div[1]/div[3]/div[2]/a[1]")));
        driver.findElement(By.xpath("/html[1]/body[1]/div[1]/header[1]/div[1]/div[3]/div[2]/a[1]")).click();

        //Profile  click
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Profile")));
        driver.findElement(By.linkText("Profile")).click();
    }
}

