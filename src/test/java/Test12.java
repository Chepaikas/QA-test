import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class Test12 {

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
    private boolean Checked(By locator){
        WebElement checkbox = driver.findElement(locator);
        if(checkbox.getAttribute("checked")==null){
            return false;
        }
        else{
            if(checkbox.getAttribute("checked").contains("true")) {
                return true;
            }
            else {
                return false;
            }
        }
    }
    @Before
    public void start(){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver,10);
    }
    @Test
    public void test12() throws IOException {
        driver.get("http://localhost/litecart/admin");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        assertTrue(element(By.cssSelector("#sidebar")));
        driver.findElement(By.cssSelector("#sidebar [href*=catalog]")).click();
        driver.findElement(By.cssSelector("[href*=edit_product]")).click();

        assertTrue(element(By.cssSelector("#tab-general")));
        WebElement generalSet = driver.findElement(By.cssSelector("#tab-general"));
        generalSet.findElement(By.cssSelector("[name=status][value='1']")).click();
        generalSet.findElement(By.cssSelector("[name='name[en]']")).sendKeys("Blue Duck");
        generalSet.findElement(By.cssSelector("[name=code]")).sendKeys("rdxxx");

        if(Checked(By.cssSelector("[data-name='Root']"))==false){
            generalSet.findElement(By.cssSelector("[data-name=Root]")).click();
        }
        if(Checked(By.cssSelector("[data-name='Rubber Ducks']"))==false){
            generalSet.findElement(By.cssSelector("[data-name='Rubber Ducks']")).click();
        }
        if(Checked(By.cssSelector("[data-name=Subcategory]"))==true){
            generalSet.findElement(By.cssSelector("[data-name=Subcategory]")).click();
        }
        List<WebElement> ProdGroups = generalSet.findElements(By.cssSelector("[name='product_groups[]']"));
        for(int x = 0; x < ProdGroups.size(); x++){
            if(ProdGroups.get(x).getAttribute("checked")==null || ProdGroups.get(x).getAttribute("checked").contains("true")==false){
                ProdGroups.get(x).click();
            }
        }
        new Actions(driver)
                .moveToElement(generalSet.findElement(By.cssSelector("[name=quantity]")))
                .click()
                .keyDown(Keys.CONTROL).sendKeys("a")
                .keyUp(Keys.CONTROL)
                .sendKeys(Keys.DELETE)
                .sendKeys("10")
                .perform();
        generalSet.findElement(By.cssSelector("[name=quantity_unit_id] option[value='1']")).click();
        generalSet.findElement(By.cssSelector("[name=delivery_status_id] option[value='1']")).click();
        generalSet.findElement(By.cssSelector("[name=sold_out_status_id] option[value='2']")).click();

        File b = new File("../QA-test/Upload/Blue-Duck.jpg");
        String absolute = b.getCanonicalPath();
        driver.findElement(By.cssSelector("[type=file]")).sendKeys(absolute);

        String browserName = (String) ((HasCapabilities) driver).getCapabilities().getCapability(CapabilityType.BROWSER_NAME);

        if(browserName.equals("time")){
            generalSet.findElement(By.cssSelector("[name=date_valid_from]")).sendKeys("2021-12-14");
            generalSet.findElement(By.cssSelector("[name=date_valid_to]")).sendKeys("2022-12-14");
        }else{
            generalSet.findElement(By.cssSelector("[name=date_valid_from]")).sendKeys("14122021");
            generalSet.findElement(By.cssSelector("[name=date_valid_to]")).sendKeys("14122022");
        }
        driver.findElement(By.cssSelector("[href='#tab-information']")).click();
        assertTrue(element(By.cssSelector("#tab-information")));
        WebElement informationSet = driver.findElement(By.cssSelector("#tab-information"));
        informationSet.findElement(By.cssSelector("[name=manufacturer_id] option[value='1']")).click();

        informationSet.findElement(By.cssSelector("[name=keywords]")).sendKeys("Duck Blue Rubber");
        informationSet.findElement(By.cssSelector("[name='short_description[en]']")).sendKeys("Blue rubber duck for your toilet");
        informationSet.findElement(By.cssSelector(".trumbowyg-editor")).sendKeys("A duck for those who can no longer go hunting on their own.");
        informationSet.findElement(By.cssSelector("[name='head_title[en]']")).sendKeys("Blue_duck");
        informationSet.findElement(By.cssSelector("[name='meta_description[en]']")).sendKeys("medical duck");

        driver.findElement(By.cssSelector("[href='#tab-prices']")).click();
        assertTrue(element(By.cssSelector("#tab-prices")));
        WebElement priceSet = driver.findElement(By.cssSelector("#tab-prices"));
        new Actions(driver)
                .moveToElement(priceSet.findElement(By.cssSelector("[name=purchase_price]"))).click()
                .keyDown(Keys.CONTROL).sendKeys("a")
                .keyUp(Keys.CONTROL)
                .sendKeys(Keys.DELETE)
                .sendKeys("10")
                .perform();
        priceSet.findElement(By.cssSelector("[name*='currency_code'] option[value='USD']")).click();
        priceSet.findElement(By.cssSelector("[name='tax_class_id'] option[value]")).click();
        new Actions(driver)
                .moveToElement(priceSet.findElement(By.cssSelector("[name='gross_prices[USD]']"))).click()
                .keyDown(Keys.CONTROL).sendKeys("a")
                .keyUp(Keys.CONTROL)
                .sendKeys(Keys.DELETE)
                .sendKeys("20")
                .perform();
        new Actions(driver)
                .moveToElement(priceSet.findElement(By.cssSelector("[name='gross_prices[EUR]']"))).click()
                .keyDown(Keys.CONTROL).sendKeys("a")
                .keyUp(Keys.CONTROL)
                .sendKeys(Keys.DELETE)
                .sendKeys("17,67")
                .perform();

        driver.findElement(By.cssSelector("[name=save]")).click();
        assertTrue(element(By.cssSelector("[name=catalog_form]")));
        List<WebElement> categories = driver.findElements(By.cssSelector("a[href*='category']"));
        for(int x = 0; x < categories.size(); x++){
            if(categories.get(x).getAttribute("textContent")!=null && categories.get(x).getAttribute("textContent").contains("Rubber ducks")){
                categories.get(x).click();
            }
        }
        List<WebElement> products = driver.findElements(By.cssSelector("a[href*='&product']"));
        for(int x = 0; x < products.size(); x++){
            if(products.get(x).getAttribute("textContent")!=null && products.get(x).getAttribute("textContent").contains("Blue Duck")){
                products.get(x).click();
                break;
            }
        }
        wait.until(titleIs("Edit Product: Blue Duck | My Store"));
    }
    @After
    public void stop(){
        driver.quit();
        driver = null;
    }
}