/*
 * LeetCode #295: Find Median from Data Stream
 * https://leetcode.com/problems/find-median-from-data-stream/
 */

import java.util.*;

// ========================================
// Approach: Two Heaps
// (Max-Heap + Min-Heap)
// Time: addNum -> O(log n), findMedian -> O(1)
// Space: O(n)
// ========================================
class MedianFinder {

    private PriorityQueue<Integer> smallHalf; // Max heap for smaller half
    private PriorityQueue<Integer> largeHalf; // Min heap for larger half

    // Initialize both heaps
    public MedianFinder() {
        smallHalf = new PriorityQueue<>(Collections.reverseOrder()); // Max heap
        largeHalf = new PriorityQueue<>(); // Min heap (default)
    }

    // Add number and maintain balance between heaps
    public void addNum(int num) {
        // Decide which heap to add to based on comparison
        if (smallHalf.isEmpty() || num <= smallHalf.peek()) {
            smallHalf.offer(num); // Add to smaller half
        } else {
            largeHalf.offer(num); // Add to larger half
        }

        // Rebalance heaps to maintain size constraint
        if (smallHalf.size() > largeHalf.size() + 1) {
            largeHalf.offer(smallHalf.poll()); // Move to balance
        } else if (largeHalf.size() > smallHalf.size()) {
            smallHalf.offer(largeHalf.poll()); // Move to balance
        }
    }

    // Return median from heap roots in O(1) time
    public double findMedian() {
        if (smallHalf.size() > largeHalf.size()) {
            return smallHalf.peek(); // Odd count: return middle
        } else {
            return (smallHalf.peek() + largeHalf.peek()) / 2.0; // Even: average
        }
    }
}
