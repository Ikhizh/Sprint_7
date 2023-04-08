package corrierCreationTests;

import courier.Courier;
import courier.CourierCreator;
import courier.CourierCreds;
import org.junit.Before;
import org.junit.Test;
import testData.Constans;
import static org.hamcrest.Matchers.is;

public class CourierCreationNegativeTest {
    private Courier courier;
    private CourierCreator courierCreator;
    private CourierCreds courierCreds;
    @Before
    public void setUp() {
        courierCreator = new CourierCreator();
        courierCreds = new CourierCreds(Constans.LOGIN, Constans.PASSWORD);
    }

    @Test
    public void createNewCourierWithoutLogin() {
        courier = new Courier(null, Constans.PASSWORD, Constans.FIRSTNAME);

        courierCreator
                .create(courier)
                .statusCode(400)
                .and()
                .assertThat().body("message", is("Недостаточно данных для создания учетной записи"));
    }

    @Test
    public void createNewCourierWithoutPassword() {
        courier = new Courier(Constans.LOGIN, null, Constans.FIRSTNAME);
        courierCreator
                .create(courier)
                .statusCode(400)
                .and()
                .assertThat().body("message", is("Недостаточно данных для создания учетной записи"));
    }
}
