import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertTrue;

public class Test9_1 {

    private WebDriver driver;

    private boolean isElementPresent(By locator) {
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
        driver.manage().timeouts();
    }
    @Test
    public void test9_1() throws Exception{
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        List<WebElement> country = driver.findElements(By.cssSelector("tr.row a:not([title])"));
        ArrayList <String> countryString = new ArrayList<>();
        for (int x = 0; x < country.size(); x++){
            countryString.add(x, country.get(x).getText());
        }
        ArrayList <String> countrysorted = new ArrayList<>();
        countrysorted = (ArrayList<String>) countryString.clone();
        Collections.sort(countrysorted);
        if (countryString.equals(countrysorted) == false){
            throw new Exception("Расположение стран не в алфавитном порядке");
        }
        List <WebElement> countries = driver.findElements(By.cssSelector("tr.row"));
        for(int y = 0; y < countries.size(); y++){
            if(countries.get(y).getAttribute("textContent").contains("0") == false){
                countries.get(y).findElement(By.cssSelector("a:not([title])")).click();
                assertTrue(isElementPresent(By.cssSelector("#table-zones")));
                List <WebElement> zone = driver.findElements(By.cssSelector("td [name*=name][name*=zones]"));
                ArrayList <String> zoneString = new ArrayList<>();
                for(int z = 0; z < zone.size(); z++){
                    zoneString.add(z, zone.get(z).getAttribute("value"));
                }
                ArrayList <String> zonesorted = new ArrayList<>();
                zonesorted = (ArrayList<String>) zoneString.clone();
                Collections.sort(zonesorted);
                if(zoneString.equals(zonesorted) == false){
                    throw new Exception("Расположение зон не в алфавитном порядке");
                }
                driver.navigate().back();
                countries = driver.findElements(By.cssSelector("tr.row"));
            }
        }
    }
    @After
    public void stop(){
        driver.quit();
        driver = null;
    }
}
