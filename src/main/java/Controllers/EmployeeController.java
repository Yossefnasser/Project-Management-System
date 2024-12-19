package Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import models.Employee;
import models.Tasks;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class EmployeeController {

    @FXML
    private TextArea RequestText;

    @FXML
    private TableView<Tasks> assignedTasks;
    @FXML
    private Button calculateButton;

    @FXML
    private Text calculatedTime;

    @FXML
    private TextField entryTimeField;

    @FXML
    private TextField exitTimeField;

    @FXML
    private Button markComplete;

    @FXML
    private TableColumn<Tasks, String> nameField;

    @FXML
    private TableColumn<Tasks, String> statusField;

    @FXML
    private TableColumn<Tasks, String> deadlineField;
    @FXML
    private TableView<String> penalties;

    @FXML
    private TableColumn<String, String> penaltyDateColumn;

    @FXML
    private TableColumn<String, String> penaltyReasonColumn;

    @FXML
    private Button submitButton;

    @FXML
    private ComboBox<Tasks> tasksBox;

    @FXML
    private TextField startDateField, endDateField;

    @FXML
    private Button startDateButton;

    @FXML
    private TableView<Map<String, String>> employeeRequestsTable;

    private ObservableList<Map<String, String>> requests = FXCollections.observableArrayList();


    private Employee employee;

    public void initialize() {

    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
        // Load vacation requests from the file
        String employeeID = employee.getId(); // Get the employee name from the Employee object

        // Load vacation requests that belong to this employee
        List<Map<String, String>> savedRequests = employee.loadVacationRequestsFromFile(employeeID);

        // Add loaded requests to the ObservableList
        for (Map<String, String> request : savedRequests) {
            requests.add(request);
        }

        // Optionally, set the TableView to use the ObservableList
        employeeRequestsTable.setItems(requests);
    }

    @FXML
    void MarkComplete(ActionEvent event) {

    }

    @FXML
    void calculateButtonAction(ActionEvent event) {
        String entryTimeStr = entryTimeField.getText();
        String exitTimeStr = exitTimeField.getText();

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            LocalTime entryTime = LocalTime.parse(entryTimeStr, formatter);
            LocalTime exitTime = LocalTime.parse(exitTimeStr, formatter);

            Duration duration;
            if (exitTime.isBefore(entryTime)) {
                System.out.println("nnn");
                duration = Duration.between(exitTime, entryTime);
            } else {
                duration = Duration.between(entryTime, exitTime);
            }

            long hours = duration.toHours();
            long minutes = duration.toMinutes() % 60;

            calculatedTime.setText("Total Working Hours: " + hours + " hours and " + minutes + " minutes");
            System.out.println(hours + " hours and " + minutes + " minutes");

            employee.setHoursWorked(employee.getHoursWorked() + hours);

            employee.updateUserInFile(employee);

            System.out.println(employee.getHoursWorked());

//            List<Employee> employees = loadEmployees();
//            employee.updateEmployee(employees, employee);


        } catch (DateTimeParseException ex) {
            calculatedTime.setText("Invalid time format! Please use HH:mm.");
        }
    }

    @FXML
    private void handleVacationRequest() {
        String startDate = startDateField.getText();
        String endDate = endDateField.getText();

        if (startDate.isEmpty() || endDate.isEmpty()) {
            System.out.println("Start date or end date is missing.");
            return;
        }

        // Add a new request as a map
        Map<String, String> request = new HashMap<>();
        request.put("employeeID", employee.getId()); // Replace with actual employee name if needed
        request.put("startDate", startDate);
        request.put("endDate", endDate);
        request.put("status", "Pending");

        // Add the map to the ObservableList
        requests.add(request);

        String requestString = employee.getId() + ", " + startDate + ", " + endDate + ", Pending";
        employee.saveVacationRequestsToFile(requestString);

        // Clear the input fields
        startDateField.clear();
        endDateField.clear();
    }

    @FXML
    void submitHandler(ActionEvent event) {

    }
    @FXML
    void logOut(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/LoginPage.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(new Scene(root));

    }

}
