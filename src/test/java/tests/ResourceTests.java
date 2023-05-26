package tests;

import models.ResponseDataModel;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.is;
import static specs.Specs.*;

public class ResourceTests {
    @Test
    void getUsersListTest() {
        ResponseDataModel response =
        given(requestSpec)
                .when()
                .get("/users?page=2")
                .then()
                .spec(response200)
                .extract().as(ResponseDataModel.class);
        assertThat(response.getData().get(0).getLastName().equals("Lawson"));
        assertThat(response.getData().get(1).getLastName().equals("Ferguson"));
        assertThat(response.getData().get(2).getLastName().equals("Funke"));


    }


    @Test
    void getSingleUserNotFoundTest() {
        given(requestSpec)
                .log().uri()
                .when()
                .get("/users/23")
                .then()
                .spec(response404);


    }
}