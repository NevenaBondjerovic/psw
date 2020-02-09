package basetests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@RunWith(Parameterized.class)
public class HomePageTest extends BaseTest {

    private static String HOME_URL = "http://localhost:4200/clinicalcentre/home";
    private static String LOGIN_URL = "http://localhost:4200/login";
    private static String CLINICS_PAGE = "http://localhost:4200/clinicalcentre/clinics";
    private static String HISTORY_PAGE = "http://localhost:4200/clinicalcentre/myprofile/history";
    private static String MEDICAL_RECORD_PAGE = "http://localhost:4200/clinicalcentre/myprofile/medicalrecord";
    private static String MY_PROFILE_PAGE = "http://localhost:4200/clinicalcentre/myprofile/profile";

    public HomePageTest(String driverType){
        this.driverType = driverType;
    }

    @After
    public void tearDown(){
        if(driver != null)
            driver.quit();
    }

    @Test
    public void clinicLinkForwardingToClinicsPage(){
        login();
        driver.findElement(By.linkText("Clinics")).click();
        WebDriverWait waitForLoad = new WebDriverWait(driver, 30);
        waitForLoad.until(ExpectedConditions.urlToBe(CLINICS_PAGE));

        Assert.assertEquals(CLINICS_PAGE, driver.getCurrentUrl());
    }

    @Test
    public void historyLinkForwardingToHistoryPage(){
        login();
        driver.findElement(By.linkText("History")).click();
        WebDriverWait waitForLoad = new WebDriverWait(driver, 30);
        waitForLoad.until(ExpectedConditions.urlToBe(HISTORY_PAGE));

        Assert.assertEquals(HISTORY_PAGE, driver.getCurrentUrl());
    }

    @Test
    public void medicalRecordLinkForwardingToMedicalRecordPage(){
        login();
        driver.findElement(By.linkText("Medical record")).click();
        WebDriverWait waitForLoad = new WebDriverWait(driver, 30);
        waitForLoad.until(ExpectedConditions.urlToBe(MEDICAL_RECORD_PAGE));

        Assert.assertEquals(MEDICAL_RECORD_PAGE, driver.getCurrentUrl());
    }

    @Test
    public void myProfileLinkForwardingToMyProfilePage(){
        login();
        driver.findElement(By.linkText("My profile")).click();
        WebDriverWait waitForLoad = new WebDriverWait(driver, 30);
        waitForLoad.until(ExpectedConditions.urlToBe(MY_PROFILE_PAGE));

        Assert.assertEquals(MY_PROFILE_PAGE, driver.getCurrentUrl());
    }

    private void login(){
        driver.get(LOGIN_URL);
        driver.findElement(By.name("username"))
                .sendKeys("n.b.131993@gmail.com");
        driver.findElement(By.name("password"))
                .sendKeys("password");
        driver.findElement(By.name("submit")).click();
        WebDriverWait waitForLoad = new WebDriverWait(driver, 30);
        waitForLoad.until(ExpectedConditions.urlToBe(HOME_URL));
    }

}
