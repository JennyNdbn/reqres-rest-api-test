package guru.qa.tests;

import guru.qa.models.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static guru.qa.specs.TestSpecs.testRequestSpec;
import static guru.qa.specs.TestSpecs.testResponseSpec;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class ReqresInExtendedTests extends TestBase {

    @Test
    @DisplayName("Check response status of existing list of users")
    @Tag("reqres_in")
    void checkListUsersStatus() {
        step("Check that response status is 200", () -> {
            given(testRequestSpec)
                    .when()
                    .get("/users?page=2")
                    .then()
                    .spec(testResponseSpec)
                    .statusCode(200);
        });
    }

    @Test
    @DisplayName("Verify name and email of single user")
    @Tag("reqres_in")
    void checkSingleUser() {
        SingleUserResponseModel response =
                step("Get single user data", () ->
                        given(testRequestSpec)
                                .when()
                                .get("/users/2")
                                .then()
                                .spec(testResponseSpec)
                        .extract().as(SingleUserResponseModel.class));
        step("Verify single user data", () -> {
                assertThat(response.getUser().getId()).isEqualTo(2);
                assertThat(response.getUser().getEmail()).isEqualTo("janet.weaver@reqres.in");
                assertThat(response.getUser().getFirstName()).isEqualTo("Janet");
                assertThat(response.getUser().getLastName()).isEqualTo("Weaver");
        });

    }

    @Test
    @DisplayName("Check response status of non existing resource")
    @Tag("reqres_in")
    void checkResourceNotFoundStatus() {
        step("Check that response status is 404", () -> {
        given(testRequestSpec)
                .when()
                .get("/unknown/23")
                .then()
                .spec(testResponseSpec)
                .statusCode(404);
        });
    }

    @Test
    @DisplayName("Verify name, job and date of user after update")
    @Tag("reqres_in")
    public void updateUser() {
        UserUpdBodyModel user = new UserUpdBodyModel();
        user.setName("morpheus");
        user.setJob("zion resident");
        //String updateData = "{\"name\": \"morpheus\", \"job\": \"zion resident\"}";
        String dateTimeNow = DateTimeFormatter.ofPattern("yyyy-MM-dd")
                .format(LocalDateTime.now());

        UserUpdResponseModel response =
                step("Update and get user data", () ->
                given(testRequestSpec)
                .body(user)
                .when()
                .put("/users/2")
                .then()
                .spec(testResponseSpec)
                .statusCode(200)
                .extract().as(UserUpdResponseModel.class));
        step("Verify user data after update", () -> {
            assertThat(response.getName()).isEqualTo("morpheus");
            assertThat(response.getJob()).isEqualTo("zion resident");
            assertThat(response.getUpdatedAt()).isAfterOrEqualTo(dateTimeNow);
        });
    }

    @Test
    @DisplayName("Verify token after user registration")
    @Tag("reqres_in")
    void checkSuccessfulRegisterTest() {
        LoginBodyModel loginData = new LoginBodyModel();
        loginData.setEmail("eve.holt@reqres.in");
        loginData.setPassword("pistol");
        //String testData = "{\"email\":\"eve.holt@reqres.in\", \"password\":\"pistol\"}";
        //String token = "QpwL5tke4Pnpja7X4";

        LoginResponseModel response =
                step("User registration", () ->
                given(testRequestSpec)
                .body(loginData)
                .when()
                .post("/register")
                .then()
                .spec(testResponseSpec)
                .statusCode(200)
                .extract().as(LoginResponseModel.class));
        step("Verify token after registration", () -> {
        assertThat(response.getToken()).isEqualTo("QpwL5tke4Pnpja7X4");
        });
    }
}
