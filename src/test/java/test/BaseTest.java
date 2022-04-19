package test;

import Config.Config;
import Config.PlatformSelector;
import Config.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

public class BaseTest {

    protected static PlatformSelector selector = new PlatformSelector();
    //protected RemoteWebDriver driver;
    protected WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void beforeSuite(){
        assert Config.PLATFORM != null;
        driver = selector.getPlatform(Config.PLATFORM.equals("firefox")?
                Platform.FIREFOX:
                Platform.valueOf(Config.PLATFORM.toUpperCase()));
        driver.manage().timeouts().implicitlyWait(BasePageObject.DEFAULT_TIMEOUT.getSeconds(), TimeUnit.SECONDS);
        driver.get(Config.URL);
        driver.manage().window().maximize();
    }

    @AfterMethod(alwaysRun = true)
    public void afterSuite(){
        if(driver != null) driver.quit();
    }

    //public RemoteWebDriver getDriver(){return driver;}
    public WebDriver getDriver(){return driver;}
}
