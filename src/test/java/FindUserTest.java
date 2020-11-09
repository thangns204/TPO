import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;


public class FindUserTest {
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
    public void userSearch() throws InterruptedException {
        // Navigate To The URL
        driver.get("https://soundcloud.com/");
        driver.manage().window().setSize(new Dimension(960, 1053));

        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"content\"]/div/div/div[2]/div/div[1]/span/span/form/input")));
        driver.findElement(By.xpath("//*[@id=\"content\"]/div/div/div[2]/div/div[1]/span/span/form/input")).click();
        driver.findElement(By.xpath("//*[@id=\"content\"]/div/div/div[2]/div/div[1]/span/span/form/input")).sendKeys("50cent");
        driver.findElement(By.xpath("//*[@id=\"content\"]/div/div/div[2]/div/div[1]/span/span/form/input")).sendKeys(Keys.ENTER);

        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.linkText("People")));
        driver.findElement(By.linkText("People")).click();

        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"content\"]/div/div/div[3]/div/div/div/ul/li[1]/div/div/div/h2/a")));
        driver.findElement(By.xpath("//*[@id=\"content\"]/div/div/div[3]/div/div/div/ul/li[1]/div/div/div/h2/a")).click();
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"content\"]/div/div[2]/div/div[1]/div/div[2]/h3")));
        System.out.println(driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[2]/div/div[1]/div/div[2]/h3")).getText());

        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"content\"]/div/div[4]/div[2]/div/article[1]/table/tbody/tr")));
        WebElement elements = driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[4]/div[2]/div/article[1]/table/tbody/tr"));
        List<WebElement> links = elements.findElements(By.tagName("td"));

        System.out.println("Number of elements:" + links.size());

        for (int i = 0; i < links.size(); i++) {
            System.out.println(links.get(i).findElement(By.className("infoStats__value")).getText());
        }
    }
}

