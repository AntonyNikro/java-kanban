package practicum.manager;

import practicum.models.Task;

import java.util.List;

public interface HistoryManager {

    List<Task> getHistory();

    void addHistory(Task task);

    void remove(Task task);
}
