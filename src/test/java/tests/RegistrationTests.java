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
@Feature("Registration tests")
public class RegistrationTests {

    @Test
    @DisplayName("Успешная регистрация пользователя")
    void successfulRegistrationTest() {
        UserModel userModel = new UserModel();
        userModel.setEmail("eve.holt@reqres.in");
        userModel.setPassword("pistol");
        ResponseModel response =   step("Make request", () ->
                given(requestSpec)
                        .body(userModel)
                        .when()
                        .post("/register")
                        .then()
                        .spec(response200)
                        .extract().as(ResponseModel.class));
        step("Verify response", () ->
        assertThat(response.getToken()).isEqualTo("QpwL5tke4Pnpja7X4"));


    }

    @Test
    @DisplayName("Ошибка регистрации пользователя")
    void unsuccessfulRegistrationTest() {
        UserModel userModel = new UserModel();
        userModel.setEmail("sydney@fife");
        step("Make request", () ->
        given(requestSpec)
                .body(userModel)
                .when()
                .post("/register")
                .then()
                .spec(response400))
                .body("error", is("Missing password"));

    }
}
