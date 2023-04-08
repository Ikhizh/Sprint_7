package deleteCourierTests;

import courier.Courier;
import courier.CourierCreator;
import courier.CourierCreds;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import testData.Constans;

import static org.hamcrest.Matchers.is;

public class DeleteCourierNegativeTest { private Courier courier;
    private CourierCreator courierCreator;
    private CourierCreds courierCreds;
    private CourierCreds courierWrongID;

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
    public void deleteCourierWithWrongID(){
        courierCreator
                .delete("000000")
                .statusCode(404)
                .and()
                .assertThat().body("message", is("Курьера с таким id нет."));
    }
}
