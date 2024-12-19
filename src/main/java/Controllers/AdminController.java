package Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import models.Admin;
import models.TeamLeader;
import models.User;

import java.util.List;

public class AdminController {

    @FXML private TableView<User> userTable;
    @FXML private TableColumn<User, String> colName;
    @FXML private TableColumn<User, String> colAge;
    @FXML private TableColumn<User, String> colId;
    @FXML private TableColumn<User, String> colPassword;

    @FXML private TextField nameField;
    @FXML private TextField ageField;
    @FXML private TextField idField;
    @FXML private TextField passwordField;

    private ObservableList<User> userList;
    private Admin admin;

    @FXML
    public void initialize() {
        userList = FXCollections.observableArrayList();

        colName.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getName()));
        colAge.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(String.valueOf(data.getValue().getAge())));
        colId.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getId()));
        colPassword.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getPassword()));

        refreshTable();
    }

    @FXML
    private void addUser() {
        String name = nameField.getText();
        String ageText = ageField.getText();
        String id = idField.getText();
        String password = passwordField.getText();

        if (name.isEmpty() || ageText.isEmpty() || id.isEmpty() || password.isEmpty()) {
            showAlert("Missing Information", "All fields are required.", Alert.AlertType.ERROR);
            return;
        }

        int age = Integer.parseInt(ageText);
        User newUser = new Admin(name, id, password); // Add as Admin for simplicity
        admin.addUser(newUser);
        refreshTable();
        clearFields();
        showAlert("Success", "User added successfully.", Alert.AlertType.INFORMATION);
    }

    @FXML
    private void updateUser() {
        String id = idField.getText();
        if (id.isEmpty()) {
            showAlert("Missing ID", "Please enter the ID of the user to update.", Alert.AlertType.ERROR);
            return;
        }

        String name = nameField.getText();
        String ageText = ageField.getText();
        String password = passwordField.getText();

        boolean updated = admin.updateUser(id, name, password);
        if (updated) {
            refreshTable();
            clearFields();
            showAlert("Success", "User updated successfully.", Alert.AlertType.INFORMATION);
        } else {
            showAlert("Error", "User not found.", Alert.AlertType.ERROR);
        }
    }
    public void setAdmin(Admin admin) {
        this.admin = admin;
    }
    @FXML
    private void deleteUser() {
        String id = idField.getText();
        if (id.isEmpty()) {
            showAlert("Missing ID", "Please enter the ID of the user to delete.", Alert.AlertType.ERROR);
            return;
        }

        boolean deleted = admin.deleteUser(id);
        if (deleted) {
            refreshTable();
            clearFields();
            showAlert("Success", "User deleted successfully.", Alert.AlertType.INFORMATION);
        } else {
            showAlert("Error", "User not found.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void refreshTable() {
        userList.clear();
        List<User> users = admin.viewAllUsers();
        userList.addAll(users);
        userTable.setItems(userList);
    }

    private void clearFields() {
        nameField.clear();
        ageField.clear();
        idField.clear();
        passwordField.clear();
    }

    private void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}