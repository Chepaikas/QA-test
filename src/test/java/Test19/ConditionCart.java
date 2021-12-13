package Test19;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ConditionCart {

    public static int currentAmount;
    private static WebDriver driver;
    private static WebDriverWait wait;


    public static int amount(){
        driver = Page.driver;
        WebElement quantityInCart = driver.findElement(By.cssSelector("#cart .quantity"));
        String quantity = quantityInCart.getAttribute("textContent");
        currentAmount = Integer.valueOf(quantity);
        return currentAmount;
    }

    public static boolean confirmProd(){
        driver = Page.driver;
        wait = new WebDriverWait(driver, 10);
        try {
            WebElement quantityInCart = driver.findElement(By.cssSelector("#cart .quantity"));
            wait.until(ExpectedConditions.attributeContains(quantityInCart, "textContent", Integer.toString(currentAmount + Product.ToAdd)));
            return true;
        }catch (Exception ex){
            return false;
        }
    }
}
