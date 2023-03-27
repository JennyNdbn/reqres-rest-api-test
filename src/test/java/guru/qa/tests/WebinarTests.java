package guru.qa.tests;

import guru.qa.models.*;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static guru.qa.specs.TestSpecs.testRequestSpec;
import static guru.qa.specs.TestSpecs.testResponseSpec;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class WebinarTests extends TestBaseWebinar {

    @Test
    @Owner("Evgeniia Nadobnaia")
    @DisplayName("Check response status of existing list of users")
    @Tag("webinar")
    void checkListUsersStatus() {
        //String testData = "{\"email\"=\"NadobnayaEA@arurza.ru\", \"password\"=\"zimmer483\", \"rememberMe\"=\"true\"}";
        step("Check that response status is 200", () -> {
            given(testRequestSpec)
                    .contentType("application/x-www-form-urlencoded; charset=utf-8")
                    //.cookie("__ddg1_=jS8Md9UgEE9wCx1LSXmf; UserFirstVisitDate=7%20%D1%84%D0%B5%D0%B2%D1%80%D0%B0%D0%BB%D1%8F%202023%20%D0%B3.; popmechanic_sbjs_migrations=popmechanic_1418474375998%3D1%7C%7C%7C1471519752600%3D1%7C%7C%7C1471519752605%3D1; intercom-device-id-eb4dv2rj=08fc59fc-5aa4-4bf2-8510-64b85b6d9e77; carrotquest_device_guid=d8dd2af0-b241-4bbc-91cb-e45d2bc6b652; carrotquest_uid=1373042746037635463; carrotquest_auth_token=user.1373042746037635463.43181-ca84f6447f3691c171e5f6cade.9899d87b445956237df552f93c2ee6b06f353a1d12f7367f; carrotquest_realtime_services_transport=wss; atatus-aid=id|65d406e057184ac7b4425bb0d3f424d7&timestamp|2023-02-07T16:32:13.857Z; Wref=https://webinar.ru/clients/; tmr_lvid=ee1e376e15e2c3148d29333fa3f20d64; tmr_lvidTS=1679019842468; _ym_uid=1679019843848898129; _ym_d=1679019843; _ga_FLHF3D07LG=GS1.1.1679752313.1.1.1679752370.3.0.0; _ga=GA1.2.689323895.1679019842; mp_0ff52acccc4445cd1afa75416a39d7de_mixpanel=%7B%22distinct_id%22%3A%2060250687%2C%22%24device_id%22%3A%20%22186ed619c5f531-0673e05fc10689-26031851-1fa400-186ed619c60c40%22%2C%22%24initial_referrer%22%3A%20%22https%3A%2F%2Fwebinar.ru%2F%22%2C%22%24initial_referring_domain%22%3A%20%22webinar.ru%22%2C%22%24user_id%22%3A%2060250687%7D; webrtcTestResult_v2=ok; intercom-session-eb4dv2rj=UjNsT2UyQzllbHpBaGZIYjNaMmxmc2R5Z3F1M0UzN2k4M0JmdmFxM211T1oxbCtKZC9MQ0FmQXEvWlNNQUNrVy0tZFE3V0ppL0VRVGcrMDBQT0EwZ1FSUT09--4dcbd9881e641debb0cc3f41833e28813248dfaa; RUM_EPISODES=s=1679928946831&r=https%3A//events.webinar.ru/; atatus-sid=id|110e507c240246f1b141f45589041db7&timestamp|2023-03-27T14:55:48.022Z; sessionId=2292f96955374a0d0c9aad47da93170c")
                    .cookie("d65194b8645819406e7219d7801287b7")
                    .body("email=NadobnayaEA%40arurza.ru&password=zimmer483&rememberMe=true")
                    //.body(testData)
                    .when()
                    //.queryParam("email", "NadobnayaEA@arurza.ru")
                    //.queryParam("password", "zimmer483")
                    .post("/login")
                    .then()
                    .spec(testResponseSpec)
                    .statusCode(200);
        });
    }

    @Test
    @Owner("Evgeniia Nadobnaia")
    @DisplayName("Check response status of existing list of users")
    @Tag("webinar")
    void checkListUsersStatus1() {
        SingleUserResponseModel response =
        step("Check that response status is 200", () ->
            given(testRequestSpec)
                    .contentType("application/x-www-form-urlencoded; charset=utf-8")
                    .cookie("sessionId=c9d614b5b694a9ef9f7cb6dd95d249e4; Path=/; Secure; HttpOnly; Expires=Mon, 24 Apr 2023 16:07:48 GMT;")
                    .when()
                    //.queryParam("email", "NadobnayaEA@arurza.ru")
                    //.queryParam("password", "zimmer483")
                    .get("/login")
                    .then()
                    .spec(testResponseSpec)
                    .statusCode(200)
                    .extract().as(SingleUserResponseModel.class));
        step("Verify single user data", () -> {
            assertThat(response.getId()).isEqualTo(60250687);
            assertThat(response.getEmail()).isEqualTo("nadobnayaea@arurza.ru");
            assertThat(response.getNickname()).isEqualTo("Евгения Надобная ПВК \"АРУ РЗА\"");
            assertThat(response.getRealNickname()).isEqualTo("Евгения Надобная ПВК \"АРУ РЗА\"");
        });
    }
    @Test
    @Owner("Evgeniia Nadobnaia")
    @DisplayName("Check response status of existing list of users")
    @Tag("webinar")
    void checkListUsersStatus2() {
        StatisticsResponseModel response =
        step("Check that response status is 200", () ->
            given(testRequestSpec)
                    .contentType("application/x-www-form-urlencoded; charset=utf-8")
                    .cookie("sessionId=c9d614b5b694a9ef9f7cb6dd95d249e4; Path=/; Secure; HttpOnly; Expires=Mon, 24 Apr 2023 16:07:48 GMT;")
                    .when()
                    //.queryParam("email", "NadobnayaEA@arurza.ru")
                    //.queryParam("password", "zimmer483")
                    .get("/user/statistics")
                    .then()
                    .spec(testResponseSpec)
                    .statusCode(200)
                    .extract().as(StatisticsResponseModel.class));
        step("Verify single user data", () -> {
            assertThat(response.getContacts().getTotal()).isEqualTo(1);
        });

    }
    @Test
    @Owner("Evgeniia Nadobnaia")
    @DisplayName("Check response status of existing list of users")
    @Tag("webinar")
    void checkListUsersStatusNeg() {
        //String testData = "{\"email\"=\"NadobnayaEA@arurza.ru\", \"password\"=\"zimmer483\", \"rememberMe\"=\"true\"}";
        step("Check that response status is 418", () -> {
            given(testRequestSpec)
                    .contentType("application/x-www-form-urlencoded; charset=utf-8")
                    //.cookie("d65194b8645819406e7219d7801287b7")
                    .body("")
                    .when()
                    .post("/login")
                    .then()
                    .spec(testResponseSpec)
                    .statusCode(418)
                    .body("error.message", is("ERROR_MOBILE_USER"));;
        });
    }
    @Test
    @Owner("Evgeniia Nadobnaia")
    @DisplayName("Check response status of existing list of users")
    @Tag("webinar")
    void checkListUsersStatusNeg2() {
        step("Check that response status is 404", () -> {
            given(testRequestSpec)
                    .contentType("application/x-www-form-urlencoded; charset=utf-8")
                    .cookie("")
                    .body("")
                    .when()
                    .get("/login")
                    .then()
                    .spec(testResponseSpec)
                    .statusCode(404)
                    .body("error.message", is("User is not found"))
            ;
        });
    }
/*
    @Test
    @Owner("Evgeniia Nadobnaia")
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
    @Owner("Evgeniia Nadobnaia")
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
    @Owner("Evgeniia Nadobnaia")
    @DisplayName("Verify name, job and date of user after update")
    @Tag("reqres_in")
    public void updateUser() {
        UserUpdBodyModel user = new UserUpdBodyModel();
        user.setName("morpheus");
        user.setJob("zion resident");
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
    @Owner("Evgeniia Nadobnaia")
    @DisplayName("Verify token after user registration")
    @Tag("reqres_in")
    void checkSuccessfulRegisterTest() {
        LoginBodyModel loginData = new LoginBodyModel();
        loginData.setEmail("eve.holt@reqres.in");
        loginData.setPassword("pistol");

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

    @Test
    @Owner("Evgeniia Nadobnaia")
    @DisplayName("Verify user email by id from list of users")
    @Tags({@Tag("reqres_in"),@Tag("groovy")})
    void checkEmailByIdUsingGroovy() {
        step("Verify user email using groovy", () -> {
            given(testRequestSpec)
                    .when()
                    .get("/users?page=2")
                    .then()
                    .spec(testResponseSpec)
                    .body("data.find{it.id == 9}.email", is("tobias.funke@reqres.in"));
        });
    }*/
}
