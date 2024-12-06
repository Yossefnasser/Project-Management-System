package pl2.example.demo;

import java.util.HashMap;
import java.util.Map;

public abstract class User {
    private String name;
    private int age;
    private String Email;
    private int id;
    private int phoneNumber;
    private String Username;
    private String password;

    private final Map<String, String> userRoles = new HashMap<>();
    private static final Map<String, String> userPasswords = new HashMap<>();
    public User(String name, int age, String Email, int id, int phoneNumber) {
        this.name = name;
        this.age = age;
        this.Email = Email;
        this.id = id;
        this.phoneNumber = phoneNumber;
    }
    public static boolean login(String username, String password) {

        userPasswords.put("employee1", "password123");
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
    public int getId() {
        return id;
    }
    public int getPhoneNumber() {
        return phoneNumber;
    }
    public Map<String, String> getUserRoles() {
        return userRoles;
    }
    public Map<String, String> getUserPasswords() {
        return userPasswords;
    }
}
