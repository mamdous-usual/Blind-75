/*
 * LeetCode #19: Remove Nth Node From End of List
 * https://leetcode.com/problems/remove-nth-node-from-end-of-list/
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
// Approach 1: Brute Force
// Time: O(n) | Space: O(1)
// ========================================
/*
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // Step 1: Calculate the total length of the list
        int length = 0;
        ListNode current = head;
        while (current != null) {
            length++;
            current = current.next;
        }

        // Step 2: Calculate the position from the beginning (0-based)
        int positionFromStart = length - n;

        // Step 3: Handle edge case - removing the first node
        if (positionFromStart == 0) {
            return head.next;
        }

        // Step 4: Traverse to the node before the one to be removed
        current = head;
        for (int i = 0; i < positionFromStart - 1; i++) {
            current = current.next;
        }

        // Step 5: Remove the nth node from end
        current.next = current.next.next;

        return head;
    }
}
*/

// ========================================
// Approach 2: Two Pointer
// Time: O(n) | Space: O(1)
// ========================================
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0); // Dummy node before head to handle edge cases
        dummy.next = head; // Point dummy to head

        ListNode fast = dummy; // Fast pointer starts at dummy
        ListNode slow = dummy; // Slow pointer also starts at dummy

        // Move fast pointer 'n' steps ahead
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }

        // Move both pointers until fast reaches the last node
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }

        // Skip the target node by adjusting the link
        slow.next = slow.next.next;

        // Return the new head (in case original head was removed)
        return dummy.next;
    }
}
