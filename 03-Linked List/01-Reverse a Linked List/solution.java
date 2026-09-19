/*
 * LeetCode #206: Reverse Linked List
 * https://leetcode.com/problems/reverse-linked-list/
 */

// Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

// ========================================
// Approach 1: Recursive
// Time: O(n) | Space: O(n) (call stack)
// ========================================
/*
class Solution {
    public ListNode reverseList(ListNode head) {
        // Base case: if list is empty or has only one node, return it
        if (head == null || head.next == null) {
            return head;
        }

        // Recursively reverse the rest of the list
        ListNode reversedHead = reverseList(head.next);

        // Make the next node point back to the current node
        head.next.next = head;

        // Break the link to the next node to avoid cycle
        head.next = null;

        // Return the head of the reversed list
        return reversedHead;
    }
}
*/

// ========================================
// Approach 2: Iterative
// Time: O(n) | Space: O(1)
// ========================================
class Solution {
    public ListNode reverseList(ListNode head) {
        // Set the previous node to null (end of reversed list)
        ListNode prev = null;

        // Start with the head of the original list
        ListNode curr = head;

        // Loop until all nodes are processed
        while (curr != null) {
            // Save the next node before breaking the link
            ListNode next = curr.next;

            // Reverse the current node's pointer
            curr.next = prev;

            // Move prev to the current node
            prev = curr;

            // Move curr to the next node in the original list
            curr = next;
        }

        // prev will be the new head of the reversed list
        return prev;
    }
}
