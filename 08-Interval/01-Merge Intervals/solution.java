/*
 * LeetCode #56: Merge Intervals
 * https://leetcode.com/problems/merge-intervals/
 */

import java.util.*;

// ========================================
// Approach 1: In-Place Sort + Null Marking
// Time: O(n log n) | Space: O(n)
// ========================================
/*
class Solution {
    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return new int[0][];
        }

        // Sort intervals by start time
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        // Merge overlapping intervals
        for (int i = 0; i < intervals.length - 1; i++) {
            if (intervals[i][1] >= intervals[i + 1][0]) {
                intervals[i + 1][0] = intervals[i][0];
                intervals[i + 1][1] = Math.max(intervals[i][1], intervals[i + 1][1]);
                intervals[i] = null; // Mark for removal
            }
        }

        // Step 3: Collect non-null intervals
        List<int[]> output = new ArrayList<>();
        for (int[] interval : intervals) {
            if (interval != null) {
                output.add(interval);
            }
        }

        // Convert list to array and return
        return output.toArray(new int[output.size()][]);
    }
}
*/

// ========================================
// Approach 2: Sort + Merge (Most Common)
// Time: O(n log n) | Space: O(n)
// ========================================
class Solution {

    public int[][] merge(int[][] intervals) {
        // Handle edge cases (empty or single interval)
        if (intervals.length <= 1) {
            return intervals;
        }

        // Sort intervals by start time
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        // Initialize result list with first interval
        List<int[]> mergedList = new ArrayList<>();
        mergedList.add(intervals[0]);

        // Process each remaining interval
        for (int i = 1; i < intervals.length; i++) {
            int[] currentInterval = intervals[i];

            // Get end time of last merged interval
            int prevEnd = mergedList.get(mergedList.size() - 1)[1];

            // Get start and end of current interval
            int currentStart = currentInterval[0];
            int currentEnd = currentInterval[1];

            if (prevEnd >= currentStart) {
                // Merge overlapping intervals
                mergedList.get(mergedList.size() - 1)[1] = Math.max(prevEnd, currentEnd);
            } else {
                // Add non-overlapping interval
                mergedList.add(currentInterval);
            }
        }

        // Convert list to array
        return mergedList.toArray(new int[mergedList.size()][]);
    }
}
