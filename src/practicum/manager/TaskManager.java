package practicum.manager;

import practicum.models.Epic;
import practicum.models.SubTask;
import practicum.models.Task;

import java.util.List;

public interface TaskManager {
    List<Task> getTasks();

    List<Task> getSubTasks();

    List<Task> getEpic();

    Task getTaskId(int id);

    SubTask getSubTaskId(int id);

    Epic getEpicId(int id);

    void deleteTasks();

    void deleteSubTasks();

    void deleteEpics();

    List<SubTask> epicSubTaskIds(int id);

   int addTask(Task task);

    int addSubTask(SubTask subTask);

    int addEpic(Epic epic);

    void updateTask(Task task);

    void updateSubTask(SubTask subTask);

    void updateEpic(Epic epic);

    void updateEpicStatus(Epic epic);

    void removeTaskId(int id);

    void removeSubTaskId(int id);

    void removeEpicId(int id);

    List<Task> getHistory();

    HistoryManager getHistoryManager();

}
