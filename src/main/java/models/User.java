package models;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public abstract class User {
    private String name;
    private int age;
    private String Email;
    private String id;
    private String password;
    private String role;
    private static final String FILE_PATH = "data/employees.txt";


    private static final Map<String, String> userPasswords = new HashMap<>();

    public User(String name, String id, String password, String role) {
        this.name = name;
        this.role = role;
        this.id = id;
        this.password = password;
    }

    public void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("data/employees.txt", true))) {
            writer.write(serialize());
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error saving user to file: " + e.getMessage());
        }
    }

    public static List<User> readAllUsers() {
        List<User> users = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("data/employees.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    users.add(deserialize(line));
                } catch (Exception e) {
                    System.err.println("Error deserializing user: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading users from file: " + e.getMessage());
        }
        return users;
    }

    // Serialize user data dynamically
    protected String serialize() {
        StringBuilder data = new StringBuilder();
        data.append("role=").append(role).append(";");   // Save rule first
        data.append("id=").append(id).append(";");
        data.append("name=").append(name).append(";");
        data.append("password=").append(password).append(";");

        // Subclasses can add additional data
        addCustomData(data);
        return data.toString();
    }

    // Hook for subclasses to add extra fields
    protected void addCustomData(StringBuilder data) {}

    // Deserialize user data based on rule
    public static User deserialize(String data) {
        Map<String, String> fields = new HashMap<>();
        String[] keyValuePairs = data.split(";");
        for (String pair : keyValuePairs) {
            String[] keyValue = pair.split("=");
            if (keyValue.length == 2) {
                fields.put(keyValue[0], keyValue[1]);
            }
        }

        // Extract common fields
        String name = fields.get("name");
        String id = fields.get("id");
        String password = fields.get("password");
        String role = fields.get("role");
        String hoursWorkedStr = fields.get("hoursWorked");
        double hoursWorked = hoursWorkedStr != null ? Double.parseDouble(hoursWorkedStr) : 0.0;

        switch (role) {
            case "Admin":
                throw new UnsupportedOperationException("Admin role not implemented yet");
            case "PM":
                throw new UnsupportedOperationException("Admin role not implemented yet");
            case "TL":
                return new TeamLeader(name, id, password, fields.get("teamId"));
            case "Employee":
                return new Employee(name, id, password, hoursWorked);
            default:
                throw new IllegalArgumentException("Unknown rule: " + role);
        }
    }

    // Abstract method for subclasses to perform role-specific tasks
    public abstract void performRoleSpecificTask();

    public void updateUserInFile(User updatedUser) {
        List<User> users = readAllUsers();

        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(updatedUser.getId())) {
                users.set(i, updatedUser);
                break;
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (User user : users) {
                writer.write(user.serialize());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error updating user in file: " + e.getMessage());
        }
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

    public String getRole() {
        return role;
    }
}
