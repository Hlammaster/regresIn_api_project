package tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import models.ResponseDataModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static specs.Specs.*;
@Owner("Evgenii Goncharov")
@Epic("Api test")
@Feature("Resource tests")
public class ResourceTests {
    @Test
    @DisplayName("Запрос списка пользователей")
    void getUsersListTest() {
        ResponseDataModel response =   step("Make request", () ->
                given(requestSpec)
                        .when()
                        .get("/users?page=2")
                        .then()
                        .spec(response200)
                        .extract().as(ResponseDataModel.class));
        step("Verify response", () ->
        assertThat(response.getData().get(0).getLastName().equals("Lawson")));
        assertThat(response.getData().get(1).getLastName().equals("Ferguson"));
        assertThat(response.getData().get(2).getLastName().equals("Funke"));


    }


    @Test
    @DisplayName("Пользователь не найден")
    void getSingleUserNotFoundTest() {
        step("Make request", () ->
        given(requestSpec)
                .log().uri()
                .when()
                .get("/users/23")
                .then()
                .spec(response404));


    }
}