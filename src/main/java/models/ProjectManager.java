package models;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProjectManager extends Employee {
    // Key: Project Name, Value: Completion Percentage
    private final Map<String, Float> projectCompletion;

    // Constructor
    public ProjectManager(String name, int age, String id, String phoneNumber,double hoursWorked , Map<String, Float> projectCompletion ) {
        super(name, age, id, phoneNumber, hoursWorked);
        this.projectCompletion = projectCompletion;
    }

    // View Project Completion
    public String viewProjectCompletion() {
        if (projectCompletion.isEmpty()) {
            return "No projects available.";
        } else {
            return "Project Completion Status: " + projectCompletion.toString();
        }
    }

    // Update Project Completion
    public String updateProjectCompletion(String projectName, float percentage) {
        projectCompletion.put(projectName, percentage);
        if (percentage == 100) {
            return "The project '" + projectName + "' has been completed!";
        }
        return "Project '" + projectName + "' updated to " + percentage + "% completion.";
    }

    // Report Employee
    public String reportEmployee(String employeeName, String reportDetails) {
        return "Report on " + employeeName + ": " + reportDetails;
    }

    // Getter for Project Completion
    public Map<String, Float> getProjectCompletion() {
        return projectCompletion;
    }
}