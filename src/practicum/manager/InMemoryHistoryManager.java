package practicum.manager;

import practicum.models.Task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {

    private static int SIZE = 10;

    private final List<Task> history = new ArrayList<>();

    @Override
    public void addHistory(Task task) {
        history.add(task);
        if(history.size() > SIZE) {
            history.remove(0);
        }
    }
    @Override
    public List<Task> getHistory() {

        return history; // не совсем понятно что требуется, такие методы не проходили. Прошу пояснить)
    }
}
