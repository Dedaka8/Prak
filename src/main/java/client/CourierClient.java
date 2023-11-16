package client;

import data.CourierCredentials;
import data.CourierData;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class CourierClient  extends RestClient{

    public static final String COURIER_PATH = "/api/v1/courier";
    public static final String LOGIN_PATH = "/api/v1/courier/login";
    public static final String DELETE_PATH = "/api/v1/courier/";


    @Step("Отправка POST запроса на " + COURIER_PATH)
    public ValidatableResponse createCourier(CourierData courier){
        return given()
                .spec(requestSpecification())
                .and()
                .body(courier)
                .when()
                .post(COURIER_PATH)
                .then();

    }


    @Step("Отправка POST запроса на " + LOGIN_PATH)
    public ValidatableResponse loginCourier(CourierCredentials courier){
        return given()
                .spec(requestSpecification())
                .and()
                .body(courier)
                .when()
                .post(LOGIN_PATH)
                .then();

    }

    @Step("Отправка DELETE запроса на " + DELETE_PATH)
    public ValidatableResponse deleteCourier(String id){
        return given()
                .spec(requestSpecification())
                .and()
                .when()
                .delete(DELETE_PATH + id)
                .then();

    }
}