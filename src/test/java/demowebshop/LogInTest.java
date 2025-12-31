package demowebshop;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.text;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static specs.Specs.*;

public class LogInTest extends TestBase {

    @Step("Open Demo Web Shop site")
    @Test
    void loginViaApiAndContinueUi() {
        open("/");
        setAuthCookie();
        refresh();

        $("#topcartlink").click();
        $(".page-title").shouldHave(text("Shopping cart"));
    }

    @Step("Add product to cart via API")
    @Test
    void successfulAddingToCartApi() {
        String body = "product_attribute_75_5_31=96&product_attribute_75_6_32=100&product_attribute_75_3_33=102&product_attribute_75_8_35=107&product_attribute_75_8_35=108&addtocart_75.EnteredQuantity=1";

        given()
                .spec(request)
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .cookie("NOPCOMMERCE.AUTH", authCookie)
                .body(body)
                .when()
                .post("/addproducttocart/details/75/1")
                .then()
                .spec(response200)
                .body("success", is(true));
    }
}
