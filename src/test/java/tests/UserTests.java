package tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import models.UserModel;
import models.UserResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static specs.Endpoints.CREATE_USER;
import static specs.Endpoints.UPDATE_USER;
import static specs.Specs.*;

@Owner("Evgenii Goncharov")
@Epic("Api test")
@Feature("User tests")
public class UserTests {
    @Test
    @DisplayName("Создание аккаунта")
    void createUserTest() {
        UserModel userModel = new UserModel();
        userModel.setName("morpheus");
        userModel.setJob("leader");
        UserResponseModel response = step("Make request", () ->
                given(requestSpec)
                        .body(userModel)
                        .when()
                        .post(CREATE_USER)
                        .then()
                        .spec(response201)
                        .extract().as(UserResponseModel.class));
        step("Verify response", () ->
                assertThat(response.getName()).isEqualTo("morpheus"));
        assertThat(response.getJob()).isEqualTo("leader");


    }

    @Test
    @DisplayName("Изменение данных аккаунта")
    void updateUserAccountTest() {
        UserModel userModel = new UserModel();
        userModel.setName("morpheus");
        userModel.setJob("zion resident");
        UserResponseModel response = step("Make request", () ->
                given(requestSpec)
                        .body(userModel)
                        .when()
                        .patch(UPDATE_USER)
                        .then()
                        .spec(response200)
                        .extract().as(UserResponseModel.class));
        step("Verify response", () ->
                assertThat(response.getName()).isEqualTo("morpheus"));
        assertThat(response.getJob()).isEqualTo("zion resident");

    }

    @Test
    @DisplayName("Удаление аккаунта")
    void deleteUserTest() {

        step("Make request", () ->
                given(requestSpec)
                        .when()
                        .delete(UPDATE_USER)
                        .then()
                        .spec(response204));


    }
}
