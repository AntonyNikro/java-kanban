package practicum.manager;

import practicum.models.Epic;
import practicum.models.Status;
import practicum.models.SubTask;
import practicum.models.Task;

import java.util.*;

public class InMemoryTaskManager implements TaskManager {
    private int addId;
    private final Map<Integer, Task> taskHash = new HashMap<>();
    private final Map<Integer, SubTask> subTaskHash = new HashMap<>();
    private final Map<Integer, Epic> epicHash = new HashMap<>();

    private HistoryManager historyManager = Managers.getDefaultHistory();

    @Override
    public List<Task> getTasks() {
      return new ArrayList<>(taskHash.values());
    }

    @Override
    public List<Task> getSubTasks() {

        return new ArrayList<>(subTaskHash.values());
    }

    @Override
    public List<Task> getEpic() {

        return new ArrayList<>(epicHash.values());
    }


    @Override
    public Task getTaskId(int id) {
        historyManager.addHistory(taskHash.get(addId));
        return taskHash.get(id);
    }

    @Override
    public SubTask getSubTaskId(int id) {
        historyManager.addHistory(subTaskHash.get(addId));
        return subTaskHash.get(id);
    }

    @Override
    public Epic getEpicId(int id) {
        historyManager.addHistory(epicHash.get(addId));
        return epicHash.get(id);
    }

    @Override
    public void deleteTasks() {
        taskHash.clear();
    }

    @Override
    public void deleteSubTasks() {
        for (var id : subTaskHash.keySet()) { //честно говоря не осознал необходимость применения действий указанных
            this.removeSubTaskId(id); // в замечании. Есть же реализация через removeSubTaskId. Либо тогда не очень понятно как это делать,
        } // прошу пояснить)


    }

    @Override
    public void deleteEpics() {
        subTaskHash.clear();
        epicHash.clear();
    }

    @Override
    public List<SubTask> epicSubTaskIds(int id) {
        List<Integer> getEpicSubTasks = epicHash.get(id).getSubTaskIdList();
        List<SubTask> subTaskArray = new ArrayList<>();
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
        for (int id : epic.getSubTaskIdList()) {
            SubTask subTask = subTaskHash.get(id);
            if (subTask.getStatus().equals(Status.NEW)) {
                epic.setStatus(Status.NEW);
            }
            if (subTask.getStatus().equals(Status.DONE)) {
                epic.setStatus(Status.DONE);
            } else {
                epic.setStatus(Status.IN_PROGRESS);
            }

        }

    }

    @Override
    public void removeTaskId(int id) {
            taskHash.remove(id);

    }

    @Override
    public void removeSubTaskId(int id) {
        int epicId = subTaskHash.get(id).getEpicId();
        SubTask subTask = subTaskHash.get(id);
        Epic epic = getEpicId(subTask.getEpicId());
        if (subTaskHash.containsKey(id)) {
            if (epicHash.containsKey(epic.getId())) {
                epicHash.get(subTask).getSubTaskIdList().remove(id); //сделал так, если правильно понял...
                updateEpicStatus(epic);
            }
        }
        subTaskHash.remove(id);
    }

    @Override
    public void removeEpicId(int id) {
        if (epicHash.containsKey(id)) {
            Epic epic = epicHash.get(id);
            for (int subTaskId : epic.getSubTaskIdList()) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InMemoryTaskManager that = (InMemoryTaskManager) o;
        return addId == that.addId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(addId);
    }
}
