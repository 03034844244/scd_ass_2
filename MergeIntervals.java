import java.util.List;

public class MergeIntervals {
    public static void mergeIntervals(List<int[]> intervals) {
        if (intervals.isEmpty()) {
            return;
        }

        int n = intervals.size();
        int writeIndex = 0;

        for (int i = 1; i < n; i++) {
            int[] current = intervals.get(writeIndex);
            int[] next = intervals.get(i);

            if (current[1] >= next[0]) {
                // Merge overlapping intervals by updating the end time of the current interval.
                current[1] = Math.max(current[1], next[1]);
            } else {
                // Non-overlapping interval found, update writeIndex and interval.
                writeIndex++;
                intervals.set(writeIndex, next);
            }
        }

        // Remove any extra intervals beyond the merged ones.
        intervals.subList(writeIndex + 1, n).clear();
    }

    public static void main(String[] args) {
        List<int[]> intervals = List.of(new int[]{1, 3}, new int[]{2, 6}, new int[]{8, 10}, new int[]{15, 18});

        mergeIntervals(intervals);

        for (int[] interval : intervals) {
            System.out.println("[" + interval[0] + ", " + interval[1] + "]");
        }
    }
}
