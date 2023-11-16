import client.CourierClient;
import data.CourierCredentials;
import data.CourierData;
import data.CourierGenerator;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;

import static org.apache.http.HttpStatus.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LoginCourierTest {

    public static final String NOT_FULL_DATA = "Недостаточно данных для входа";
    public static final String LOGIN_NOT_FOUND = "Учетная запись не найдена";
    private CourierClient courierClient;
    private CourierData courier;

    @Before
    public void setUp() {
        courierClient = new CourierClient();
        courier = CourierGenerator.getRandomCourier();
        courierClient.createCourier(courier);

    }
    @After
    public void cleanUp(){
        ValidatableResponse responseLogin = courierClient.loginCourier(CourierCredentials.from(courier));
        String courierId = responseLogin.extract().path("id").toString();
        if(courierId != null) {
            courierClient.deleteCourier(courierId);

        }
    }

    @Test
    @DisplayName("Вход курьера в систему")
    public void courierCanLoggingTest(){
        ValidatableResponse responseLogin = courierClient.loginCourier(CourierCredentials.from(courier));
        responseLogin.assertThat()
                .statusCode(SC_OK)
                .body("id", notNullValue());

    }

    @Test //этот тест падает по 504, через постман аналогично, когда не передается пароль.
    @DisplayName("Попытка входа курьера без пароля")
    public void courierWithoutPasswordLoggingTest(){
        ValidatableResponse responseLogin = courierClient.loginCourier(CourierCredentials.withoutPassword(courier));
        responseLogin.assertThat()
                .statusCode(SC_BAD_REQUEST)
                .body("message", equalTo(NOT_FULL_DATA));
    }
    @Test
    @DisplayName("Попытка входа курьера без логина")
    public void courierWithoutLoginLoggingTest(){
        ValidatableResponse responseLogin = courierClient.loginCourier(CourierCredentials.withoutLogin(courier));
        responseLogin.assertThat()
                .statusCode(SC_BAD_REQUEST)
                .body("message", equalTo(NOT_FULL_DATA));
    }

    @Test
    @DisplayName("Попытка входа курьера с неверным паролем")
    public void courierWithErrorPasswordLoggingTest() {
        ValidatableResponse responseLogin = courierClient.loginCourier(CourierCredentials.errorPassword(courier));
        responseLogin.assertThat()
                .statusCode(SC_NOT_FOUND)
                .body("message", equalTo(LOGIN_NOT_FOUND));
    }



}