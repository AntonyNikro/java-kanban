package practicum.models;

import practicum.manager.save_file.TaskType;

public class SubTask extends Task {
    private int epicId;

    public SubTask(int id, String name, String description, TaskType taskType, Status status, int epicId) {
        super(id, name, description, taskType, status);
        this.epicId = epicId;
    }

    public SubTask(String name, String description, int epicId, TaskType taskType) {
        super(name, description, taskType);
        this.epicId = epicId;
    }

    public int getEpicId() {
        return epicId;
    }

    public void setEpicId(int epicId) {
        this.epicId = epicId;
    }

    @Override
    public String toString() {
        return "practicum.models.SubTask{" +
                "epicId=" + epicId +
                '}';
    }
}
