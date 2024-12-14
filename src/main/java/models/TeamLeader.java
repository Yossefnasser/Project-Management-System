package models;
import java.util.ArrayList;
import java.util.List;

public class TeamLeader extends Employee{
    private List<Employee>team;


    public TeamLeader(String name, int age, String id, String password,double hoursWorked , List<Employee> team) {
        super(name, age, id, password, hoursWorked);
        this.team = team;
    }
    //put the   task  to employee
    public void assignTask(Employee employee , Task task) {

    }
    //viewCompletedTasks()
    public List<Task> viewCompletedTasks() {
        List<Task> completedTasks = new ArrayList<>();



        return completedTasks;
    }

    // manageEmployees() 1-accept or reject vacation/2-add penalties
    public List<Employee> manageEmployees() {
        // Simply return the team list
        return this.team;
    }

}