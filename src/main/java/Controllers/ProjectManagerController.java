package Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import models.ProjectManager;

public class ProjectManagerController {

    @FXML
    private TextArea projectCompletionTextArea;

    @FXML
    private TextField projectNameField, projectPercentageField, employeeNameField, reportDetailsField;

    @FXML
    private Label updateStatusLabel, reportStatusLabel;

    // Create an instance of ProjectManager
    private ProjectManager projectManager = new ProjectManager("John Doe", "35", "PM123");

    // Handle "View Projects" button click
    @FXML
    private void onViewProjects() {
        String projectStatus = String.valueOf(projectManager.viewProjectCompletion());
        projectCompletionTextArea.setText(projectStatus);
    }

    // Handle "Update Project" button click
    @FXML
    private void onUpdateProject() {
        String projectName = projectNameField.getText().trim();
        String percentageText = projectPercentageField.getText().trim();

        if (projectName.isEmpty() || percentageText.isEmpty()) {
            updateStatusLabel.setText("Please fill in all fields!");
            updateStatusLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        try {
            float percentage = Float.parseFloat(percentageText);
            if (percentage < 0 || percentage > 100) {
                updateStatusLabel.setText("Percentage must be between 0 and 100.");
                updateStatusLabel.setStyle("-fx-text-fill: red;");
                return;
            }

            String updateMessage = projectManager.updateProjectCompletion(projectName, percentage);
            updateStatusLabel.setText(updateMessage);
            updateStatusLabel.setStyle("-fx-text-fill: green;");
        } catch (NumberFormatException e) {
            updateStatusLabel.setText("Invalid percentage. Enter a number.");
            updateStatusLabel.setStyle("-fx-text-fill: red;");
        }
    }

    // Handle "Report Employee" button click
    @FXML
    private void onReportEmployee() {
        String employeeName = employeeNameField.getText().trim();
        String reportDetails = reportDetailsField.getText().trim();

        if (employeeName.isEmpty() || reportDetails.isEmpty()) {
            reportStatusLabel.setText("Please fill in all fields!");
            reportStatusLabel.setStyle("-fx-text-fill: red;");
            return;
        }
    }
}