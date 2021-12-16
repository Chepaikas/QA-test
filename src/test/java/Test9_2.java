import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertTrue;

public class Test9_2 {

    private WebDriver driver;

    private boolean isElementPresent(By locator){
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
    }
    @Test
    public void task9_2() throws Exception{
        driver.get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        List<WebElement> country = driver.findElements(By.cssSelector("[name=geo_zones_form] td a:not([title])"));
        for(int x = 0; x < country.size(); x++){
            driver.findElements(By.cssSelector("[name=geo_zones_form] td a:not([title])")).get(x).click();
            assertTrue(isElementPresent(By.cssSelector("#table-zones")));
            Select zones = new Select(driver.findElement(By.cssSelector("select[name*=zone_code]")));
            ArrayList<String> zonesString = new ArrayList<>();
            for (WebElement zone : zones.getOptions()) {
                if (zone.getText() != "-- All Zones --") {
                    zonesString.add(zone.getText());
                }
            }
            ArrayList<String> zonesSorted = new ArrayList<>();
            zonesSorted = (ArrayList<String>) zonesString.clone();
            Collections.sort(zonesSorted);
            if(zonesString.equals(zonesSorted) == false){
                throw new Exception("Расположение зон не в алфавитном порядке");
            }
            driver.navigate().back();
        }
    }
    @After
    public void stop(){
        driver.quit();
        driver = null;
    }
}
