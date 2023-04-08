package deleteCourierTests;

import courier.Courier;
import courier.CourierCreator;
import courier.CourierCreds;
import org.junit.Before;
import org.junit.Test;
import testData.Constans;
import static org.hamcrest.Matchers.is;

public class DeleteCourierTest {
    private Courier courier;
    private CourierCreator courierCreator;
    private CourierCreds courierCreds;

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

    @Test
    public void deleteCourier() {
        courierCreator
                .delete(courierCreds)
                .assertThat().body("ok", is(true));
        }
}

