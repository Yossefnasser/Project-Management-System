package models;

import java.util.HashMap;
import java.util.Map;

public abstract class User {
    private String name;
    private int age;
    private String Email;
    private String id;
    private String password;
    private String Username;

    private static final Map<String, String> userPasswords = new HashMap<>();
    public User(String name, int age, String id, String password) {
        this.name = name;
        this.age = age;
        this.id = id;
        this.password = password;
    }
    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
    public String getEmail() {
        return Email;
    }
    public String getId() {
        return id;
    }
    public String getPassword() {
        return password;
    }
}
