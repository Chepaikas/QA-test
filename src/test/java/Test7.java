import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.openqa.selenium.By.cssSelector;

public class Test7 {

    private WebDriver driver;

    @Before
    public void start(){
        driver = new ChromeDriver();
    }
    @Test
    public void test7(){
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        for (int x = 0; x < driver.findElements(cssSelector("#box-apps-menu #app-")).size(); x++){
            driver.findElements(cssSelector("#box-apps-menu #app-")).get(x).click();
            driver.findElement(By.cssSelector("h1"));
            if(driver.findElements(By.cssSelector("#box-apps-menu #app- .docs")).size()!=0){
                for(int y = 0; y < driver.findElements(By.cssSelector("#box-apps-menu #app- .docs li")).size(); y++){
                    driver.findElements(By.cssSelector("#box-apps-menu #app- .docs li")).get(y).click();
                    driver.findElement(By.cssSelector("h1"));
                }
            }
        }
    }
    @After
    public void stop(){
        driver.quit();
        driver = null;
    }
}
