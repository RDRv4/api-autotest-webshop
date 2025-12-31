package demowebshop;

import models.UserGenerator;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static specs.Specs.request;

public class NegativeTest {

    @Test
    void loginWithInvalidPassword(){
        UserGenerator user = UserGenerator.builder()
                .email("d.rudovich@gmial.com")
                .password("wrongPassword123")
                .build();

        String response = given()
                .spec(request)
                .body(user)
                .post("https://demowebshop.tricentis.com/login")
                .then()
                .statusCode(200)
                .extract().asString();

        assertThat(response, containsString("Login was unsuccessful"));

    }
}
