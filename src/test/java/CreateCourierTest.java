import client.CourierClient;
import data.CourierCredentials;
import data.CourierData;
import data.CourierGenerator;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.After;
import static org.apache.http.HttpStatus.*;
import static org.hamcrest.CoreMatchers.*;


public class CreateCourierTest {
    public static final String DUPLICATION_ERROR = "Этот логин уже используется. Попробуйте другой.";
    public static final String NOT_FULL_DATA_ERROR = "Недостаточно данных для создания учетной записи";

    private CourierClient courierClient;
    private CourierData courier;

    private CourierData courierWithoutLogin;
    private CourierData courierWithoutPassword;

    private String courierId;

    @Before
    public void SetUp() {
        courierClient = new CourierClient();
        courier = CourierGenerator.getRandomCourier();
        courierWithoutLogin = CourierGenerator.getRandomCourierWithoutLogin();
        courierWithoutPassword = CourierGenerator.getRandomCourierWithoutPassword();

    }

    @After
    public void cleanUp(){
        if(courierId != null) {
            courierClient.deleteCourier(courierId);
        }
    }

    @Test
    @DisplayName("Создание курьера")
    public void courierCanBeCreatedTest(){

        ValidatableResponse response = courierClient.createCourier(courier);
        response.assertThat()
                .statusCode(SC_CREATED)
                .body("ok", is(true));


        ValidatableResponse responseLogin = courierClient.loginCourier(CourierCredentials.from(courier));
        responseLogin.assertThat()
                .statusCode(SC_OK);
        courierId = responseLogin.extract().path("id").toString();

    }



    @Test
    @DisplayName("Попытка создания дубля курьера")
    public void courierDuplicationCreatedTest(){


        courierClient.createCourier(courier);
        ValidatableResponse responseLogin = courierClient.loginCourier(CourierCredentials.from(courier));
        courierId = responseLogin.extract().path("id").toString();

        ValidatableResponse response = courierClient.createCourier(courier);
        response.assertThat()
                .statusCode(SC_CONFLICT)
                .body("message", equalTo(DUPLICATION_ERROR));

    }
    @Test
    @DisplayName("Попытка создания курьера без логина")
    public void courierCreatedWithoutLogin(){
        ValidatableResponse response = courierClient.createCourier(courierWithoutLogin);
        response.assertThat()
                .statusCode(SC_BAD_REQUEST)
                .body("message", equalTo(NOT_FULL_DATA_ERROR));
    }

    @Test
    @DisplayName("Попытка создания  без пароля")
    public void courierCreatedWithoutPassword(){
        ValidatableResponse response = courierClient.createCourier(courierWithoutPassword);
        response.assertThat()
                .statusCode(SC_BAD_REQUEST)
                .body("message", equalTo(NOT_FULL_DATA_ERROR));
    }

}