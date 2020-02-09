package chrome;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class ClinicsPageTest extends BaseTest {

    private static String HOME_URL = "http://localhost:4200/clinicalcentre/home";
    private static String LOGIN_URL = "http://localhost:4200/login";
    private static String CLINICS_PAGE = "http://localhost:4200/clinicalcentre/clinics";
    private static String AVAILABLE_APPOINTMENTS_PAGE = "http://localhost:4200/clinicalcentre/appointments";
    private static String AVAILABLE_DOCTORS_PAGE = "http://localhost:4200/clinicalcentre/doctors";
    private static String SEARCH_CLINICS = "http://localhost:4200/clinicalcentre/searchclinics";

    @Test
    public void showClinicDetailsOnClinicSelect(){
        goToClinicsPage();
        driver.findElement(By.tagName("table")).findElement(By.tagName("tr")).click();
        WebDriverWait waitForLoad = new WebDriverWait(driver, 30);
        waitForLoad.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("details")));

        Assert.assertEquals("Clinic details", driver.findElement(By.tagName("h5")).getText());
    }

    @Test
    public void goToAvailableAppointments(){
        goToClinicsPage();
        driver.findElement(By.tagName("table")).findElement(By.tagName("tr")).click();
        WebDriverWait waitForLoad = new WebDriverWait(driver, 30);
        waitForLoad.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("details")));
        driver.findElement(By.linkText("Click here to schedule the appointment")).click();
        waitForLoad = new WebDriverWait(driver, 30);
        waitForLoad.until(ExpectedConditions.urlToBe(AVAILABLE_APPOINTMENTS_PAGE));

        Assert.assertEquals(AVAILABLE_APPOINTMENTS_PAGE, driver.getCurrentUrl());
    }

    @Test
    public void goToAvailableDoctors(){
        goToClinicsPage();
        driver.findElement(By.tagName("table")).findElement(By.tagName("tr")).click();
        WebDriverWait waitForLoad = new WebDriverWait(driver, 30);
        waitForLoad.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("details")));
        driver.findElement(By.linkText("Click here to see the doctors list")).click();
        waitForLoad = new WebDriverWait(driver, 30);
        waitForLoad.until(ExpectedConditions.urlContains("http://localhost:4200/clinicalcentre/doctors"));
        String url = driver.getCurrentUrl();
        Assert.assertEquals(AVAILABLE_DOCTORS_PAGE, substring(url));
    }

    @Test
    public void searchForAppointment(){
        goToClinicsPage();
        driver.findElement(By.linkText("Search for appointment")).click();
        WebDriverWait waitForLoad = new WebDriverWait(driver, 30);
        waitForLoad.until(ExpectedConditions.urlToBe(SEARCH_CLINICS));

        Assert.assertEquals(SEARCH_CLINICS, driver.getCurrentUrl());
    }

    private void goToClinicsPage(){
        driver.get(LOGIN_URL);
        driver.findElement(By.name("username"))
                .sendKeys("n.b.131993@gmail.com");
        driver.findElement(By.name("password"))
                .sendKeys("password");
        driver.findElement(By.name("submit")).click();
        WebDriverWait waitForLoad = new WebDriverWait(driver, 30);
        waitForLoad.until(ExpectedConditions.urlToBe(HOME_URL));
        driver.findElement(By.id("topbar")).findElement(By.linkText("Clinics")).click();
        waitForLoad = new WebDriverWait(driver, 30);
        waitForLoad.until(ExpectedConditions.urlToBe(CLINICS_PAGE));
    }

    private String substring(String url){
        String returnValue = url;
        for(int i=0;i<3;i++){
            returnValue = returnValue.substring(0, returnValue.lastIndexOf("/"));
        }
        return returnValue;
    }

}
