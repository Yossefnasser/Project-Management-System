package Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.MapValueFactory;
import models.TeamLeader;

import java.util.HashMap;
import java.util.Map;

public class TLController {

    @FXML
    private TextField Empfeild;

    @FXML
    private TextField TaskFeild;

    @FXML
    private Button AddBtn;

    @FXML
    private Label AddMsg;

    @FXML
    private ListView<String> CmpTsk;

    @FXML
    private TableView<Map<String, String>> teamLeaderRequestsTable;

    @FXML
    private TableColumn<Map, String> Employee;

    @FXML
    private TableColumn<Map, String> start;

    @FXML
    private TableColumn<Map, String> end;

    @FXML
    private TableColumn<Map, String> status;

    @FXML
    private Button approveButton;

    @FXML
    private Button denyButton;

    @FXML
    private TextField EmpId;

    @FXML
    private TextField Description;

    @FXML
    private Button AddBtn1;

    @FXML
    private Label AddMsg1;

    private final ObservableList<String> completedTasks = FXCollections.observableArrayList();
    private final ObservableList<Map<String, String>> vacationRequests = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Initialize columns for vacation requests table
        Employee.setCellValueFactory(new MapValueFactory<>("employeeName"));
        start.setCellValueFactory(new MapValueFactory<>("startDate"));
        end.setCellValueFactory(new MapValueFactory<>("endDate"));
        status.setCellValueFactory(new MapValueFactory<>("status"));

        teamLeaderRequestsTable.setItems(vacationRequests);
        CmpTsk.setItems(completedTasks);
    }
    TeamLeader teamLeader;

    public void setTL(TeamLeader teamLeader) {
        this.teamLeader = teamLeader;
    }

    @FXML
    private void handleAddTask(ActionEvent event) {
        String employee = Empfeild.getText();
        String task = TaskFeild.getText();

        if (employee.isEmpty() || task.isEmpty()) {
            AddMsg.setText("Fields cannot be empty");
        } else {
            // Add task logic (e.g., add to database or list)
            AddMsg.setText("Task assigned to " + employee);
        }

        Empfeild.clear();
        TaskFeild.clear();
    }

    @FXML
    private void handleApprove(ActionEvent event) {
        Map<String, String> selectedRequest = teamLeaderRequestsTable.getSelectionModel().getSelectedItem();

        if (selectedRequest != null) {
            selectedRequest.put("status", "Approved");
            teamLeaderRequestsTable.refresh();
        }
    }

    @FXML
    private void handleDeny(ActionEvent event) {
        Map<String, String> selectedRequest = teamLeaderRequestsTable.getSelectionModel().getSelectedItem();

        if (selectedRequest != null) {
            selectedRequest.put("status", "Denied");
            teamLeaderRequestsTable.refresh();
        }
    }

    @FXML
    private void handleAddPenalties(ActionEvent event) {
        String employeeId = EmpId.getText();
        String description = Description.getText();

        if (employeeId.isEmpty() || description.isEmpty()) {
            AddMsg1.setText("Fields cannot be empty");
        } else {
            // Logic to add penalty (e.g., add to database or list)
            AddMsg1.setText("Penalty added for Employee ID: " + employeeId);
        }

        EmpId.clear();
        Description.clear();
    }

    public void addCompletedTask(String task) {
        completedTasks.add(task);
    }

    public void addVacationRequest(String employeeName, String startDate, String endDate) {
        Map<String, String> request = new HashMap<>();
        request.put("employeeName", employeeName);
        request.put("startDate", startDate);
        request.put("endDate", endDate);
        request.put("status", "Pending");

        vacationRequests.add(request);
    }
}