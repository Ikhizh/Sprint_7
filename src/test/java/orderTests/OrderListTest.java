package orderTests;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class OrderListTest {
    private final String[] ordersKeys = {"id", "courierId", "firstName", "lastName", "address", "metroStation", "phone",
            "rentTime", "deliveryDate", "track", "color", "comment", "createdAt", "updatedAt", "status"};
    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru/";
    }

    @Test
    public void checkAllOrders() {
        ArrayList<?> response = given()
                .header("Content-type", "application/json")
                .when()
                .get("api/v1/orders")
                .then()
                .statusCode(200)
                .extract()
                .path("orders");
        assertThat(response, hasSize(greaterThan(0)));
        for (Object o : response) {
            Map<?, ?> order = (LinkedHashMap<?, ?>) o;
            for (String key : ordersKeys) {
                assertThat(order, hasKey(key));
            }
        }
    }
}
