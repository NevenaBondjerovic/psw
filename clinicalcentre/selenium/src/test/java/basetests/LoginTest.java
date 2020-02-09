package basetests;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@RunWith(Parameterized.class)
public class LoginTest extends BaseTest {

    private static String HOME_URL = "http://localhost:4200/clinicalcentre/home";
    private static String LOGIN_URL = "http://localhost:4200/login";
    private static String USERNAME_OR_PASS_DOES_NOT_EXIST = "Provided username or password does not exist.";

    public LoginTest(String driverType){
        this.driverType = driverType;
    }

    @Test
    public void successfulLogin(){
        driver.get(LOGIN_URL);
        driver.findElement(By.name("username"))
                .sendKeys("n.b.131993@gmail.com");
        driver.findElement(By.name("password"))
                .sendKeys("password");
        driver.findElement(By.name("submit")).click();
        WebDriverWait waitForLoad = new WebDriverWait(driver, 30);
        waitForLoad.until(ExpectedConditions.urlToBe(HOME_URL));
        Assert.assertEquals(HOME_URL, driver.getCurrentUrl());
    }

    @Test
    public void failedLogin_providedUsernameDoesNotExist(){
        driver.get(LOGIN_URL);
        driver.findElement(By.name("username"))
                .sendKeys("someUnknownUsername@gmail.com");
        driver.findElement(By.name("password"))
                .sendKeys("password");
        driver.findElement(By.name("submit")).click();
        WebDriverWait waitForLoad = new WebDriverWait(driver, 30);
        waitForLoad.until(ExpectedConditions.presenceOfElementLocated(By.id("errorMessage")));
        Assert.assertEquals(LOGIN_URL, driver.getCurrentUrl());
        Assert.assertEquals(USERNAME_OR_PASS_DOES_NOT_EXIST,
                driver.findElement(By.id("errorMessage")).getText());
    }

    @Test
    public void failedLogin_wrongPassword(){
        driver.get(LOGIN_URL);
        driver.findElement(By.name("username"))
                .sendKeys("n.b.131993@gmail.com");
        driver.findElement(By.name("password"))
                .sendKeys("someUnknownPass");
        driver.findElement(By.name("submit")).click();
        WebDriverWait waitForLoad = new WebDriverWait(driver, 30);
        waitForLoad.until(ExpectedConditions.presenceOfElementLocated(By.id("errorMessage")));
        Assert.assertEquals(LOGIN_URL, driver.getCurrentUrl());
        Assert.assertEquals(USERNAME_OR_PASS_DOES_NOT_EXIST,
                driver.findElement(By.id("errorMessage")).getText());
    }

}
