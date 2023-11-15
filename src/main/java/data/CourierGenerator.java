package data;

import io.qameta.allure.Step;
import org.apache.commons.lang3.RandomStringUtils;

public class CourierGenerator {
    @Step("Создание случайного курьера")
    public static CourierData getRandomCourier(){
        String login = RandomStringUtils.randomAlphabetic(10);
        String password = RandomStringUtils.randomAlphabetic(10);
        String name = RandomStringUtils.randomAlphabetic(10);

        return new CourierData(login,password,name);
    }

    @Step("Создание случайного курьера без пароля")
    public static CourierData getRandomCourierWithoutLogin(){
        String password = RandomStringUtils.randomAlphabetic(10);
        String name = RandomStringUtils.randomAlphabetic(10);

        return new CourierData(password,name,2);
    }
    @Step("Создание случайного курьера без логина")
    public static CourierData getRandomCourierWithoutPassword(){
        String login = RandomStringUtils.randomAlphabetic(10);
        String name = RandomStringUtils.randomAlphabetic(10);

        return new CourierData(login,name,1);
    }
    @Step("Создание случайного курьера без имени")
    public static CourierData getRandomCourierWithoutName(){
        String login = RandomStringUtils.randomAlphabetic(10);
        String password = RandomStringUtils.randomAlphabetic(10);

        return new CourierData(login,password,3);
    }


}