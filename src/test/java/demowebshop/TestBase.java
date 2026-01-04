package demowebshop;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import config.WebDriverConfig;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.response.Response;
import models.UserGenerator;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

import static com.codeborne.selenide.Selenide.refresh;
import static io.restassured.RestAssured.given;
import static specs.Specs.*;

public class TestBase {

    protected String authCookie;
    private static final WebDriverConfig config = ConfigFactory.create(
            WebDriverConfig.class,
            System.getProperties()
    );

    @BeforeAll
    static void setWebDriver() {
        Configuration.baseUrl = config.baseUrl();
        Configuration.browser = config.browser();
        Configuration.browserVersion = config.browserVersion();
        Configuration.browserSize = config.browserResolution();
        Configuration.remote = config.remoteUrl();
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));
        Configuration.browserCapabilities = capabilities;

        System.out.println("System property EMAIL=" + System.getProperty("defaultUserEmail"));
        System.out.println("System property PASSWORD=" + System.getProperty("defaultUserPassword"));
        System.out.println("Owner EMAIL=" + config.defaultUserEmail());
        System.out.println("Owner PASSWORD=" + config.defaultUserPassword());
    }

    @BeforeEach
    void addListenerAllure() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    protected String getAuthCookie(UserGenerator user) {
        Response response = given()
                .spec(request)
                .body(user)
                .post("/login")
                .then()
                .spec(response302)
                .extract()
                .response();

        String cookie = response.getCookie("NOPCOMMERCE.AUTH");
        if (cookie == null) {
            throw new RuntimeException("Авторизационный cookie не получен. Проверь email/пароль.");
        }
        return cookie;
    }
//
//    protected void setAuthCookie(String cookie) {
//        WebDriverRunner.getWebDriver().manage().addCookie(new Cookie("NOPCOMMERCE.AUTH", cookie));
//        refresh();
//    }
}
