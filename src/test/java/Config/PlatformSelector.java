package Config;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import static Config.Platform.*;


public class PlatformSelector {

    //private RemoteWebDriver driver;
    private WebDriver driver;

    public WebDriver getPlatform(Platform platform) {
        switch (platform){
            case CHROME:
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--ignore-ssl-errors=yes", "--ignore-certificate-errors", "--disable-notifications");

                DesiredCapabilities capabilities = new DesiredCapabilities();
                capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);

                //driver = new RemoteWebDriver(URI.create("url").toURL(), capabilities);
                System.setProperty("webdriver.chrome.driver", "C:\\Users\\fernan\\Documents\\chromedriver\\chromedriver.exe");
                driver = new ChromeDriver(capabilities);
                break;

            case FIREFOX:
            case EDGE:
            case SAFARI:
            case MOBILE:
        }
        return driver;
    }

}
