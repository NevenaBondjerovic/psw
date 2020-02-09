package basetests;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@RunWith(Parameterized.class)
public class WorkCalendarTest extends BaseTest {

    private static String HOME_URL = "http://localhost:4200/clinicalcentre/home";
    private static String LOGIN_URL = "http://localhost:4200/login";
    private static String WORK_CALENDAR_PAGE = "http://localhost:4200/clinicalcentre/workcalendar";

    public WorkCalendarTest(String driverType){
        this.driverType = driverType;
    }

    @Test
    public void showClinicDetailsOnClinicSelect(){
        goToWorkCalendarPage();
        driver.findElement(By.tagName("table"))
                .findElements(By.tagName("tr")).get(1)
                .findElement(By.tagName("td")).click();
        WebDriverWait waitForLoad = new WebDriverWait(driver, 30);
        waitForLoad.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("details")));

        Assert.assertEquals("Appointment details", driver.findElement(By.tagName("h5")).getText());
    }

    private void goToWorkCalendarPage(){
        driver.get(LOGIN_URL);
        driver.findElement(By.name("username"))
                .sendKeys("user10@email.com");
        driver.findElement(By.name("password"))
                .sendKeys("password");
        driver.findElement(By.name("submit")).click();
        WebDriverWait waitForLoad = new WebDriverWait(driver, 30);
        waitForLoad.until(ExpectedConditions.urlToBe(HOME_URL));
        driver.findElement(By.id("topbar")).findElement(By.linkText("Work calendar")).click();
        waitForLoad = new WebDriverWait(driver, 30);
        waitForLoad.until(ExpectedConditions.urlToBe(WORK_CALENDAR_PAGE));
    }
}
