package models;

public class Task {
    private int taskId;
    private String taskName;
    private String taskStatus;
    public Task(int TaskId, String taskName, String taskStatus) {
        this.taskId = TaskId;
        this.taskName = taskName;
        this.taskStatus = taskStatus;
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

    public boolean isCompleted() {
        return taskStatus.equals("completed");
    }
}
