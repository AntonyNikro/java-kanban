package practicum.manager;

import practicum.models.Task;

import java.util.*;

public class InMemoryHistoryManager implements HistoryManager {

    private final Map<Integer, Node<Task>> history;
    private final CustomLinkedList<Task> historyList;

    public InMemoryHistoryManager() {
        this.history = new HashMap<>();
        this.historyList = new CustomLinkedList<>();
    }

    class CustomLinkedList<T> {
        private Node<T> head;
        private Node<T> tail;

        public void linkLast(T t) {

            Node node = new Node<>(t, tail, null);
            if (head == null) {
                head = node;
            } else {
                tail.setNext(node);
            }
            tail = node;
        }

        public List<T> getTasks() {
            List<T> tasks = new ArrayList<>();
            Node<T> current = head;

            while (current != null) {
                if (current.getValue() != null) {
                    tasks.add(current.getValue());
                }
                current = current.getNext();
            }
            return tasks;
        }

        public void removeNode(Node<T> node) {
            if (node.equals(head)) {
                head = node.getNext();
                if (node.getNext() != null) {
                    node.getNext().setPrev(null);
                } else {
                    tail = null;
                }
            } else {
                node.getPrev().setNext(node.getNext());
                if (node.getNext() != null) {
                    node.getNext().setPrev(node.getPrev());

                }
            }
        }
    }

    @Override
    public void addHistory(Task task) {
        remove(task);
        historyList.linkLast(task);
        history.put(task.getId(), historyList.tail.getPrev());
    }

    @Override
    public void remove(Task task) {
        if (history.containsKey(task.getId())) {
            historyList.removeNode(history.get(task.getId()));
            history.remove(task.getId());
        }
    }

    @Override
    public List<Task> getHistory() {
        return historyList.getTasks();
    }
}
