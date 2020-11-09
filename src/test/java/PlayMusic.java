import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class PlayMusic {
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
    public void play() throws InterruptedException {
        // Navigate To The URL
    // Navigate To The URL
        driver.get("https://soundcloud.com/");
        driver.manage().window().setSize(new Dimension(960, 1053));

        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"content\"]/div/div/div[2]/div/div[1]/span/span/form/input")));
        driver.findElement(By.xpath("//*[@id=\"content\"]/div/div/div[2]/div/div[1]/span/span/form/input")).click();
        driver.findElement(By.xpath("//*[@id=\"content\"]/div/div/div[2]/div/div[1]/span/span/form/input")).sendKeys("trinh dinh quang");
        driver.findElement(By.xpath("//*[@id=\"content\"]/div/div/div[2]/div/div[1]/span/span/form/input")).sendKeys(Keys.ENTER);

        // click to play
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"content\"]/div/div/div[3]/div/div/div/ul/li[1]/div/div/div/div[2]/div[1]/div/div/div[1]/a")));
        driver.findElement(By.xpath("//*[@id=\"content\"]/div/div/div[3]/div/div/div/ul/li[1]/div/div/div/div[2]/div[1]/div/div/div[1]/a")).click();
        System.out.println(driver.findElement(By.xpath("//*[@id=\"content\"]/div/div/div[3]/div/div/div/ul/li[1]/div/div/div/div[2]/div[1]/div/div/div[1]/a")).getText());
        //click pause
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"content\"]/div/div/div[3]/div/div/div/ul/li[1]/div/div/div/div[2]/div[1]/div/div/div[1]/a")));
        driver.findElement(By.xpath("//*[@id=\"content\"]/div/div/div[3]/div/div/div/ul/li[1]/div/div/div/div[2]/div[1]/div/div/div[1]/a")).click();
        System.out.println(driver.findElement(By.xpath("//*[@id=\"content\"]/div/div/div[3]/div/div/div/ul/li[1]/div/div/div/div[2]/div[1]/div/div/div[1]/a")).getText());

    }

}
