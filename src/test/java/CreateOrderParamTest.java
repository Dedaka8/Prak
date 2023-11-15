
import client.OrderClient;
import data.OrderData;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.apache.http.HttpStatus.SC_CREATED;
import static org.hamcrest.Matchers.notNullValue;

@RunWith(Parameterized.class)
public class CreateOrderParamTest {

    private OrderClient orderClient;
    private OrderData order;


    private final String firstName;
    private final String lastName;
    private final String address;
    private final String metroStation;
    private final String phone;
    private final int rentTime;
    private final String deliveryDate;
    private final String comment;
    private final String[] color;

    public CreateOrderParamTest(String firstName, String lastName, String address, String metroStation, String phone, int rentTime, String deliveryDate, String comment, String[] color) {


        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.comment = comment;
        this.color = color;
    }

    @Before
    public void SetUp() {
        orderClient = new OrderClient();

    }

    @Parameterized.Parameters()
    public static Object[][] getData() {
        return new Object[][]{
                {"Test", "Test", "address", "metro", "+7925999999",1, "2020-06-06", "comment", new String[] {"BLACK","GREY"}},
                {"Test", "Test", "address", "metro", "+7925999999",2, "2020-06-06", "comment", new String[] {"GREY"}},
                {"Test", "Test", "address", "metro", "+7925999999",3, "2020-06-06", "comment", new String[] {"BLACK"}},
                {"Test", "Test", "address", "metro", "+7925999999",1, "2020-06-06", "comment", null}


        };
    }

    @Test
    @DisplayName("Создание заказа")
    public void orderCanBeCreatedTest() {

        order = new OrderData(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, color);
        ValidatableResponse response = orderClient.createOrder(order);
        response.assertThat()
                .statusCode(SC_CREATED)
                .body("track", notNullValue());


    }
}
