/*
 * LeetCode #435: Non-overlapping Intervals
 * https://leetcode.com/problems/non-overlapping-intervals/
 */

import java.util.*;

// ========================================
// Approach: Greedy
// (Sort by End Time)
// Time: O(n log n) | Space: O(1)
// ========================================
class Solution {

    public int eraseOverlapIntervals(int[][] intervals) {
        // If there are no intervals or just one, no removals are needed
        if (intervals == null || intervals.length <= 1) {
            return 0;
        }

        // Step 1: Sort intervals by their end time (greedy choice)
        Arrays.sort(intervals, (a, b) -> a[1] - b[1]);

        int removals = 0; // Count of intervals to remove
        int prevEnd = intervals[0][1]; // End time of the last non-overlapping interval

        // Step 2: Iterate through all intervals starting from the second one
        for (int i = 1; i < intervals.length; i++) {
            int currStart = intervals[i][0]; // Start of the current interval
            int currEnd = intervals[i][1];   // End of the current interval

            // Case 1: No overlap → update prevEnd
            if (prevEnd <= currStart) {
                prevEnd = currEnd;
            }
            // Case 2: Overlap → remove current interval
            else {
                removals++;
                // Keep prevEnd as is (smaller end time already chosen)
            }
        }

        // Step 3: Return the total number of removals needed
        return removals;
    }
}
