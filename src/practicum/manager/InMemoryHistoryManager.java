package practicum.manager;

import practicum.models.Task;

import java.util.ArrayList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {

    private final List<Task> history = new ArrayList<>();

    @Override
    public void addHistory(Task task) {
        history.add(task);
        if(history.size() > 10) {
            history.remove(0);
        }
    }
    @Override
    public List<Task> getHistory() {
        return history;
    }
}