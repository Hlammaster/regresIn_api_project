package tests;

import models.UserModel;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static specs.Specs.*;

public class LoginTests {
    @Test
    void successfulLogin() {
        UserModel userModel = new UserModel();
        userModel.setEmail("eve.holt@reqres.in");
        userModel.setPassword("cityslicka");
        given(requestSpec)
                .body(userModel)
                .when()
                .post("/login")
                .then()
                .spec(response200)
                .body("token", is("QpwL5tke4Pnpja7X4"));
    }

    @Test
    void unsuccessfulLogin() {

        UserModel userModel = new UserModel();
        userModel.setEmail("peter@klaven");
        given(requestSpec)
                .body(userModel)
                .when()
                .post("/login")
                .then()
                .spec(response400)
                .body("error", is("Missing password"));
    }
}

