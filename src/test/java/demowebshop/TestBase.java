package demowebshop;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.response.Response;
import models.UserGenerator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.Cookie;

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

    @BeforeEach
    void loginApi() {
        UserGenerator user = UserGenerator.builder()
                .email("d.rudovich@gmial.com")
                .password("ROJneTpDbmkVwIg")
                .build();

        Response response = given()
                .spec(request)
                .body(user)
                .post("/login")
                .then()
                .spec(response302)
                .extract().response();

        authCookie = response.getCookie("NOPCOMMERCE.AUTH");
        if (authCookie == null) {
            throw new RuntimeException("Авторизационный cookie не получен. Проверь email/пароль.");
        }
    }

    protected void setAuthCookie() {
        WebDriverRunner.getWebDriver().manage().addCookie(new Cookie("NOPCOMMERCE.AUTH", authCookie));
    }
}
