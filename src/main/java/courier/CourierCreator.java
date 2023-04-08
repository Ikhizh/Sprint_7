package courier;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class CourierCreator {
    public static final String BASE_URI = "https://qa-scooter.praktikum-services.ru/";
    private static final String PATH = "api/v1/courier";
    private static final String LOGIN_PATH = "api/v1/courier/login";

    public CourierCreator() {
        RestAssured.baseURI = BASE_URI;
    }

    public ValidatableResponse create(Courier courier) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(courier)
                .when()
                .post(PATH)
                .then();
    }

    public ValidatableResponse login(CourierCreds creds) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(creds)
                .when()
                .post(LOGIN_PATH)
                .then();
    }

    public ValidatableResponse delete(CourierCreds creds) {
        String courierId = this.login(creds).statusCode(200).extract().jsonPath().getString("id");
        return RestAssured
                .given()
                .when()
                .header("Delete_Courier", "Bearer" + courierId)
                .delete(PATH + "/" + courierId)
                .then()
                .statusCode(200);
    }

    public ValidatableResponse delete(String  courierId) {
        return RestAssured
                .given()
                .when()
                .header("Delete_Courier", "Bearer" + courierId)
                .delete(PATH + "/" + courierId)
                .then();
    }
}
