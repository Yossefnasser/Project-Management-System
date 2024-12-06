package models;

import javafx.concurrent.Task;
import pl2.example.demo.User;

import javax.swing.*;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

public class Employee extends User {
    private LocalTime enterTime ;
    private LocalTime exitTime ;
    private List<String> penalties ;
    private List<String> assignedTasks;
    public Employee(String name, int age, String Email, int id, int phoneNumber) {
        super(name, age, Email, id, phoneNumber);
    }
    public float  calculateWorkingHours(){
        float workingHours = enterTime.getHour() - exitTime.getHour();
        System.out.println(workingHours);
        return workingHours;
    }
    public void requestVacation(){

    }
    public List<String> viewPenalties(){
        return penalties;
    }
    public void checkTaskCompletion(Task task) {}

    public List<String> viewAssignedTasks(){
        return assignedTasks;
    }

    public LocalTime getEnterTime() {
        return enterTime;
    }

    public LocalTime getExitTime() {
        return exitTime;
    }

    public List<String> getPenalties() {
        return penalties;
    }

    public List<String> getAssignedTasks() {
        return assignedTasks;
    }

    public void setEnterTime(LocalTime enterTime) {
        this.enterTime = enterTime;
    }

    public void setExitTime(LocalTime exitTime) {
        this.exitTime = exitTime;
    }
}
