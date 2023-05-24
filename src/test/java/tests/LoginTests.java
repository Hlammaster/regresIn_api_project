package tests;

import io.restassured.http.ContentType;
import models.UserModel;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;
import static org.hamcrest.Matchers.is;
import static specs.Specs.requestSpec;

public class LoginTests {
    @Test
    void successfulLogin(){
        UserModel userModel = new UserModel();
        userModel.setEmail("eve.holt@reqres.in");
        userModel.setPassword("cityslicka");
        given(requestSpec)
                .body(userModel)
                .when()
                .post("/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("token", is("QpwL5tke4Pnpja7X4"));
    }
    @Test
    void unsuccessfulLogin(){

        UserModel userModel = new UserModel();
        userModel.setEmail("peter@klaven");
        given(requestSpec)
                .body(userModel)
                .when()
                .post("/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error", is("Missing password"));
    }
}

