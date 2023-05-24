package tests;

import io.restassured.http.ContentType;
import models.UserModel;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;
import static org.hamcrest.Matchers.is;
import static specs.Specs.*;

public class UserTests {
    @Test
    void createUserTest() {
        UserModel userModel = new UserModel();
        userModel.setName("morpheus");
        userModel.setJob("leader");
        given(requestSpec)
                .body(userModel)
                .when()
                .post("/users")
                .then()
                .spec(response201)
                .body("name", is("morpheus"))
                .body("job", is("leader"));


    }
    @Test
    void updateUserAccountTest() {
        UserModel userModel = new UserModel();
        userModel.setName("morpheus");
        userModel.setJob("zion resident");
        given(requestSpec)
                .body(userModel)
                .when()
                .patch("/users/2")
                .then()
                .spec(response200)
                .body("name", is("morpheus"))
                .body("job", is("zion resident"));


    }
    @Test
    void deleteUserTest() {

        given(requestSpec)
                .when()
                .delete("/users/2")
                .then()
                .spec(response204));



    }
}
