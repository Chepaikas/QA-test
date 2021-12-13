package Test19;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import static org.junit.Assert.assertTrue;
import static Test19.Tools.element;


public class Product extends Page {

    public static int ToAdd;

    public Product(WebDriver driver) {
        super(driver);
    }

    public static void chooseQuantity(int quantity) {
        ToAdd = quantity;
        new Actions(driver)
                .moveToElement(driver.findElement(By.cssSelector("[name=quantity]"))).click()
                .keyDown(Keys.CONTROL).sendKeys("x")
                .keyUp(Keys.CONTROL)
                .sendKeys(Keys.DELETE)
                .sendKeys(Integer.toString(ToAdd))
                .perform();
    }
    public static void chooseSize(String possibleSize) {
        if(element(By.cssSelector("[name='options[Size]']"))){
            driver.findElement(By.cssSelector("[name='options[Size]']")).click();
            driver.findElement(By.cssSelector("[name='options[Size]'] [value="+possibleSize+"]")).click();
        }
    }
    public static void pressAddtoCart() {
        ConditionCart.amount();
        driver.findElement(By.cssSelector("button[value='Add To Cart']")).click();
        assertTrue(ConditionCart.confirmProd());
    }
}
