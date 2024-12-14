package models;

import javafx.concurrent.Task;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class Employee extends User {
    private double hoursWorked;
    private double monthlyHoursWorked ;
    private List<String> penalties ;
    private List<Task> assignedTasks;
    private static final String EMPLOYEE_FILE = "employees.txt";

    public Employee(String name, int age, String id, String phoneNumber , double hoursWorked) {
        super(name, age, id, phoneNumber);
        this.hoursWorked = hoursWorked ;
    }
    public void saveEmployeeData() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(EMPLOYEE_FILE, true))) {
            writer.write(getId() + "," + getName() + "," + getPhoneNumber() + "," + getAge() + "," + this.hoursWorked);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error saving employee data: " + e.getMessage());
        }
    }

    // Load Data from File
    public static List<Employee> loadEmployeeData() {
        List<Employee> employees = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(EMPLOYEE_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    Employee employee = new Employee(
                            parts[1],
                            Integer.parseInt(parts[0]),
                            parts[2],
                            parts[3],
                            Double.parseDouble(parts[4])
                    );
                    employees.add(employee);
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading employee data: " + e.getMessage());
        }
        return employees;
    }
    public void monthlyWorked(long duration) {

        monthlyHoursWorked += duration;
        System.out.println(monthlyHoursWorked);

    }
    public List<String> viewPenalties(){
        return penalties;
    }
    public void checkTaskCompletion(Task task) {}

    public List<Task> viewAssignedTasks(){
        return assignedTasks;
    }

    public void setHoursWorked(long hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    public List<String> getPenalties() {
        return penalties;
    }

    public List<Task> getAssignedTasks() {
        return assignedTasks;
    }

    public long getHoursWorked() {
        return (long) hoursWorked;
    }
}

