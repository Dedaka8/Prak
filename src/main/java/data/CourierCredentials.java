package data;

import io.qameta.allure.Step;

public class CourierCredentials {
    private String login;
    private String password;

    public CourierCredentials() {
    }

    public CourierCredentials(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public CourierCredentials(String parameter, int type) {
        if(type == 1) {
            this.login = parameter;
        } else {
            this.password = parameter;
        }
    }

    @Step("Получение данных о курьере")
    public static CourierCredentials from(CourierData courier){

        return new CourierCredentials(courier.getLogin(),courier.getPassword());
    }
    @Step("Получение данных о курьере с ошибочным паролем")
    public static CourierCredentials errorPassword(CourierData courier){

        return new CourierCredentials(courier.getLogin(),courier.getPassword()+"1");
    }

    @Step("Получение данных о курьере без пароля")
    public static CourierCredentials withoutPassword(CourierData courier){

        return new CourierCredentials(courier.getLogin(),1);
    }
    @Step("Получение данных о курьере без логина")
    public static CourierCredentials withoutLogin(CourierData courier){

        return new CourierCredentials(courier.getPassword(),2);
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}