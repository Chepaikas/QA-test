import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

public class Test11 {

    private WebDriver driver;
    private boolean element(By locator){
        try {
            driver.findElement(locator);
            return true;
        }catch (NoSuchElementException ex){
            return false;
        }
    }
    private String randEmail(){
        String email = RandomStringUtils.randomAlphabetic(10) + "@gmail.com";
        return email;
    }
    private String randPass(){
        String password = RandomStringUtils.randomAlphabetic(5) + RandomStringUtils.randomAlphanumeric(5);
        return password;
    }
    @Before
    public void start(){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
    @Test
    public void test11(){
        driver.get("http://localhost/litecart/en/create_account");
        assertTrue(element(By.cssSelector("form")));
        WebElement form = driver.findElement(By.cssSelector("form"));
        form.findElement(By.name("tax_id")).sendKeys("donut");
        form.findElement(By.name("company")).sendKeys("Springfield nuclear power plant");
        form.findElement(By.name("firstname")).sendKeys("Homer");
        form.findElement(By.name("lastname")).sendKeys("Simpson");
        form.findElement(By.name("address1")).sendKeys("742 Evergreen Terrace");
        form.findElement(By.name("postcode")).sendKeys("12345");
        form.findElement(By.name("city")).sendKeys("Springfield");
        form.findElement(By.cssSelector(".select2-selection")).click();
        driver.findElement(By.cssSelector(".select2-search__field")).sendKeys("United States" + Keys.ENTER);
        form.findElement(By.cssSelector("[name=zone_code] [value=OR]")).click();
        String testEmail = randEmail();
        form.findElement(By.name("email")).sendKeys(testEmail);
        form.findElement(By.name("phone")).sendKeys("+123456789");
        String testPassword = randPass();
        form.findElement(By.name("password")).sendKeys(testPassword);
        form.findElement(By.name("confirmed_password")).sendKeys(testPassword);
        form.findElement(By.name("create_account")).click();

        assertTrue(element(By.cssSelector("#box-account")));
        driver.findElement(By.cssSelector("#box-account [href*=logout]")).click();

        assertTrue(element(By.cssSelector("#box-account-login")));
        WebElement login = driver.findElement(By.cssSelector("#box-account-login"));
        login.findElement(By.name("email")).sendKeys(testEmail);
        login.findElement(By.name("password")).sendKeys(testPassword);
        login.findElement(By.name("login")).click();

        assertTrue(element(By.cssSelector("#box-account")));
        driver.findElement(By.cssSelector("#box-account [href*=logout]")).click();
        assertTrue(element(By.cssSelector("#box-account-login")));
    }
    @After
    public void stop(){
        driver.quit();
        driver = null;
    }
}
