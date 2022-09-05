package practicum.manager;

public class Node<T> {
   private T value;
    Node<T> prev;
    Node<T> next;

    Node(T value, Node<T> prev, Node<T> next) {
        this.value = value;
        this.prev = prev;
        this.next = next;

    }

    public T getValue() {
        return value;
    }
}
