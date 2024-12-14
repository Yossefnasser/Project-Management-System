package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import models.Employee;
import models.User;

import java.io.IOException;

public class LoginController {

    @FXML
    private Text errorMessage;

    @FXML
    private PasswordField passwordfield;

    @FXML
    private TextField usernamefield;


    @FXML
    private Button loginButton;

    private void processLogin() {
        String username = usernamefield.getText();
        String password = passwordfield.getText();
        if (username.equals('1')&&password.equals( '1') ) {
            Employee newEmployee = new Employee("Jane Doe", 20, "222", "04010", 0);
            newEmployee.saveEmployeeData();
        }

        System.out.println("Username: " + username);
        System.out.println("Password: " + password);

        if (username.isEmpty() || password.isEmpty()) {
            errorMessage.setText("Username or password is empty");
        } else if (!User.login(username, password)) {
            errorMessage.setText("Username or password is incorrect");
        } else {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/EmployeePage.fxml"));
                Parent root = loader.load();

                Stage stage = (Stage) usernamefield.getScene().getWindow(); // Get stage from any node in the scene
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
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

    public void clearHandler(MouseEvent event){
        errorMessage.setText("");
        passwordfield.setText("");
        usernamefield.setText("");
    }
}
