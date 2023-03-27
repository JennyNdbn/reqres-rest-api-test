package guru.qa.tests;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class TestBaseWebinar {
    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = "https://events.webinar.ru";
        RestAssured.basePath = "/api";

    }
}
