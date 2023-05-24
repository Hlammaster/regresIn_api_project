package tests;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.is;
import static specs.Specs.*;

public class ResourceTests {
    @Test
    void getUsersListTest() {
        given(requestSpec)
                .when()
                .get("/users?page=2")
                .then()
                .spec(response200)
                .body(matchesJsonSchemaInClasspath("schemes/status-scheme-responce.json"));


    }

    @Test
    void getSingleUserTest() {
        given(requestSpec)
                .when()
                .get("/users/2")
                .then()
                .spec(response200)
                .body(matchesJsonSchemaInClasspath("schemes/status-singleuser-response.json"));


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