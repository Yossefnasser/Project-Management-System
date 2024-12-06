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
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import models.Employee;
import pl2.example.demo.User;

import java.io.IOException;

public class LoginController {

    @FXML
    private Button clearButton;

    @FXML
    private Text errorMessage;

    @FXML
    private Button loginButton;

    @FXML
    private Label newlabel;

    @FXML
    private PasswordField passwordfield;

    @FXML
    private TextField usernamefield;


    @FXML
    void loginHandler(MouseEvent event) {

        String username = usernamefield.getText();
        String password = passwordfield.getText();

        System.out.println("Username: " + username);
        System.out.println("Password: " + password);
        if (username.isEmpty() || password.isEmpty()) {
            errorMessage.setText("Username or password is empty");
        }
        else if (!User.login(username , password)) {
            errorMessage.setText("Username or password is incorrect");
        }
        else {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/EmployeePage.fxml")); // Update the path to your new FXML file
                Parent root = loader.load();

                // Get the current stage
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                // Set the new scene
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public void clearHandler(MouseEvent event){
        errorMessage.setText("");
        passwordfield.setText("");
        usernamefield.setText("");
    }
}
