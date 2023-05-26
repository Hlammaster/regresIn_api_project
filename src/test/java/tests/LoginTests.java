package tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import models.ResponseModel;
import models.UserModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static specs.Specs.*;
@Owner("Evgenii Goncharov")
@Epic("Api test")
@Feature("Login tests")
public class LoginTests {
    @Test
    @DisplayName("Успешный логин пользователя")
    void successfulLogin() {
        UserModel userModel = new UserModel();
        userModel.setEmail("eve.holt@reqres.in");
        userModel.setPassword("cityslicka");
        ResponseModel response =   step("Make request", () ->
                given(requestSpec)
                        .body(userModel)
                        .when()
                        .post("/login")
                        .then()
                        .spec(response200)
                        .extract().as(ResponseModel.class));
        step("Verify response", () ->
        assertThat(response.getToken()).isEqualTo("QpwL5tke4Pnpja7X4"));
    }

    @Test
    @DisplayName("Неуспешный логин пользователя")
    void unsuccessfulLogin() {

        UserModel userModel = new UserModel();
        userModel.setEmail("peter@klaven");
        step("Make request", () ->
        given(requestSpec)
                .body(userModel)
                .when()
                .post("/login")
                .then()
                .spec(response400))
                .body("error", is("Missing password"));
    }
}

