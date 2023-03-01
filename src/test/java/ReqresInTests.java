import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ReqresInTests {

    private final String BASE_URL = "https://reqres.in/api";

    @Test
    @DisplayName("Check response status of existing list of users")
    @Tag("reqres_in")
    void checkListUsersStatus() {
        given()
                .log().uri()
                .when()
                .get(BASE_URL + "/users?page=2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200);
    }

    @Test
    @DisplayName("Verify name and email of single user")
    @Tag("reqres_in")
    void checkSingleUser() {
        given()
                .log().uri()
                .when()
                .get(BASE_URL + "/users/2")
                .then()
                .log().status()
                .log().body()
                .body("data.id", equalTo(2),
                        "data.email", equalTo("janet.weaver@reqres.in"),
                        "data.first_name", equalTo("Janet"),
                        "data.last_name", equalTo("Weaver")
                );
    }

    @Test
    @DisplayName("Check response status of non existing resource")
    @Tag("reqres_in")
    void checkResourceNotFoundStatus() {
        given()
                .log().uri()
                .when()
                .get(BASE_URL + "/unknown/23")
                .then()
                .log().status()
                .log().body()
                .statusCode(404);
    }

    @Test
    @DisplayName("Verify name, job and date of user after update")
    @Tag("reqres_in")
    public void updateUser() {
        String updateData = "{\"name\": \"morpheus\", \"job\": \"zion resident\"}";
        String dateTimeNow = DateTimeFormatter.ofPattern("yyyy-MM-dd")
                .format(LocalDateTime.now());

        given()
                .log().uri()
                .contentType(ContentType.JSON)
                .body(updateData)
                .when()
                .put(BASE_URL + "/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("name", is("morpheus"),
                        "job", is("zion resident"),
                        "updatedAt", containsString(dateTimeNow));
    }

    @Test
    @DisplayName("Verify token after user registration")
    @Tag("reqres_in")
    void checkSuccessfulRegisterTest() {
        String testData = "{\"email\":\"eve.holt@reqres.in\", \"password\":\"pistol\"}";
        String token = "QpwL5tke4Pnpja7X4";

        given()
                .log().uri()
                .contentType(ContentType.JSON)
                .body(testData)
                .when()
                .post(BASE_URL + "/register")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("token", is(token));
    }
}
