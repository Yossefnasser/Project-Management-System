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

    public Employee(String name, int age, String id, String password , double hoursWorked) {
        super(name, age, id, password);
        this.hoursWorked = hoursWorked ;
    }
    public void saveToFile() {
        File file = new File(EMPLOYEE_FILE);
        System.out.println("Looking for employee file at: " + file.getAbsolutePath());
        try {
            // Ensure the file exists, create it if not
            if (!file.exists()) {
                file.createNewFile(); // Create the file if it doesn't exist
                System.out.println("File created: " + file.getAbsolutePath());
            }

            // Write to the file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
                writer.write(toString());
                writer.newLine();
                System.out.println("Employee data saved successfully.");
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to save employee data.");
        }
    }


    // Search and validate login
    public static Employee login(String id, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader(EMPLOYEE_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Employee employee = parseEmployee(line);
                if (employee != null && employee.getId().equals(id) && employee.getPassword().equals(password)) {
                    return employee;
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return null;
    }
    public static List<Employee> loadEmployees() {
        List<Employee> employees = new ArrayList<>();
        File file = new File(EMPLOYEE_FILE);
        System.out.println("Looking for employee file at: " + file.getAbsolutePath());

        // Check if the file exists
        if (!file.exists()) {
            System.out.println("Employee file not found. No employees loaded.");
            return employees;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Assuming `toString` writes data as `name,age,id,phoneNumber,hoursWorked`
                String[] data = line.split(",");
                if (data.length >= 5) {
                    String name = data[0];
                    int age = Integer.parseInt(data[1]);
                    String id = data[2];
                    String password = data[3];
                    double hoursWorked = Double.parseDouble(data[4]);

                    // Create an Employee object
                    Employee employee = new Employee(name, age, id, password, hoursWorked);
                    employees.add(employee);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error reading employees file.");
        }

        return employees;
    }


    // Parse a single employee from a line
    private static Employee parseEmployee(String line) {
        try {
            String[] parts = line.split(",");
            String name = parts[0].split("=")[1];
            int age = Integer.parseInt(parts[1].split("=")[1]);
            String id = parts[2].split("=")[1];
            String password = parts[3].split("=")[1];
            double hoursWorked = Double.parseDouble(parts[4].split("=")[1]);
            return new Employee(name, age, id, password, hoursWorked);
        } catch (Exception e) {
            System.out.println("Error parsing employee: " + e.getMessage());
            return null;
        }
    }

    @Override
    public String toString() {
        return "name=" + getName() + ",age=" + getAge() + ",id=" + getId() +
                ",password=" + getPassword() + ",hoursWorked=" + hoursWorked ;
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

