package pl2.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class Controller {

    @FXML
    private Button button;

    @FXML
    private Label newlabel;

    @FXML
    void getText(MouseEvent event) {
        newlabel.setText("nigga");
    }

}
