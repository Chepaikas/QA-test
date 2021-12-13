import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

public class Test14 {

    private WebDriver driver;
    private WebDriverWait wait;

    private boolean element(By locator){
        try {
            driver.findElement(locator);
            return true;
        }catch (NoSuchElementException ex){
            return false;
        }
    }
    private ExpectedCondition<String> window(Set<String> oldWindows){
        return new ExpectedCondition<String>() {
            @Override
            public String apply(WebDriver driver) {
                Set<String> allWindows = driver.getWindowHandles();
                allWindows.removeAll(oldWindows);
                if(allWindows.size() > 0){
                    return allWindows.iterator().next();
                }
                else{
                    return null;
                }
            }
        };
    }
    @Before
    public void start(){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver,10);
    }
    @Test
    public void test14(){
        driver.get("http://localhost/litecart/admin");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        assertTrue(element(By.cssSelector("#sidebar")));
        driver.findElement(By.cssSelector("#sidebar [href*=countries]")).click();
        assertTrue(element(By.cssSelector("form[name=countries_form]")));
        driver.findElement(By.cssSelector("a.button[href*=country]")).click();
        assertTrue(element(By.cssSelector("#content form")));
        List<WebElement> Links = driver.findElements(By.cssSelector("i[class*=external-link]"));
        String Source = driver.getWindowHandle();
        Set <String> ExistingWindows = driver.getWindowHandles();
        for(int x = 0; x < Links.size(); x++){
            Links.get(x).click();
            String newWindow = wait.until(window(ExistingWindows));
            driver.switchTo().window(newWindow);
            assertTrue(element(By.cssSelector("title")));
            System.out.println((x+1)+". "+driver.getTitle());
            driver.close();
            driver.switchTo().window(Source);
        }
    }
    @After
    public void stop(){
        driver.quit();
        driver = null;
    }
}
