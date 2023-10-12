import java.util.LinkedHashMap;
import java.util.Map;

public class q5 {
    private Map<Character, Integer> charCounts;

    public q5() {
        charCounts = new LinkedHashMap<>();
    }

    public void add(char ch) {
        charCounts.put(ch, charCounts.getOrDefault(ch, 0) + 1);
    }

    public char getFirstNonRepeatingChar() {
        for (Map.Entry<Character, Integer> entry : charCounts.entrySet()) {
            if (entry.getValue() == 1) {
                return entry.getKey();
            }
        }
        return '-';
    }

    public static void main(String[] args) {
        q5 stream = new q5();
        stream.add('a');
        stream.add('b');
        stream.add('a');
        stream.add('c');
        System.out.println("First non-repeating character is: " + stream.getFirstNonRepeatingChar());
    }
}
