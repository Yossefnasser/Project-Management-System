package pl2.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

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
        User user = new Employee("John Doe", 30, "john.doe@example.com", 1, 123456789);

        String username = usernamefield.getText();
        String password = passwordfield.getText();

        System.out.println("Username: " + username);
        System.out.println("Password: " + password);
        if (username.isEmpty() || password.isEmpty()) {
            errorMessage.setText("Username or password is empty");
        }
        else if (!user.login(username , password)) {
            errorMessage.setText("Username or password is incorrect");
        }
        else {

        }

    }

    public void clearHandler(MouseEvent event){
        errorMessage.setText("");
        passwordfield.setText("");
        usernamefield.setText("");
    }
}
