package data;

public class CourierData {
    private String login;
    private String password;
    private String firstName;

    public CourierData() {
    }

    public CourierData(String login, String password, String firstName) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
    }

    public CourierData(String password, String firstName) {

        this.password = password;
        this.firstName = firstName;
    }
    public CourierData(String firstParameter, String secondParameter, int type) {

        if(type == 1) {
            this.login = firstParameter;
            this.firstName = secondParameter;
        } else if (type == 2) {
            this.password = firstParameter;
            this.firstName = secondParameter;
        } else {
            this.login = firstParameter;
            this.password = secondParameter;
        }
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}