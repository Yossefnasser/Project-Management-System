package pl2.example.demo;

public abstract class User {
    private String name;
    private int age;
    private String Email;
    private int id;
    private int phoneNumber;
    public User(String name, int age, String Email, int id, int phoneNumber) {
        this.name = name;
        this.age = age;
        this.Email = Email;
        this.id = id;
        this.phoneNumber = phoneNumber;
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
}
