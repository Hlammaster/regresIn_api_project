package tests;

import io.restassured.http.ContentType;
import models.UserModel;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;
import static org.hamcrest.Matchers.is;
import static specs.Specs.*;

public class RegistrationTests {

    @Test
    void successfulRegistrationTest() {
        UserModel userModel = new UserModel();
        userModel.setEmail("eve.holt@reqres.in");
        userModel.setPassword("pistol");
        given(requestSpec)
                .body(userModel)
                .when()
                .post("/register")
                .then()
                .spec(response200)
                .body("id", is(4))
                .body("token", is("QpwL5tke4Pnpja7X4"));


    }

    @Test
    void unsuccessfulRegistrationTest() {
        UserModel userModel = new UserModel();
        userModel.setEmail("sydney@fife");
        given(requestSpec)
                .body(userModel)
                .when()
                .post("/register")
                .then()
                .spec(response400)
                .body("error", is("Missing password"));

    }
}
