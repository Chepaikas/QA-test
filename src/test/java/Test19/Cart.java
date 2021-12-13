package Test19;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;
import static Test19.Tools.element;

public class Cart extends Page{

    public Cart(WebDriver driver) {
        super(driver);
    }
    public static void open() {
        driver.findElement(By.cssSelector("a.link[href*=checkout]")).click();
        wait.until(titleIs("Checkout | My Store"));
    }
    public static void deleteProd() {
        WebElement sum = driver.findElement(By.cssSelector("#box-checkout-summary"));
        List<WebElement> Checkout = sum.findElements(By.cssSelector("td.item"));
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
}
