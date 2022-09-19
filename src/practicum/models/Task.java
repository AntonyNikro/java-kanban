package practicum.models;

import practicum.manager.save_file.TaskType;

public class Task {
    private int id;
    private String name;
    private String description;

    private TaskType taskType;

    private Status status;

    public Task(int id, String name, String description, TaskType taskType, Status status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.taskType = taskType;
        this.status = status;
    }

    public Task(String name, String description, TaskType taskType, Status status) {
        this.name = name;
        this.description = description;
        this.taskType = taskType;
        this.status = status;
    }

    public Task(String name, String description, TaskType taskType) {
        this.name = name;
        this.description = description;
        this.taskType = taskType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "practicum.models.Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }
}