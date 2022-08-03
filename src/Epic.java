import java.util.ArrayList;

public class Epic extends Task {
    protected ArrayList<Integer> subTaskIdArray;

    public Epic(int id, String name, String description, String status) {
        super(id, name, description, status);
        subTaskIdArray = new ArrayList<>();
    }

    public ArrayList<Integer> getSubTaskIdArray() {
        return subTaskIdArray;
    }

    public void setSubTaskIdArray(ArrayList<Integer> subTaskIdArray) {
        this.subTaskIdArray = subTaskIdArray;
    }

    @Override
    public String toString() {
        return "Epic{" +
                "subTaskIdArray=" + subTaskIdArray +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
