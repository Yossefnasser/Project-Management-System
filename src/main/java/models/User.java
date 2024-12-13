package models;

import java.util.HashMap;
import java.util.Map;

public abstract class User {
    private String name;
    private int age;
    private String Email;
    private String id;
    private String phoneNumber;
    private String Username;
    private String password;

    private static final Map<String, String> userPasswords = new HashMap<>();
    public User(String name, int age, String id, String phoneNumber) {
        this.name = name;
        this.age = age;
        this.id = id;
        this.phoneNumber = phoneNumber;
    }
    public static boolean login(String username, String password) {

        userPasswords.put("1", "1");
        userPasswords.put("teamleader1", "password456");
        userPasswords.put("manager1", "password789");
        userPasswords.put("admin1", "adminpass");
        if (userPasswords.containsKey(username) && userPasswords.get(username).equals(password)) {
            return true;
        }
        return false;
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
    public String getPhoneNumber() {
        return phoneNumber;
    }
}
