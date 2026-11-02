/*
 * LeetCode #57: Insert Interval
 * https://leetcode.com/problems/insert-interval/
 */

import java.util.*;

// ========================================
// Approach 1: Brute Force / Merge All
// Time: O(n log n) | Space: O(n)
// ========================================
/*
class Solution {

    public int[][] insert(int[][] intervals, int[] newInterval) {
        // Create a new array with a size one larger than the original to hold all intervals.
        int[][] newIntervals = new int[intervals.length + 1][2];

        // Copy all the original intervals into the new array.
        for (int i = 0; i < intervals.length; i++) {
            newIntervals[i] = intervals[i];
        }

        // Add the new interval to the very end of the new array.
        newIntervals[intervals.length] = newInterval;

        // Call a helper function 'merge' to sort the combined list and merge any overlapping intervals.
        // The 'merge' function is expected to handle the core merging logic.
        return merge(newIntervals);
    }

    private int[][] merge(int[][] intervals) {
        // Handle edge cases (empty or only one interval)
        if (intervals.length <= 1) {
            return intervals; // No merging needed
        }

        // Sort intervals based on the start time
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        // Initialize result list with the first interval
        List<int[]> mergedList = new ArrayList<>();
        mergedList.add(intervals[0]);

        // Iterate through the rest of the intervals
        for (int i = 1; i < intervals.length; i++) {
            int[] currentInterval = intervals[i]; // Current interval being processed

            int prevEnd = mergedList.get(mergedList.size() - 1)[1]; // End time of last merged interval

            int currentStart = currentInterval[0]; // Start time of current interval
            int currentEnd = currentInterval[1];   // End time of current interval

            if (prevEnd >= currentStart) {
                // If current interval overlaps with the last one in mergedList
                mergedList.get(mergedList.size() - 1)[1] =
                        Math.max(prevEnd, currentEnd); // Merge them
            } else {
                // No overlap, add current interval as is
                mergedList.add(currentInterval);
            }
        }

        // Convert merged list back to 2D array
        return mergedList.toArray(new int[mergedList.size()][]);
    }
}
*/

// ========================================
// Approach 2: Greedy Linear Scan (Optimal)
// Time: O(n) | Space: O(n)
// ========================================
class Solution {

    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> result = new ArrayList<>(); // Result list to store merged intervals
        int i = 0;
        int n = intervals.length;

        // Add all intervals that end before the new interval starts (no overlap)
        while (i < n && intervals[i][1] < newInterval[0]) {
            result.add(intervals[i]); // Safe to add as-is
            i++;
        }

        // Merge all intervals that overlap with newInterval
        while (i < n && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]); // Update start
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]); // Update end
            i++;
        }
        result.add(newInterval); // Add the merged newInterval

        // Add all remaining intervals (after the merged newInterval)
        while (i < n) {
            result.add(intervals[i]); // These start after newInterval ends
            i++;
        }

        // Convert the result list to a 2D array and return
        return result.toArray(new int[result.size()][]);
    }
}
