package models;
import java.util.ArrayList;
import java.util.List;

public class TeamLeader extends User{
    private String teamId;


    public TeamLeader(String name, String id, String password , String teamId) {
        super(name, id, password,"TL");
        this.teamId = teamId;
    }
    //put the   task  to employee
    public void assignTask(Employee employee , Tasks task) {

    }
    //viewCompletedTasks()
    public List<Tasks> viewCompletedTasks() {
        List<Tasks> completedTasks = new ArrayList<>();

        return completedTasks;
    }

    // manageEmployees() 1-accept or reject vacation/2-add penalties
    public String manageEmployees() {
        // Simply return the team list
        return this.teamId;
    }

    @Override
    protected void addCustomData(StringBuilder data) {
        data.append("teamId=").append(teamId).append(";");
    }

    @Override
    public void performRoleSpecificTask() {

    }
}