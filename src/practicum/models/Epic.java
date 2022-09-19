package practicum.models;

import practicum.manager.save_file.TaskType;

import java.util.ArrayList;
import java.util.List;

public class Epic extends Task {
    private List<Integer> subTaskIdList;

    public Epic(int id, String name, String description, TaskType taskType) {
        super(id, name, description, taskType, null);
        subTaskIdList = new ArrayList<>();
    }

    public Epic(String name, String description, TaskType taskType) {
        super(name, description, taskType);
    }



    public List<Integer> getSubTaskIdList() {
        return subTaskIdList;
    }

    public void setSubTaskIdList(List<Integer> subTaskIdList) {
        this.subTaskIdList = subTaskIdList;
    }

    public  void addSubTaskId (int subTaskId) {
        this.subTaskIdList.add(subTaskId);
    }

    @Override
    public String toString() {
        return "practicum.models.Epic{" +
                "subTaskIdArray=" + subTaskIdList +
                '}';
    }
}
