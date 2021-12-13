package Test19;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;
import static org.junit.Assert.assertTrue;
import static Test19.Tools.element;

public class Main extends Page{

    public Main(WebDriver driver) {
        super(driver);
    }
    public static String prodBox = "";

    public static void open() {
        driver.get("http://localhost/litecart");
    }
    public static void chooseCategory(String category) throws Exception {

        if(category.equals("Most Popular")){
            prodBox = "#box-most-popular ";
        }else {
            if (category.equals("Campaigns")){
                prodBox = "#box-campaigns ";
            }
            else {
                if (category.equals("Latest Products")){
                    prodBox = "#box-latest-products ";
                }else {
                    throw new Exception("Ошибка выбора категории товара");
                }
            }
        }
    }
    public static void firstProduct() {
        List<WebElement> prodHomePage = driver.findElements(By.cssSelector(prodBox + "li"));
        prodHomePage.get(0).click();
        assertTrue(element(By.cssSelector("#box-product")));
    }
}
