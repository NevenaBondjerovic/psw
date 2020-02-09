package e2etests;

import basetests.BaseTest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@RunWith(Parameterized.class)
public class ClinicsPageTest extends BaseTest {

    private static String HOME_URL = "http://localhost:4200/clinicalcentre/home";
    private static String LOGIN_URL = "http://localhost:4200/login";
    private static String CLINICS_PAGE = "http://localhost:4200/clinicalcentre/clinics";
    private static String AVAILABLE_APPOINTMENTS_PAGE = "http://localhost:4200/clinicalcentre/appointments";
    private static String AVAILABLE_DOCTORS_PAGE = "http://localhost:4200/clinicalcentre/doctors";
    private static String SEARCH_CLINICS = "http://localhost:4200/clinicalcentre/searchclinics";
    private static String SERVICE_NOT_AVAILABLE_ERROR_MESSAGE =
            "The service is not available at the moment. Please try again later.";

    public ClinicsPageTest(String driverType){
        this.driverType = driverType;
    }

    @Test
    public void showClinicDetailsOnClinicSelect(){
        goToClinicsPage();
        driver.findElement(By.tagName("tr")).findElement(By.tagName("td")).click();
        WebDriverWait waitForLoad = new WebDriverWait(driver, 30);
        waitForLoad.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("details")));

        Assert.assertEquals("Clinic details", driver.findElement(By.tagName("h5")).getText());
        Assert.assertNotNull(driver.findElement(By.className("dataSection")));
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
