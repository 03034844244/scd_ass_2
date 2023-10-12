import java.util.LinkedHashSet;
import java.util.Set;

public class WordCheck {
    private Set<Character> repeatedChars;
    private Set<Character> nonRepeatedChars;

    public WordCheck() {
        repeatedChars = new LinkedHashSet<>();
        nonRepeatedChars = new LinkedHashSet<>();
    }

    public void add(char ch) {
        if (repeatedChars.contains(ch)) {
            nonRepeatedChars.remove(ch);
        } else {
            if (nonRepeatedChars.contains(ch)) {
                nonRepeatedChars.remove(ch);
                repeatedChars.add(ch);
            } else {
                nonRepeatedChars.add(ch);
            }
        }
    }

    public char getFirstNonRepeatingChar() {
        for (char ch : nonRepeatedChars) {
            return ch;
        }
        return '-';
    }

    public static void main(String[] args) {
        WordCheck stream = new WordCheck();
        stream.add('a');
        stream.add('b');
        stream.add('a');
        stream.add('c');
        System.out.println("First non-repeating character is: " + stream.getFirstNonRepeatingChar());
    }
}