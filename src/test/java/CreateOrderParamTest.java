
import client.OrderClient;
import data.OrderData;
import data.OrderGenerator;
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

    private final String[] color;

    public CreateOrderParamTest( String[] color) {

        this.color = color;
    }

    @Before
    public void SetUp() {
        orderClient = new OrderClient();

    }

    @Parameterized.Parameters()
    public static Object[][] getData() {
        return new Object[][]{
                { new String[] {"BLACK","GREY"}},
                { new String[] {"GREY"}},
                { new String[] {"BLACK"}},
                { null }
        };
    }

    @Test
    @DisplayName("Создание заказа")
    public void orderCanBeCreatedTest() {

        order = OrderGenerator.getRandomOrderWithColor(color);
        ValidatableResponse response = orderClient.createOrder(order);
        response.assertThat()
                .statusCode(SC_CREATED)
                .body("track", notNullValue());

    }
}
