package models;

import java.util.ArrayList;
import java.util.List;

public class Admin extends User {
    private List<User> users;
    private static final String ADMIN_FILE = "admins.txt";

    public Admin(String name, String id, String password) {
        super(name, id, password, "Admin");
    }

    // Add User
    public void addUser(User user) {
        users.add(user);
        System.out.println("User added successfully.");
    }

    // Update User by ID
    public boolean updateUser(String id, String newName, String newPassword) {
        for (User user : users) {
            if (user.getId().equals(id)) {
                user.setName(newName);
                user.setPassword(newPassword);
                return true;
            }
        }
        return false;
    }

    // Delete User
    public boolean deleteUser(String id) {
        return users.removeIf(user -> user.getId().equals(id));
    }

    // View all Users
    public List<User> viewAllUsers() {
        return users;
    }

    @Override
    public void performRoleSpecificTask() {
    }
}
