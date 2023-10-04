package models;

import lombok.Data;

@Data
public class Credentials {
    private String login;
    private String password;

    public Credentials() {
    }

    public Credentials(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public static Credentials from(Courier courier) {
        return new Credentials(courier.getLogin(), courier.getPassword());
    }
}
