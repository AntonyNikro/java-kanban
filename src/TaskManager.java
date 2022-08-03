import java.util.ArrayList;
import java.util.HashMap;

public class TaskManager {
    private int addId;
    public HashMap<Integer, Task> taskHash = new HashMap<>();
    public HashMap<Integer, SubTask> subTaskHash = new HashMap<>();
    public HashMap<Integer, Epic> epicHash = new HashMap<>();

    public Task getTaskId(int id) {
        return taskHash.get(id);
    }

    public SubTask getSubTaskId(int id) {
        return subTaskHash.get(id);
    }

    public Epic getEpicId(int id) {
        return epicHash.get(id);
    }

    public void deleteTasks() {
        taskHash.clear();
    }

    public void deleteSubTasks() {
        subTaskHash.clear();
    }

    public void deleteEpics() {
        subTaskHash.clear();
        epicHash.clear();
    }

    public ArrayList<SubTask> getEpicSubTasks(int id) {
        ArrayList<Integer> getEpicSubTasks = epicHash.get(id).getSubTaskIdArray();
        ArrayList<SubTask> subTaskArray = new ArrayList<>();
        for (int item : getEpicSubTasks) {
            subTaskArray.add(subTaskHash.get(item));
        }
        return subTaskArray;
    }

    public int addTask(Task task) {
        task.setId(addId++);
        taskHash.put(task.getId(), task);
        return task.getId();
    }

    public int addSubTask(SubTask subTask) {
        int epicId = subTask.getEpicId();
        Epic epic = epicHash.get(epicId);
        subTask.setId(addId++);
        subTaskHash.put(subTask.getId(), subTask);
        epic.subTaskIdArray.add(subTask.getId());
        updateEpicStatus(epic);
        return subTask.getId();
    }

    public int addEpic(Epic epic) {
        epic.setId(addId++);
        epicHash.put(epic.getId(), epic);
        return epic.getId();
    }

    public void updateTask(Task task) {
        if (taskHash.containsKey(task.getId())) {
            taskHash.put(task.getId(), task);
        }
    }

    public void updateSubTask(SubTask subTask) {
        if (subTaskHash.containsKey(subTask.getId())) {
            Epic epic = epicHash.get(subTask.getEpicId());
            if (epicHash.containsKey(epic.getId())) {
                subTaskHash.put(subTask.getId(), subTask);
                updateEpicStatus(epic);
            }
        }
    }

    public void updateEpic(Epic epic) {
        if (epicHash.containsKey(epic.getId())) {
            epicHash.put(epic.getId(), epic);
        }
    }

    private void updateEpicStatus(Epic epic) {
        String status = null;
        epic.getSubTaskIdArray();
        if (epic.getSubTaskIdArray().isEmpty()) {
            epic.setStatus("NEW");
            return;
        }
        for (int id : epic.getSubTaskIdArray()) {
            SubTask subTask = subTaskHash.get(id);
            if (status == null) {
                status = subTask.getStatus();
            }
            if (status.equals(subTask.getStatus()) && !status.equals("IN_PROGRESS")) {
                epic.setStatus("IN_PROGRESS");
                return;
            }
            if (status.equals(subTask.getStatus()) && !status.equals("DONE")) {
                epic.setStatus("DONE");
                return;
            }
        }
        epic.setStatus(status);
    }

    public void removeTaskId(int id) {
        if (taskHash.containsKey(id)) {
            taskHash.remove(id);
        }
    }

    public void removeSubTaskId(int id) {
        int epicId = subTaskHash.get(id).getEpicId();
        SubTask subTask = subTaskHash.get(id);
        Epic epic = getEpicId(subTask.getEpicId());
        if (subTaskHash.containsKey(id)) {
            if (epicHash.containsKey(epic.getId())) {
                epicHash.get(epicId).getSubTaskIdArray().remove(id);
                subTaskHash.remove(id);
                updateEpicStatus(epic);
            }
        }
    }

    public void removeEpicId(int id) {
        if (epicHash.containsKey(id)) {
            Epic epic = epicHash.get(id);
            for (int subTaskId : epic.getSubTaskIdArray()) {
                subTaskHash.remove(subTaskId);
            }
            epicHash.remove(id);
        }
    }

}
