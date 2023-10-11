import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;

public class q1<T> {
    private List<T> stack;
    
    public q1() {
        stack = new ArrayList<>();
    }

    public void push(T item) {
        stack.add(item);
    }

    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        int lastIndex = stack.size() - 1;
        T poppedItem = stack.remove(lastIndex);
        return poppedItem;
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public int size() {
        return stack.size();
    }

    public static void main(String[] args) {
        q1<Object> stack = new q1<>();
        
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
}
