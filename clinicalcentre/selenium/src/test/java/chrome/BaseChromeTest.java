package chrome;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public abstract class BaseChromeTest {

    private static final String CHROMEDRIVER_PATH = "src/libs/chromedriver";
    protected WebDriver driver;

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", CHROMEDRIVER_PATH);
        driver = new ChromeDriver();
    }

    @After
    public void tearDown(){
        if(driver != null)
            driver.quit();
    }

}
