package hello.jdbc.domain;

import lombok.Data;

@Data
public class User {
    private String id;
    private String name;
    private String password;

    public User() {

    }

    public User(String id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }
}
