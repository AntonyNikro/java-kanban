import java.util.ArrayList;

public class Epic extends Task {
    private ArrayList<Integer> subTaskIdArray;

    public Epic(int id, String name, String description, Status status) {
        super(id, name, description, status);
        subTaskIdArray = new ArrayList<>();
    }

    public ArrayList<Integer> getSubTaskIdArray() {
        return subTaskIdArray;
    }

    public void setSubTaskIdArray(ArrayList<Integer> subTaskIdArray) {
        this.subTaskIdArray = subTaskIdArray;
    }

    public  void addSubTaskId (int subTaskId) {
        this.subTaskIdArray.add(subTaskId);
    }

    @Override
    public String toString() {
        return "Epic{" +
                "subTaskIdArray=" + subTaskIdArray +
                '}';
    }
}
