package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import models.Employee;

import java.io.IOException;
import java.util.List;

public class LoginController {

    @FXML
    private Text errorMessage;

    @FXML
    private PasswordField passwordfield;

    @FXML
    private TextField usernamefield;

    @FXML
    private Button loginButton;

    /**
     * Process the login attempt.
     */
    private void processLogin() {
        String username = usernamefield.getText();
        String password = passwordfield.getText();

        // Check if fields are empty
        if (username.isEmpty() || password.isEmpty()) {
            errorMessage.setText("Username or password is empty");
            return;
        }

        // Load employees from the file
        List<Employee> employees = Employee.loadEmployees();

        // Search for the employee by ID (username) and password
        Employee matchedEmployee = null;
        for (Employee employee : employees) {
            if (employee.getId().equals(username) && employee.getPassword().equals(password)) {
                matchedEmployee = employee;
                break;
            }
        }

        if (matchedEmployee != null) {
            // Successful login
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/EmployeePage.fxml"));
                Parent root = loader.load();

                // Pass the logged-in employee to the next controller (if needed)
                // EmployeePageController controller = loader.getController();
                // controller.setEmployee(matchedEmployee);

                Stage stage = (Stage) usernamefield.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
                errorMessage.setText("Failed to load EmployeePage.");
            }
        } else {
            // Failed login
            errorMessage.setText("Username or password is incorrect");
        }
    }

    @FXML
    void loginHandler(MouseEvent event) {
        processLogin();
    }

    @FXML
    void OnKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            processLogin();
        }
    }

    @FXML
    public void clearHandler(MouseEvent event) {
        errorMessage.setText("");
        passwordfield.setText("");
        usernamefield.setText("");
    }

}
