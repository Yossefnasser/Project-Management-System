package models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Tasks {
    private final StringProperty name;
    private final StringProperty status;
    private final StringProperty deadline;

    public Tasks(String name, String status, String deadline) {
        this.name = new SimpleStringProperty(name);
        this.status = new SimpleStringProperty(status);
        this.deadline = new SimpleStringProperty(deadline);
    }

    public StringProperty nameProperty() {
        return name;
    }

    public StringProperty statusProperty() {
        return status;
    }

    public StringProperty deadlineProperty() {
        return deadline;
    }
    @Override
    public String toString() {
        return name + " (" + status + ") due on " + deadline;
    }
}
