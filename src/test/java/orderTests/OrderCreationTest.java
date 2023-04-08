package orderTests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import testData.OrderData;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.is;

@RunWith(Parameterized.class)
public class OrderCreationTest {
    private final String[] color;

    public OrderCreationTest(String[] color) {
        this.color = color;
    }
    @Parameterized.Parameters // добавили аннотацию
    public static Object[][] getSumData() {
        return new Object[][]{
                {new String[] {"BLACK"}},
                {new String[] {"GREY"}},
                {new String[] {""}},
                {new String[] {"BLACK", "GREY"}}
        };
    }

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru/";
    }
    @Test
    public void createOrder(){
        OrderData order = new OrderData("Naruto", "Uchiha", "Konoha, 142 apt.", "4", "+7 800 355 35 35", 5, "2020-06-06", "Saske, come back to Konoha", color);
        Response response =
                given()
                .header("Content-type", "application/json") // заполни header
                .body(order) // заполни body
                .when()
                .post("/api/v1/orders");
        response.then().statusCode(201)
                .and()
                .assertThat().body("track", is(notNullValue()));
    }
}

