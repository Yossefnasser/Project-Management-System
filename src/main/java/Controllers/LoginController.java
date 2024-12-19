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
import models.TeamLeader;
import models.User;

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

    private void processLogin() {
        String username = usernamefield.getText();
        String password = passwordfield.getText();

        // Check if fields are empty
        if (username.isEmpty() || password.isEmpty()) {
            errorMessage.setText("Username or password is empty");
            return;
        }

        // Search for the employee by ID (username) and password
        List<User> users = User.readAllUsers();
        User matchedUser = null;
        for (User user : users) {
            if (user.getId().equals(username) && user.getPassword().equals(password)) {
                matchedUser = user;
                break;
            }
        }

        if (matchedUser != null) {
            // Successful login
            try {
                String fxmlFile = "";

                if (matchedUser.getRole().equals("Employee")) {
                    fxmlFile = "/views/EmployeePage.fxml";
                } else if (matchedUser.getRole().equals("TL")) {
                    fxmlFile = "/views/TLPage.fxml";
                } else {
                    errorMessage.setText("Invalid role: " + matchedUser.getRole());
                    return;
                }
                FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
                Parent root = loader.load();

                if (matchedUser instanceof Employee) {
                    EmployeeController controller = loader.getController();
                    controller.setEmployee((Employee) matchedUser);
                } else if (matchedUser instanceof TeamLeader) {
                    TLController controller = loader.getController();
                    controller.setTL((TeamLeader) matchedUser);
                }

                Stage stage = (Stage) usernamefield.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
                errorMessage.setText("Failed to load EmployeePage.");
            }
        } else {
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
