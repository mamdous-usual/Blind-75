/*
 * LeetCode #141: Linked List Cycle
 * https://leetcode.com/problems/linked-list-cycle/
 */

import java.util.*;

// Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
    }
}

// ========================================
// Approach 1: Brute Force (HashSet)
// Time: O(n) | Space: O(n)
// ========================================
/*
class Solution {
    public boolean hasCycle(ListNode head) {
        // Create a set to store visited nodes
        Set<ListNode> visitedNodes = new HashSet<>();

        // Start from the head of the list
        ListNode current = head;

        // Traverse the linked list
        while (current != null) {
            // If current node is already visited, a cycle exists
            if (visitedNodes.contains(current)) {
                return true;
            }

            // Mark current node as visited
            visitedNodes.add(current);

            // Move to the next node
            current = current.next;
        }

        // If end of list is reached, no cycle exists
        return false;
    }
}
*/

// ========================================
// Approach 2: Floyd’s Cycle Detection (Tortoise and Hare)
// Time: O(n) | Space: O(1)
// ========================================
class Solution {
    public boolean hasCycle(ListNode head) {
        // If the list is empty or has only one node, it can't have a cycle
        if (head == null || head.next == null) {
            return false;
        }

        // Initialize slow and fast pointers at the head
        ListNode slow = head;
        ListNode fast = head;

        // Traverse the list with two pointers
        while (fast != null && fast.next != null) {
            // Move slow pointer by 1 step
            slow = slow.next;

            // Move fast pointer by 2 steps
            fast = fast.next.next;

            // If slow and fast meet, a cycle exists
            if (slow == fast) {
                return true;
            }
        }

        // If fast reached the end, there is no cycle
        return false;
    }
}
