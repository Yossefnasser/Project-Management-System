package models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.HashMap;
import java.util.Map;

public class ProjectManager extends User {
    private Map<String, Float> projectCompletion; // Project Name -> Completion %
    private ObservableList<Tasks> tasks; // List of all tasks
    private ObservableList<Employee> team; // Employees managed by the PM

    // Constructor
    public ProjectManager(String name, String id, String password) {
        super(name, id, password, "PM");
        this.projectCompletion = new HashMap<>();
        this.tasks = FXCollections.observableArrayList();
        this.team = FXCollections.observableArrayList();
    }

    // Assign Task to Employee
    public void assignTask(Employee employee, Tasks task) {
        employee.addTask(task); // Add task to the employee
        tasks.add(task); // Add task to the PM's list
    }

    // View Completed Tasks
    public ObservableList<Tasks> viewCompletedTasks() {
        ObservableList<Tasks> completedTasks = FXCollections.observableArrayList();
        for (Tasks task : tasks) {
            if (task.isCompleted()) {
                completedTasks.add(task);
            }
        }
        return completedTasks;
    }

    // Manage Employee Vacation (Accept/Reject)
    public boolean manageEmployeeVacation(Employee employee, boolean accept) {
        // Use this return value to update the GUI
        return accept;
    }

    // Add Penalty to Employee
    public void addPenalty(Employee employee, String reason) {
        employee.addPenalty(reason); // Update the employee's penalty list
    }

    // View Project Completion
    public ObservableList<String> viewProjectCompletion() {
        ObservableList<String> projectStatus = FXCollections.observableArrayList();
        for (Map.Entry<String, Float> entry : projectCompletion.entrySet()) {
            projectStatus.add(entry.getKey() + ": " + entry.getValue() + "%");
        }
        return projectStatus;
    }

    // Update Project Completion
    public String updateProjectCompletion(String projectName, float percentage) {
        projectCompletion.put(projectName, percentage);
        return projectName;
    }

    // Manage Team: Add Employee
    public void addEmployee(Employee employee) {
        team.add(employee); // Add employee to the team list
    }

    // Manage Team: Remove Employee
    public void removeEmployee(Employee employee) {
        team.remove(employee); // Remove employee from the team list
    }

    // Override to add custom PM data for file storage
    @Override
    protected void addCustomData(StringBuilder data) {
        data.append("projectCompletion=").append(projectCompletion.toString()).append(";");
    }

    @Override
    public void performRoleSpecificTask() {
        // No specific console output; GUI-based
    }

}
