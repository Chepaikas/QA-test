import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;


public class Test13 {

    private WebDriver driver;
    private WebDriverWait wait;

    private boolean element(By locator) {
        try {
            driver.findElement(locator);
            return true;
        }
        catch (NoSuchElementException ex){
            return false;
        }
    }
    @Before
    public void start(){
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver,10);
    }
    @Test
    public void test13(){
        driver.get("http://localhost/litecart");
        int nProd = 3;
        for (int x = 0; x<nProd; x++) {
            List<WebElement> prodHomePage = driver.findElements(By.cssSelector("#box-most-popular li"));
            prodHomePage.get(0).click();
            WebElement cart = driver.findElement(By.cssSelector("#cart .quantity"));
            String quantity = cart.getAttribute("textContent");
            int intQ = Integer.valueOf(quantity);
            if(element(By.cssSelector("[name='options[Size]']"))){
                driver.findElement(By.cssSelector("[name='options[Size]']")).click();
                driver.findElement(By.cssSelector("[name='options[Size]'] [value=Medium]")).click();
            }
            driver.findElement(By.cssSelector("button[value='Add To Cart']")).click();
            wait.until(ExpectedConditions.attributeContains(cart, "textContent", Integer.toString(intQ + 1)));
            if(x==nProd-1){
                driver.findElement(By.cssSelector("a.link[href*=checkout]")).click();
            }else{
                driver.navigate().back();
            }
        }
        wait.until(titleIs("Checkout | My Store"));
        WebElement sum = driver.findElement(By.cssSelector("#box-checkout-summary"));
        List <WebElement> Checkout = sum.findElements(By.cssSelector("td.item"));
        int types = Checkout.size();
        while (true){
            if(element(By.cssSelector("button[value=Remove]"))){
                WebElement remove = driver.findElement(By.cssSelector("button[value=Remove]"));
                wait.until(visibilityOf(remove));
                remove.click();
                wait.until(numberOfElementsToBe(By.cssSelector("td.item"), types-1));
                types--;
                continue;
            }
            else{
                wait.until(ExpectedConditions.stalenessOf(sum));
                break;
            }
        }
    }
    @After
    public void stop(){
        driver.quit();
        driver = null;
    }
}
