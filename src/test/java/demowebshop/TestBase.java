package demowebshop;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.response.Response;
import models.UserGenerator;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.refresh;
import static io.restassured.RestAssured.given;
import static specs.Specs.*;

public class TestBase {

    protected String authCookie;

    @BeforeAll
    static void setUp() {
        Configuration.baseUrl = "https://demowebshop.tricentis.com";
        Configuration.browserSize = "1920x1080";

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

    protected void setAuthCookie(String cookie) {
        WebDriverRunner.getWebDriver().manage().addCookie(new Cookie("NOPCOMMERCE.AUTH", cookie));
        refresh();
    }
}
