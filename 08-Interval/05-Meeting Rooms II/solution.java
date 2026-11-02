/*
 * LeetCode #253: Meeting Rooms II
 * https://leetcode.com/problems/meeting-rooms-ii/
 */

import java.util.*;

// ========================================
// Approach: Min-Heap
// (Priority Queue)
// Time: O(n log n) | Space: O(n)
// ========================================
class Solution {

    public int minMeetingRooms(int[][] intervals) {
        // If there are no meetings, we need 0 rooms
        if (intervals.length == 0)
            return 0;

        // Sort meetings by their start time
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        // Min-heap (priority queue) to keep track of meeting end times
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        // Iterate through all meetings
        for (int i = 0; i < intervals.length; i++) {
            // If no rooms are allocated yet, assign one
            if (pq.size() == 0) {
                pq.add(intervals[i][1]); // Add current meeting's end time
            } else {
                // If the earliest meeting in the heap ends AFTER the current meeting starts → overlap, need a new room
                if (pq.peek() > intervals[i][0]) {
                    pq.add(intervals[i][1]);
                } else {
                    // Otherwise, we can reuse the room
                    pq.remove(); // Remove the room whose meeting ended
                    pq.add(intervals[i][1]); // Assign it to the current meeting
                }
            }
        }

        // The size of the heap is the number of meeting rooms required
        return pq.size();
    }
}
