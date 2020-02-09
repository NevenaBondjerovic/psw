package chrome;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class TopBarTest extends BaseChromeTest {

    private static String HOME_URL = "http://localhost:4200/clinicalcentre/home";
    private static String LOGIN_URL = "http://localhost:4200/login";
    private static String CLINICS_PAGE = "http://localhost:4200/clinicalcentre/clinics";
    private static String REGISTRATION_REQUEST_PAGE = "http://localhost:4200/clinicalcentre/requests";
    private static String WORK_CALENDAR_PAGE = "http://localhost:4200/clinicalcentre/workcalendar";
    private static String APPOINTMENT_REQUESTS_PAGE = "http://localhost:4200/clinicalcentre/appointmentrequests";


    @Test
    public void clinicLinkOnTheTopbar(){
        login("n.b.131993@gmail.com", "password");
        driver.findElement(By.id("topbar")).findElement(By.linkText("Clinics")).click();
        WebDriverWait waitForLoad = new WebDriverWait(driver, 30);
        waitForLoad.until(ExpectedConditions.urlToBe(CLINICS_PAGE));

        Assert.assertEquals(CLINICS_PAGE, driver.getCurrentUrl());
    }

    @Test
    public void registrationRequestsLinkOnTheTopbar(){
        login("nevenafakultet13@gmail.com", "newpassword");
        driver.findElement(By.id("topbar")).findElement(By.linkText("Registration requests")).click();
        WebDriverWait waitForLoad = new WebDriverWait(driver, 30);
        waitForLoad.until(ExpectedConditions.urlToBe(REGISTRATION_REQUEST_PAGE));

        Assert.assertEquals(REGISTRATION_REQUEST_PAGE, driver.getCurrentUrl());
    }

    @Test
    public void workCalendarLinkOnTheTopbar(){
        login("user10@email.com", "password");
        driver.findElement(By.id("topbar")).findElement(By.linkText("Work calendar")).click();
        WebDriverWait waitForLoad = new WebDriverWait(driver, 30);
        waitForLoad.until(ExpectedConditions.urlToBe(WORK_CALENDAR_PAGE));

        Assert.assertEquals(WORK_CALENDAR_PAGE, driver.getCurrentUrl());
    }

    @Test
    public void appointmentRequestsLinkOnTheTopbar(){
        login("user10@email.com", "password");
        driver.findElement(By.id("topbar")).findElement(By.linkText("Appointment requests")).click();
        WebDriverWait waitForLoad = new WebDriverWait(driver, 30);
        waitForLoad.until(ExpectedConditions.urlToBe(APPOINTMENT_REQUESTS_PAGE));

        Assert.assertEquals(APPOINTMENT_REQUESTS_PAGE, driver.getCurrentUrl());
    }

    private void login(String username, String password){
        driver.get(LOGIN_URL);
        driver.findElement(By.name("username"))
                .sendKeys(username);
        driver.findElement(By.name("password"))
                .sendKeys(password);
        driver.findElement(By.name("submit")).click();
        WebDriverWait waitForLoad = new WebDriverWait(driver, 30);
        waitForLoad.until(ExpectedConditions.urlToBe(HOME_URL));
    }

}
