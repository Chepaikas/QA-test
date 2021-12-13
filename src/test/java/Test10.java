import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;


public class Test10 {


    private WebDriver driver;

    @Before
    public void start(){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }
    @Test
    public void productName() throws Exception{
        driver.get("http://localhost/litecart");
        WebElement product = driver.findElement(By.cssSelector("#box-campaigns li.product"));
        String HomePage = product.findElement(By.cssSelector("div.name")).getText();
        product.click();
        String ProductPage = driver.findElement(By.cssSelector("h1.title")).getText();
        if(HomePage.equals(ProductPage)==false){
            throw new Exception("Названия на главной странице и странице товара не совпадают");
        }
    }
    @Test
    public void productPrices() throws Exception{
        driver.get("http://localhost/litecart");
        WebElement product = driver.findElement(By.cssSelector("#box-campaigns li.product"));
        String RegPriceHomePage = product.findElement(By.cssSelector(".regular-price")).getText();
        String CamPriceHomePage = product.findElement(By.cssSelector(".campaign-price")).getText();
        product.click();
        String RegPriceProductPage = driver.findElement(By.cssSelector("#box-product .regular-price")).getText();
        String CamPriceProductPage = driver.findElement(By.cssSelector("#box-product .campaign-price")).getText();
        if(RegPriceHomePage.equals(RegPriceProductPage)==false){
            throw new Exception("Обычная цена на главной странице и на странице товара не совпадают");
        }
        if(CamPriceHomePage.equals(CamPriceProductPage)==false){
            throw new Exception("Акционная цена на главной странице и на странице товара не совпадают");
        }
    }
    @Test
    public void regPrice() throws Exception{
        driver.get("http://localhost/litecart");
        WebElement ProductPriceHomePage = driver.findElement(By.cssSelector("#box-campaigns li.product .regular-price"));
        String RegPriceColorHomePage = ProductPriceHomePage.getCssValue("color");
        String RegPriceColorHomePagePrecut = RegPriceColorHomePage.substring(RegPriceColorHomePage.indexOf("(")+1);
        String RegPriceColorHomePageCut = RegPriceColorHomePagePrecut.substring(0,RegPriceColorHomePagePrecut.length()-1);
        String[] RegPrice1 = RegPriceColorHomePageCut.split(", ");
        int y1 = Integer.valueOf(RegPrice1[0]);
        for(int x = 1; x < 3; x++){
            if (Integer.valueOf(RegPrice1[x]) != y1){
                throw new Exception("Цвет обычной цены на главной странице не серый");
            }
        }
        System.out.println(ProductPriceHomePage.getCssValue("text-decoration-line"));
        if (ProductPriceHomePage.getCssValue("text-decoration-line").equals("line-through") == false){
            throw new Exception("Обычная цена на главной странице не перечеркнута");
        }
        ProductPriceHomePage.click();
        WebElement ProductPriceProductPage = driver.findElement(By.cssSelector("#box-product .regular-price"));
        String RegPriceColorProductPage = ProductPriceProductPage.getCssValue("color");
        String RegPriceColorProductPagePrecut = RegPriceColorHomePage.substring(RegPriceColorProductPage.indexOf("(")+1);
        String RegPriceColorProductPageCut = RegPriceColorProductPagePrecut.substring(0,RegPriceColorProductPagePrecut.length()-1);
        String[] RegPrice2 = RegPriceColorProductPageCut.split(", ");
        int y2 = Integer.valueOf(RegPrice2[0]);
        for(int x = 1; x < 3; x++){
            if (Integer.valueOf(RegPrice2[x]) != y2){
                throw new Exception("Цвет обычной цены на странице товара не серый");
            }
        }
        if (ProductPriceProductPage.getCssValue("text-decoration-line").equals("line-through") == false) {
            throw new Exception("Обычная цена на странице товара не перечеркнута");
        }
    }
    @Test
    public void camPrice() throws Exception{
        driver.get("http://localhost/litecart");
        WebElement ProductPriceHomePage = driver.findElement(By.cssSelector("#box-campaigns li.product .campaign-price"));
        String CamPriceColorHomePage = ProductPriceHomePage.getCssValue("color");
        String CamPriceColorHomePagePrecut = CamPriceColorHomePage.substring(CamPriceColorHomePage.indexOf("(")+1);
        String CamPriceColorHomePageCut = CamPriceColorHomePagePrecut.substring(0,CamPriceColorHomePagePrecut.length()-1);
        String[] CamPrice1 = CamPriceColorHomePageCut.split(", ");
        if (Integer.valueOf(CamPrice1[1]) != 0 || Integer.valueOf(CamPrice1[2]) != 0 ){
            throw new Exception("Цвет акционной цены на главной странице не красный");
        }
        String FontHomePage = ProductPriceHomePage.getCssValue("font-weight");
        if (FontHomePage.equals("700") == false && FontHomePage.equals("800") == false && FontHomePage.equals("900") == false){
            throw new Exception("Акционная цена на главной странице не выделена жирным");
        }
        ProductPriceHomePage.click();
        WebElement ProductPriceProductPage = driver.findElement(By.cssSelector("#box-product .campaign-price"));
        String CamPriceColorProductPage = ProductPriceProductPage.getCssValue("color");
        String CamPriceColorProductPagePrecut = CamPriceColorHomePage.substring(CamPriceColorProductPage.indexOf("(")+1);
        String CamPriceColorProductPageCut = CamPriceColorProductPagePrecut.substring(0,CamPriceColorProductPagePrecut.length()-1);
        String[] CamPrice2 = CamPriceColorProductPageCut.split(", ");
        if (Integer.valueOf(CamPrice2[1]) != 0 || Integer.valueOf(CamPrice2[2]) != 0 ){
            throw new Exception("Цвет акционной цены на странице товара не красный");
        }
        String FontProductPage = ProductPriceProductPage.getCssValue("font-weight");
        if (FontProductPage.equals("700") == false && FontProductPage.equals("800") == false && FontProductPage.equals("900") == false) {
            throw new Exception("Акционная цена на странице товара не выделена жирным");
        }
    }
    @Test
    public void priceSize() throws Exception{
        driver.get("http://localhost/litecart");
        WebElement RegPriceHomePage = driver.findElement(By.cssSelector("#box-campaigns li.product .regular-price"));
        WebElement CamPriceHomePage = driver.findElement(By.cssSelector("#box-campaigns li.product .campaign-price"));;
        String RegPriceFontHomePage = RegPriceHomePage.getCssValue("font-size");
        String CamPriceFontHomePage = CamPriceHomePage.getCssValue("font-size");
        String RegPriceFontHomePageCut = RegPriceFontHomePage.substring(0,RegPriceFontHomePage.length()-2);
        String CamPriceFontHomePageCut = CamPriceFontHomePage.substring(0, CamPriceFontHomePage.length()-2);
        Double SRegPriceFontHomePage = Double.parseDouble(RegPriceFontHomePageCut);
        Double SCamPriceFontHomePage = Double.parseDouble(CamPriceFontHomePageCut);
        if(SCamPriceFontHomePage<SRegPriceFontHomePage){
            throw new Exception("На главной странице размер обычной больше или равен акционной");
        }
        RegPriceHomePage.click();

        WebElement RegPriceProductPage = driver.findElement(By.cssSelector("#box-product .regular-price"));
        WebElement CamPriceProductPage = driver.findElement(By.cssSelector("#box-product .campaign-price"));
        String RegPriceFontProductPage = RegPriceProductPage.getCssValue("font-size");
        String CamPriceFontProductPage = CamPriceProductPage.getCssValue("font-size");
        String RegPriceFontProductPageCut = RegPriceFontProductPage.substring(0,RegPriceFontProductPage.length()-2);
        String CamPriceFontProductPageCut = CamPriceFontProductPage.substring(0, CamPriceFontProductPage.length()-2);
        Double SRegPriceFontProductPage = Double.parseDouble(RegPriceFontProductPageCut);
        Double SCamPriceFontProductPage = Double.parseDouble(CamPriceFontProductPageCut);
        if(SCamPriceFontProductPage<SRegPriceFontProductPage){
            throw new Exception("На странице товара размер обычной больше или равен акционной");
        }
    }
    @After
    public void stop(){
        driver.quit();
        driver = null;
    }
}
