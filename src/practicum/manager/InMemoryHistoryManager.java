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
            /*if (head == null) {
                Node<T> currentNode = new Node<>(t, null, tail);
                head = currentNode;
                tail = new Node<>(null, currentNode, null);
                return;
            }
            Node<T> currentNode = tail;
            currentNode.value = t;
            tail = new Node<>(null, currentNode, null);
            currentNode.prev.next = currentNode;
            currentNode.next = tail;*/

            Node node = new Node<>(t, tail, null); //не совсем понял подход, но реализовал как показывал в вебинаре Андрей Смалий (буду благодарен за пояснения))
            if (head == null) {
                head = node;
            } else {
                tail.next = node;
            }
            tail = node;
        }

        public List<T> getTasks() {
            List<T> tasks = new ArrayList<>();
            Node<T> current = head;

            while (current != null) {
                tasks.add(current.getValue());
                current = current.next;
            }
            return tasks;
        }

        public void removeNode(Node<T> node) {
            if (node.equals(head)) {
                head = node.next;
                if (node.next != null) {
                    node.next.prev = null;
                } else {
                    tail = null;
                }
            } else {
                node.prev.next = node.next;
                if (node.next != null) {
                    node.next.prev = node.prev;

                }
            }
        }
    }

    @Override
    public void addHistory(Task task) {
        remove(task);
        historyList.linkLast(task);
        history.put(task.getId(), historyList.tail.prev);
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
