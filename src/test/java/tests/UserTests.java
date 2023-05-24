package tests;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;
import static org.hamcrest.Matchers.is;
import static specs.Specs.requestSpec;

public class UserTests {
    @Test
    void createUserTest() {
        String body = "{ \"name\": \"morpheus\", \"job\": \"leader\" }";

        given(requestSpec)
                .body(body)
                .when()
                .post("/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .body("name", is("morpheus"))
                .body("job", is("leader"));


    }
    @Test
    void updateUserAccountTest() {
        String body = "{ \"name\": \"morpheus\", \"job\": \"zion resident\" }";

        given(requestSpec)
                .body(body)
                .when()
                .patch("/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("name", is("morpheus"))
                .body("job", is("zion resident"));


    }
    @Test
    void deleteUserTest() {

        given(requestSpec)
                .when()
                .delete("/users/2")
                .then()
                .log().all()
                .statusCode(204);



    }
}
