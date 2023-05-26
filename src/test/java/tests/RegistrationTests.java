package tests;

import models.ResponseModel;
import models.UserModel;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static specs.Specs.*;

public class RegistrationTests {

    @Test
    void successfulRegistrationTest() {
        UserModel userModel = new UserModel();
        userModel.setEmail("eve.holt@reqres.in");
        userModel.setPassword("pistol");
        ResponseModel response =
        given(requestSpec)
                .body(userModel)
                .when()
                .post("/register")
                .then()
                .spec(response200)
                .extract().as(ResponseModel.class);
        assertThat(response.getToken()).isEqualTo("QpwL5tke4Pnpja7X4");



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
