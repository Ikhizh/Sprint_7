package loginTests;

import courier.Courier;
import courier.CourierCreator;
import courier.CourierCreds;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import testData.Constans;
import static org.hamcrest.Matchers.is;

public class LogInCourierNegativeTest {
    private Courier courier;
    private CourierCreator courierCreator;
    private CourierCreds courierCreds;
    private CourierCreds courierCredsIncorrect;

    @Before
    public void setUp() {
        courier = new Courier(Constans.LOGIN, Constans.PASSWORD, Constans.FIRSTNAME);
        courierCreds = new CourierCreds(Constans.LOGIN, Constans.PASSWORD);
        courierCreator = new CourierCreator();
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
    public void loginWithoutPassword() {
        courierCredsIncorrect = new CourierCreds(Constans.LOGIN, "");
        courierCreator
                .login(courierCredsIncorrect).statusCode(400)
                .and()
                .assertThat().body("message", is("Недостаточно данных для входа"));
    }

    @Test
    public void loginWithoutLogin() {
        courierCredsIncorrect = new CourierCreds("", Constans.PASSWORD);
        courierCreator
                .login(courierCredsIncorrect).statusCode(400)
                .and()
                .assertThat().body("message", is("Недостаточно данных для входа"));
    }

    @Test
    public void loginWithIncorrectLogin() {
        courierCredsIncorrect = new CourierCreds("dsfgnfdkjgjkdgofigjifo", Constans.PASSWORD);
        courierCreator
                .login(courierCredsIncorrect).statusCode(404)
                .and()
                .assertThat().body("message", is("Учетная запись не найдена"));
    }

    @Test
    public void loginWithIncorrectPassword() {
        courierCredsIncorrect = new CourierCreds(Constans.LOGIN, "odfjoidfjgiojrgjjbgkjgklf");
        courierCreator
                .login(courierCredsIncorrect).statusCode(404)
                .and()
                .assertThat().body("message", is("Учетная запись не найдена"));
    }

}
