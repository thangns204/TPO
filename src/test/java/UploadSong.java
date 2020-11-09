import net.sourceforge.tess4j.TesseractException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class UploadSong {
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
    public void upload() throws InterruptedException, IOException, TesseractException {
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

        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.className("uploadButton__title")));
        driver.findElement(By.className("uploadButton__title")).click();
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.className("chooseFiles__input")));

        driver.findElement(By.className("chooseFiles__input")).sendKeys("/Users/thangns/Desktop/file_example_MP3_700KB.mp3");

        Thread.sleep(1000);
        {
            WebElement element = driver.findElement(By.xpath("//button[contains(text(),'Save')]"));
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            jse.executeScript("arguments[0].click()", element);
        }

        Thread.sleep(1000);
    }
}
