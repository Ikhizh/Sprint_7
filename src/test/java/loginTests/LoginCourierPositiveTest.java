package loginTests;

import courier.Courier;
import courier.CourierCreator;
import courier.CourierCreds;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import testData.Constans;

import static org.hamcrest.Matchers.*;

public class LoginCourierPositiveTest {
    private Courier courier;
    private CourierCreator courierCreator;
    private CourierCreds courierCreds;

    @Before
    public void setUp() {
        courier = new Courier(Constans.LOGIN, Constans.PASSWORD, Constans.FIRSTNAME);
        courierCreator = new CourierCreator();
        courierCreds = new CourierCreds(Constans.LOGIN, Constans.PASSWORD);
        courierCreator
                .create(courier)
                .statusCode(201)
                .and()
                .assertThat().body("ok", is(true));
    }

    @After
    public void tearDown() {
        courierCreator.delete(courierCreds);
    }

    @Test
    public void loginWithValidData() {
        courierCreator
                .login(courierCreds)
                .statusCode(200)
                .and()
                .assertThat().body("$", hasKey("id")).body("id", is(notNullValue()));
    }
}
