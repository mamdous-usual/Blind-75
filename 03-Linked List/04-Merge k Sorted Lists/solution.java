/*
 * LeetCode #23: Merge k Sorted Lists
 * https://leetcode.com/problems/merge-k-sorted-lists/
 */

import java.util.PriorityQueue;

// Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

// ========================================
// Approach: Min-Heap (Priority Queue)
// Time: O(n log k) | Space: O(k)
// ========================================
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        // Edge case: if input is null or empty, return null
        if (lists == null || lists.length == 0) {
            return null;
        }

        // Min-heap to keep track of the smallest node among all list heads
        PriorityQueue<ListNode> minHeap =
                new PriorityQueue<>((a, b) -> a.val - b.val);

        // Add the head of each non-null list to the min-heap
        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null) {
                minHeap.offer(lists[i]); // Push current list head to heap
            }
        }

        // Dummy node to start the merged list
        ListNode dummy = new ListNode(-1);
        ListNode current = dummy; // Pointer to build the merged list

        // Process the heap until it's empty
        while (!minHeap.isEmpty()) {
            ListNode minNode = minHeap.poll(); // Extract smallest node

            current.next = minNode; // Append to merged list
            current = current.next; // Move current pointer forward

            if (minNode.next != null) {
                minHeap.offer(minNode.next); // Add next node from same list to heap
            }
        }

        // Return head of the merged list
        return dummy.next;
    }
}
