package models;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Employee extends User {
    private double hoursWorked;
    private List<Map<String,String>> vacations;
    private List<Tasks> assignedTasks;

    public Employee(String name, String id, String password, double hoursWorked) {
        super(name, id, password, "Employee");
        this.hoursWorked = hoursWorked;
    }

    public double getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(double hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    public void saveVacationRequestsToFile(String request) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("data/vacation_requests.txt", true))) {
            writer.write(request);
            writer.newLine(); // Write each request in a new line
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public List<Map<String, String>> loadVacationRequestsFromFile(String employeeID) {
        List<Map<String, String>> loadedRequests = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("data/vacation_requests.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(", ");
                if (parts.length == 4 && parts[0].equals(employeeID)) {
                    Map<String, String> requestMap = new HashMap<>();
                    requestMap.put("employeeID", parts[0]);
                    requestMap.put("startDate", parts[1]);
                    requestMap.put("endDate", parts[2]);
                    requestMap.put("status", parts[3]);
                    loadedRequests.add(requestMap);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return loadedRequests;
    }


    public void setVacations(List<Map<String, String>> vacations) {
        this.vacations = vacations;
    }

    public List<Map<String,String>> getVacations() {
        return vacations;
    }

    public List<Tasks> getAssignedTasks() {
        return assignedTasks;
    }
    @Override
    protected void addCustomData(StringBuilder data) {
        data.append("hoursWorked=").append(hoursWorked).append(";");
    }

    @Override
    public void performRoleSpecificTask() {
        // Implement role-specific task for Employee
    }
}
