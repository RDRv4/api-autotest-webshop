package config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:${env}.properties",
        "classpath:local.properties",
})
public interface WebDriverConfig extends Config {

    @Key("browser")
    @DefaultValue("chrome")
    String browser();

    @Key("browserVersion")
    @DefaultValue("128.0")
    String browserVersion();

    @Key("browserResolution")
    @DefaultValue("1920x1080")
    String browserResolution();

    @Key("remoteUrl")
    String remoteUrl();

    @Key("baseUrl")
    @DefaultValue("https://demowebshop.tricentis.com")
    String baseUrl();

    @Key("defaultUserEmail")
    @DefaultValue("d.rudovich@gmial.com")
    String defaultUserEmail();

    @Key("defaultUserPassword")
    @DefaultValue("ROJneTpDbmkVwIg")
    String defaultUserPassword();
}
