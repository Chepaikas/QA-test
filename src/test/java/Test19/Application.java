package Test19;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Application {

    private WebDriver driver;

    private Main mainPage;
    private Product productPage;
    private Cart cartPage;

    public Application() {

        driver = new ChromeDriver();
        mainPage = new Main(driver);
        productPage = new Product(driver);
        cartPage = new Cart(driver);

    }
    public void chooseParameters(String Category) throws Exception {
        Main.open();
        Main.chooseCategory(Category);
        Main.firstProduct();
    }
    public void addToCart(int Quantity, String SizeIfPossible){
        Product.chooseQuantity(Quantity);
        Product.chooseSize(SizeIfPossible);
        Product.pressAddtoCart();
    }
    public void deleteCart(){
        Cart.open();
        Cart.deleteProd();
    }
    public void closeApplication(){
        driver.quit();
        driver = null;
    }
}
