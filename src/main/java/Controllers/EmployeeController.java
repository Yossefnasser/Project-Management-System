package Controllers;

public class EmployeeController {

    @FXML
    private TextArea RequestText;

    @FXML
    private TableView<?> assignedTasks;

    @FXML
    private Button calculateButton;

    @FXML
    private Text calculatedTime;

    @FXML
    private TableColumn<?, ?> deadlineField;

    @FXML
    private TextField entryTimeField;

    @FXML
    private TextField exitTimeField;

    @FXML
    private Button markComplete;

    @FXML
    private TableColumn<?, ?> nameField;

    @FXML
    private TableView<?> penalties;

    @FXML
    private TableColumn<?, ?> penaltyDateColumn;

    @FXML
    private TableColumn<?, ?> penaltyReasonColumn;

    @FXML
    private TableColumn<?, ?> statusField;

    @FXML
    private Button submitButton;

    @FXML
    private ComboBox<?> tasksBox;

    @FXML
    private TextField startDateField , endDateField ;

    @FXML
    private Button startDateButton;

    @FXML
    private TableView<Map<String, String>> employeeRequestsTable;

    private ObservableList<Map<String, String>> requests = FXCollections.observableArrayList();


    private Employee employee;
    public void initialize() {
        employee = new Employee("name" , )
        employeeRequestsTable.setItems(requests);
        List<Employee> employees = Employee.loadEmployeeData();

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

            employee.setHoursWorked(hours);
            calculatedTime.setText("Total Working Hours: " + hours + " hours and " + minutes + " minutes");

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
        request.put("employeeName", "John Doe"); // Replace with actual employee name if needed
        request.put("startDate", startDate);
        request.put("endDate", endDate);
        request.put("status", "Pending");

        // Add the map to the ObservableList
        requests.add(request);

        // Clear the input fields
        startDateField.clear();
        endDateField.clear();
    }

    @FXML
    void submitHandler(ActionEvent event) {

    }

}
