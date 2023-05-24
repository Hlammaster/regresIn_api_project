import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class UserTests {
    @Test
    void createUserTest() {
        String body = "{ \"name\": \"morpheus\", \"job\": \"leader\" }";

        given()
                .log().uri()
                .body(body)
                .contentType(ContentType.JSON)
                .when()
                .post("https://reqres.in/api/users")
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

        given()
                .log().uri()
                .log().body()
                .body(body)
                .contentType(ContentType.JSON)
                .when()
                .patch("https://reqres.in/api/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("name", is("morpheus"))
                .body("job", is("zion resident"));


    }
    @Test
    void deleteUserTest() {

        given()
                .log().uri()
                .when()
                .delete("https://reqres.in/api/users/2")
                .then()
                .log().all()
                .statusCode(204);



    }
}
