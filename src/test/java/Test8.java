import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.List;

public class Test8 {

    private WebDriver driver;

    @Before
    public void start(){
        driver = new ChromeDriver();
    }
    @Test
    public void test8() throws Exception {
        driver.get("http://localhost/litecart");
        Thread.sleep(1000);
        List<WebElement> list = driver.findElements(By.cssSelector("li.product"));
        for (int x = 0; x < list.size(); x++){
            WebElement UnderVerification = list.get(x);
            if(UnderVerification.findElements(By.cssSelector(".sticker")).size() != 1){
                throw new Exception("Не на все товары приходится по стикеру");
            }
        }
    }
    @After
    public void stop(){
        driver.quit();
        driver = null;
    }
}