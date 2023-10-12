import java.util.EmptyStackException;

public class q1<T> {
    private Node<T> top;

    public q1() {
        top = null;
    }

    public void push(T item) {
        Node<T> newNode = new Node<>(item);
        newNode.next = top;
        top = newNode;
    }

    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        T poppedItem = top.data;
        top = top.next;
        return poppedItem;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public int size() {
        int count = 0;
        Node<T> current = top;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    public static void main(String[] args) {
        q1<Integer> stack = new q1<>();

        System.out.println("Enter Stack Size: 3");
        System.out.println("Enter Stack Values");
        stack.push(1);
        stack.push(2);
        stack.push(3);

        System.out.println("Perform Stack Operations");
        System.out.println("pop");
        System.out.println("Popped: " + stack.pop());
        System.out.println("pop");
        System.out.println("Popped: " + stack.pop());
        System.out.println("size");
        System.out.println("Stack size: " + stack.size());
        System.out.println("isEmpty");
        System.out.println("Stack is " + (stack.isEmpty() ? "empty." : "not empty."));
        System.out.println("Program Exited!");
    }

    private static class Node<T> {
        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }
}
