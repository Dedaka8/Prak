package data;

import io.qameta.allure.Step;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Random;

public class OrderGenerator {

    @Step("Создание случайного заказа с заданным цветом самоката")
    public static OrderData getRandomOrderWithColor(String[] color){

       Random random = new Random();
       String firstName = RandomStringUtils.randomAlphabetic(10);
       String lastName = RandomStringUtils.randomAlphabetic(10);
       String address = RandomStringUtils.randomAlphabetic(30);
       String metroStation = RandomStringUtils.randomAlphabetic(3);
       String phone = "7925999999";
       int rentTime = random.nextInt(8) + 1;
       String deliveryDate = "2020-06-06";
       String comment = RandomStringUtils.randomAlphabetic(50);


        return new OrderData(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, color);
    }
}
