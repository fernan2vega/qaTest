package Config;

import utils.getProperty;

import static org.testng.Assert.fail;

public class Config {

    public static final String URL = getUrl();
    public static final String PLATFORM = getPlatForm();

    private static String getUrl(){
        if(getProperty.prop("URL")== null){
            fail("please set the url with -DURL=<url> (ex. -DURL=\"https://www.google.com\")");
        }
        return  getProperty.prop("URL");
    }

    private static String getPlatForm(){
        if(getProperty.prop("PLATFORM") == null){
            fail("please set platform with -DPLATFORM=[chrome|firefox|edge|safari|mobile]");
        }
        String mp = getProperty.prop("PLATFORM");
        switch (mp){
            case "chrome":
            case "firefox":
            case "edge":
            case "safari":
            case "mobile":
                return mp;
            default:
                fail(String.format("Platform should be: androide or ios\nRecived: %s", mp));
        }
        return null;

    }
}
