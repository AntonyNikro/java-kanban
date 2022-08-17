package practicum.manager;

import practicum.models.Epic;
import practicum.models.Status;
import practicum.models.SubTask;
import practicum.models.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryTaskManager implements TaskManager {
    private int addId;
    private HashMap<Integer, Task> taskHash = new HashMap<>();
    private HashMap<Integer, SubTask> subTaskHash = new HashMap<>();
    private HashMap<Integer, Epic> epicHash = new HashMap<>();

    HistoryManager historyManager = Managers.getDefaultHistory();

    public ArrayList<Task> getTasks() {
      return new ArrayList<>(taskHash.values());
    }

    public ArrayList<Task> getSubTasks() {

        return new ArrayList<>(subTaskHash.values());
    }

    public ArrayList<Task> getEpic() {

        return new ArrayList<>(epicHash.values());
    }


    public Task getTaskId(int id) {

        return taskHash.get(id);
    }

    public SubTask getSubTaskId(int id) {

        return subTaskHash.get(id);
    }

    public Epic getEpicId(int id) {

        return epicHash.get(id);
    }

    @Override
    public void deleteTasks() {

        taskHash.clear();
    }

    @Override
    public void deleteSubTasks() {
        for (var id : subTaskHash.keySet()) {
            this.removeSubTaskId(id);
        }
    }

    @Override
    public void deleteEpics() {
        subTaskHash.clear();
        epicHash.clear();
    }

    @Override
    public ArrayList<SubTask> epicSubTaskIds(int id) {
        ArrayList<Integer> getEpicSubTasks = epicHash.get(id).getSubTaskIdArray();
        ArrayList<SubTask> subTaskArray = new ArrayList<>();
        for (int item : getEpicSubTasks) {
            subTaskArray.add(subTaskHash.get(item));
        }
        return subTaskArray;
    }

    @Override
    public int addTask(Task task) {
        task.setId(addId++);
        taskHash.put(task.getId(), task);
        return task.getId();
    }

    @Override
    public int addSubTask(SubTask subTask) {
        int epicId = subTask.getEpicId();
        Epic epic = epicHash.get(epicId);
        subTask.setId(addId++);
        subTaskHash.put(subTask.getId(), subTask);
        epic.addSubTaskId(subTask.getId());
        updateEpicStatus(epic);
        return subTask.getId();
    }

    @Override
    public int addEpic(Epic epic) {
        epic.setId(addId++);
        epicHash.put(epic.getId(), epic);
        return epic.getId();
    }

    @Override
    public void updateTask(Task task) {
        if (taskHash.containsKey(task.getId())) {
            taskHash.put(task.getId(), task);
        }
    }

    @Override
    public void updateSubTask(SubTask subTask) {
        if (subTaskHash.containsKey(subTask.getId())) {
            Epic epic = epicHash.get(subTask.getEpicId());
            if (epicHash.containsKey(epic.getId())) {
                subTaskHash.put(subTask.getId(), subTask);
                updateEpicStatus(epic);
            }
        }
    }

    @Override
    public void updateEpic(Epic epic) {
        if (epicHash.containsKey(epic.getId())) {
            epicHash.put(epic.getId(), epic);
        }
    }

    @Override
    public void updateEpicStatus(Epic epic) {

        if (epic.getSubTaskIdArray().isEmpty()) {
            epic.setStatus(Status.valueOf("NEW"));

        }
        for (int id : epic.getSubTaskIdArray()) {
            SubTask subTask = subTaskHash.get(id);
            if (subTaskHash.get(id).getStatus().equals(Status.valueOf("IN_PROGRESS"))) {
                epic.setStatus(Status.valueOf("IN_PROGRESS"));

            }
            if (subTaskHash.get(id).getStatus().equals(Status.valueOf("DONE"))) {
                epic.setStatus(Status.valueOf("DONE"));

            }
        }

    }

    @Override
    public void removeTaskId(int id) {
        if (taskHash.containsKey(id)) {
            taskHash.remove(id);
        }
    }

    @Override
    public void removeSubTaskId(int id) {
        int epicId = subTaskHash.get(id).getEpicId();
        SubTask subTask = subTaskHash.get(id); // оставил эту строку так как не сослаться на epic в 141 и 143 строках
        Epic epic = getEpicId(subTask.getEpicId()); // оставил эту строку так как не сослаться на epic в 141 и 143 строках
        if (subTaskHash.containsKey(id)) {
            if (epicHash.containsKey(epic.getId())) {
                epicHash.get(epicId).getSubTaskIdArray().remove(id);
                updateEpicStatus(epic);
            }
        }
        subTaskHash.remove(id);
    }

    @Override
    public void removeEpicId(int id) {
        if (epicHash.containsKey(id)) {
            Epic epic = epicHash.get(id);
            for (int subTaskId : epic.getSubTaskIdArray()) {
                subTaskHash.remove(subTaskId);
            }
            epicHash.remove(id);
        }
    }

    @Override
    public List<Task> getHistory() {
        return historyManager.getHistory();
    }

    @Override
    public HistoryManager getHistoryManager() {
        return historyManager;
    }


}
