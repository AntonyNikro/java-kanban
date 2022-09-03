package practicum.models;

import java.util.ArrayList;
import java.util.List;

public class Epic extends Task {
    private List<Integer> subTaskIdList;

    public Epic(int id, String name, String description, Status aNew) {
        super(id, name, description, Status.NEW);
        subTaskIdList = new ArrayList<>();
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
