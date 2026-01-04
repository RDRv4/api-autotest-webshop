package demowebshop;

import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import models.UserGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.text;
import static demowebshop.UserData.DEFAULT_USER;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static specs.Specs.*;

public class DemoWebShopTests extends TestBase {

    @Test
    @Owner("d.rudovich")
    @Feature("Authentication")
    @Story("Successful login")
    @Tags({@Tag("ui"), @Tag("auth")})
    @DisplayName("Successful Login")
    void loginWithValidCredentials() {
        String cookie = getAuthCookie(DEFAULT_USER);

        open("/");
        setAuthCookie(cookie);

        $("#topcartlink").click();
        $(".page-title").shouldHave(text("Shopping cart"));
    }

    @ParameterizedTest(name = "Login with email={0}, password={1}")
    @Owner("d.rudovich")
    @Feature("Authentication")
    @Story("Unsuccessful login (parameterized)")
    @Tags({@Tag("api"), @Tag("auth")})
    @DisplayName("Unsuccessful Login")
    @CsvSource({
            "'', 'ROJneTpDbmkVwIg'",          // empty login
            "'d.rudovich@gmial.com', ''",    // empty pass
            "'wrong@gmial.com', 'ROJneTpDbmkVwIg'", // invalid login
            "'d.rudovich@gmial.com', 'wrongPassword123'" // invalid pass
    })
    void loginWithInvalidCredentials(String email, String password) {

        UserGenerator user = UserGenerator.builder().email(email).password(password).build();

        String response = given()
                .spec(request)
                .body(user)
                .post("/login")
                .then()
                .spec(response200)
                .extract()
                .asString();

        assertThat(response, containsString("Login was unsuccessful"));
    }

    @Test
    @Owner("d.rudovich")
    @Feature("Shopping Cart")
    @Story("Add product and check quantity")
    @Tags({@Tag("ui"), @Tag("cart")})
    @DisplayName("Checking Quantity after adding to Cart")
    void addProductAndCheckQuantityInTheCart() {

        String cookie = getAuthCookie(DEFAULT_USER);
        String body = "addtocart_40.EnteredQuantity=1";


        step("LogIn by DEFAULT_USER and add the Product (API)", () -> {
            given()
                    .spec(request)
                    .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                    .cookie("NOPCOMMERCE.AUTH", cookie)
                    .body(body)
                    .when()
                    .post("/addproducttocart/details/40/1")
                    .then()
                    .spec(response200)
                    .body("success", is(true));

            open("/");
            setAuthCookie(cookie);
        });

        step("Checking Quantity after adding Product to Cart", () -> {
            $("#topcartlink").click();
            $(".cart-qty").shouldHave(text("(1)"));
        });

        step("Checking Quantity after removing Product", () -> {
            $(".qty input").setValue("0").pressEnter();
            $(".page-body").shouldHave(text("Your Shopping Cart is empty!"));
        });
    }

    @Test
    @Owner("d.rudovich")
    @Feature("Shopping Cart")
    @Story("Add product and check price")
    @Tags({@Tag("ui"), @Tag("cart")})
    @DisplayName("Checking Product price after adding to Cart")
    void addProductAndCheckPriceInTheCart() {

        String cookie = getAuthCookie(DEFAULT_USER);
        String body = "addtocart_40.EnteredQuantity=1";


        step("LogIn by DEFAULT_USER and add the Product (API)", () -> {
            given()
                    .spec(request)
                    .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                    .cookie("NOPCOMMERCE.AUTH", cookie)
                    .body(body)
                    .when()
                    .post("/addproducttocart/details/40/1")
                    .then()
                    .spec(response200)
                    .body("success", is(true));

            open("/");
            setAuthCookie(cookie);
        });

        step("Checking Product Quantity after adding Product to Cart", () -> {
            $("#topcartlink").click();
            $(".product-subtotal").shouldHave(text("1.00"));
        });

        step("Checking Product Quantity after removing Product", () -> {
            $(".qty input").setValue("0").pressEnter();
            $(".page-body").shouldHave(text("Your Shopping Cart is empty!"));
        });
    }
}
