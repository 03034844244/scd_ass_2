import java.util.List;

public class q3 {
    public static void q3(List<int[]> intervals) {
        if (intervals.isEmpty()) {
            return;
        }

        int n = intervals.size();
        int index = 0;

        for (int i = 1; i < n; i++) {
            int[] current = intervals.get(index);
            int[] next = intervals.get(i);

            if (current[1] >= next[0]) {
                
                current[1] = Math.max(current[1], next[1]);
            } else {
                
                index++;
                intervals.set(index, next);
            }
        }

       
        intervals.subList(index + 1, n).clear();
    }

    public static void main(String[] args) {
        List<int[]> intervals = List.of(new int[]{1, 3}, new int[]{2, 6}, new int[]{8, 10}, new int[]{15, 18});

        q3(intervals);

        for (int[] interval : intervals) {
            System.out.println("[" + interval[0] + ", " + interval[1] + "]");
        }
    }
}
