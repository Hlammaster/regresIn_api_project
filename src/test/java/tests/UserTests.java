package tests;

import io.restassured.http.ContentType;
import models.ResponseModel;
import models.UserModel;
import models.UserResponseModel;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
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
        UserResponseModel response =
        given(requestSpec)
                .body(userModel)
                .when()
                .post("/users")
                .then()
                .spec(response201)
                .extract().as(UserResponseModel.class);
        assertThat(response.getName()).isEqualTo("morpheus");
        assertThat(response.getJob()).isEqualTo("leader");


    }
    @Test
    void updateUserAccountTest() {
        UserModel userModel = new UserModel();
        userModel.setName("morpheus");
        userModel.setJob("zion resident");
        UserResponseModel response =
        given(requestSpec)
                .body(userModel)
                .when()
                .patch("/users/2")
                .then()
                .spec(response200)
                .extract().as(UserResponseModel.class);
        assertThat(response.getName()).isEqualTo("morpheus");
        assertThat(response.getJob()).isEqualTo("zion resident");

    }
    @Test
    void deleteUserTest() {

        given(requestSpec)
                .when()
                .delete("/users/2")
                .then()
                .spec(response204);



    }
}
