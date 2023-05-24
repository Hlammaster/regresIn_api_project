package tests;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;
import static org.hamcrest.Matchers.is;
import static specs.Specs.requestSpec;

public class RegistrationTests {

    @Test
    void successfulRegistrationTest() {
        String body = "{ \"email\": \"eve.holt@reqres.in\",\"password\": \"pistol\" }";

        given(requestSpec)
                .body(body)
                .when()
                .post("/register")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("id", is(4))
                .body("token", is("QpwL5tke4Pnpja7X4"));


    }

    @Test
    void unsuccessfulRegistrationTest() {
        String body = "{ \"email\": \"sydney@fife\" }";

        given(requestSpec)
                .body(body)
                .when()
                .post("/register")
                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error", is("Missing password"));

    }
}
