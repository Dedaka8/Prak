import client.OrderClient;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.notNullValue;

public class GetOrdersTest {

    private OrderClient orderClient;
    @Before
    public void SetUp() {
        orderClient = new OrderClient();

    }

    @Test
    @DisplayName("Получение всех заказов")
    public void getOrdersTest() {

        ValidatableResponse response = orderClient.getOrder();
        response.assertThat()
                .statusCode(SC_OK)
                .body("orders.id", everyItem(notNullValue()));
    }
}