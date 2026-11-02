/*
 * LeetCode #252: Meeting Rooms
 * https://leetcode.com/problems/meeting-rooms/
 */

import java.util.*;

// ========================================
// Approach: Sorting + Linear Scan
// Time: O(n log n) | Space: O(1)
// ========================================
class Solution {

    public boolean canAttendMeetings(int[][] intervals) {
        // Sort intervals by their start time
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        // Check consecutive intervals for overlaps
        for (int i = 0; i < intervals.length - 1; i++) {
            // If one meeting ends after the next one starts, overlap exists
            if (intervals[i][1] > intervals[i + 1][0]) {
                return false;
            }
        }

        // No overlaps found → all meetings can be attended
        return true;
    }
}
