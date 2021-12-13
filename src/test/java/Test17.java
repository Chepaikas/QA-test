import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

public class Test17 {

    public WebDriver driver;
    public WebDriverWait wait;

    @Before
    public void start(){
        LoggingPreferences message = new LoggingPreferences();
        message.enable("browser", Level.ALL);

        ChromeOptions options = new ChromeOptions();
        options.setCapability(CapabilityType.LOGGING_PREFS, message);
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver,10);
    }
    @Test
    public void test17(){
        driver.get("http://localhost/litecart/admin");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");
        List<WebElement> prod = driver.findElements(By.cssSelector("a[href*='&product'][title]"));
        for(int x = 0; x < prod.size(); x++){
            prod.get(x).click();
            String ProdtName = driver.findElement(By.cssSelector("input[name='name[en]']")).getAttribute("value");
            List<LogEntry> logs = driver.manage().logs().get("browser").getAll();
            if(logs.size()!=0){
                System.out.println("При открытии страницы редактирования товара "+ProdtName+" в логе браузера появяются сообщения:");
                logs.forEach(log -> System.out.println(log));
            }
            driver.navigate().back();
            prod = driver.findElements(By.cssSelector("a[href*='&product'][title]"));
        }
    }
    @After
    public void stop(){
        driver.quit();
        driver = null;
    }
}
