package client;
import data.OrderData;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class OrderClient extends RestClient{
    public static final String ORDER_PATH = "/api/v1/orders";


    @Step("Отправка POST запроса на " + ORDER_PATH)
    public ValidatableResponse createOrder(OrderData order){
        return given()
                .spec(requestSpecification())
                .and()
                .body(order)
                .when()
                .post(ORDER_PATH)
                .then();

    }
    @Step("Отправка GET запроса на " + ORDER_PATH)
    public ValidatableResponse getOrder(){
        return given()
                .spec(requestSpecification())
                .and()
                .when()
                .get(ORDER_PATH)
                .then();

    }
}