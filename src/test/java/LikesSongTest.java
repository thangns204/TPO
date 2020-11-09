import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class LikesSongTest {
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
    public void likeSongTest() throws InterruptedException {
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

        //click profileIcon
        new WebDriverWait(driver, 7).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html[1]/body[1]/div[1]/header[1]/div[1]/div[3]/div[2]/a[1]")));
        driver.findElement(By.xpath("/html[1]/body[1]/div[1]/header[1]/div[1]/div[3]/div[2]/a[1]")).click();

        //LikeIcon  click
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Likes")));
        driver.findElement(By.linkText("Likes")).click();

        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.className("lazyLoadingList__list")));
        WebElement elements = driver.findElement(By.className("lazyLoadingList__list"));
        List<WebElement> songs = elements.findElements(By.tagName("span"));
        System.out.println(songs.size());

        {
           // WebElement element = driver.findElement(By.xpath("//button[contains(text(),'Save')]"));
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            jse.executeScript("arguments[0].click()", songs.get(0));
        }

        /*
        //Unlike  click
        {
            WebElement element1 = driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[3]/div[1]/div/div[1]/div/div/div[2]/div/div/button[1]"));
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            jse.executeScript("arguments[0].click()", element1);
        }

        Thread.sleep(1000);

        //like  click
        {
            WebElement element2 = driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[3]/div[1]/div/div[1]/div/div/div[2]/div/div/button[1]"));
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            jse.executeScript("arguments[0].click()", element2);
        }
                Thread.sleep(1000);
         */

    }
}
