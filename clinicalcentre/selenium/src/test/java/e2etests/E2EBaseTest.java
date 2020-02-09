package e2etests;

import org.junit.After;
import org.junit.Before;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Arrays;
import java.util.Collection;

public abstract class E2EBaseTest {

    private static final String CHROMEDRIVER_PATH = "src/libs/chromedriver";
    private static final String FIREFOXDRIVER_PATH = "src/libs/geckodriver";

    protected String driverType;
    protected WebDriver driver;

    @Before
    public void setUp(){
        if(this.driverType == "chrome"){
            System.setProperty("webdriver.chrome.driver", CHROMEDRIVER_PATH);
            driver = new ChromeDriver();
        }else {
            System.setProperty("webdriver.gecko.driver", FIREFOXDRIVER_PATH);
            driver = new FirefoxDriver();
        }
    }

    @After
    public void tearDown(){
        if(driver != null)
            driver.quit();
    }

    @Parameterized.Parameters
    public static Collection<Object[]> drivers() {
        return Arrays.asList( new Object[][] {
                {"chrome"},
                {"firefox"}
        });
    }

}
