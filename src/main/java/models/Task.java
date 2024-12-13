package models;

public class Task {
    private int taskId;
    private String taskName;
    private String taskDescription;
    private String taskStatus;
    private String taskDeadline;
    public Task(int Taskid, String taskName, String taskDescription, String taskStatus, String taskDeadline) {
        this.taskId = Taskid;
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.taskStatus = taskStatus;
        this.taskDeadline = taskDeadline;
    }
    public int getTaskId() {
        return taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getTaskStatus() {
        return taskStatus;
    }
    public String getTaskDeadline() {
        return taskDeadline;
    }
    public String getTaskDescription() {
        return taskDescription;
    }

    public boolean isCompleted() {
        return taskStatus.equals("completed");
    }
}
