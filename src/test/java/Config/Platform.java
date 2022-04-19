package Config;


public enum Platform {
    CHROME("chrome"),
    FIREFOX("firefox"),
    SAFARI("safari"),
    EDGE("edge"),
    MOBILE("mobile");

    private final String platform;

    Platform(String platformName){
        this.platform = platformName;
    }
}
