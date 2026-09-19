/*
 * LeetCode #143: Reorder List
 * https://leetcode.com/problems/reorder-list/
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
// Approach: Split, Reverse, and Merge
// Time: O(n) | Space: O(1)
// ========================================
class Solution {

    public void reorderList(ListNode head) {
        if (head == null || head.next == null) // Edge case: empty or single-node list
            return;

        // Step 1: Find the middle of the list
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null && fast.next.next != null) { // extra fast.next.next for make the first one middle when even list and you can also use (fast != null && fast.next != null) in this question
            slow = slow.next; // slow moves one step
            fast = fast.next.next; // fast moves two steps
        }

        // Step 2: Split the list into two halves
        ListNode first = head; // first half starts from head
        ListNode second = slow.next; // second half starts from node after mid
        slow.next = null; // terminate first half

        // Step 3: Reverse the second half
        second = reverseList(second); // reverse second half

        // Step 4: Merge the two halves alternately
        while (second != null) {
            ListNode tmp1 = first.next; // store next node of first half
            ListNode tmp2 = second.next; // store next node of second half

            first.next = second; // link current first to current second
            second.next = tmp1; // link current second to next of first

            first = tmp1; // move to next node in first half
            second = tmp2; // move to next node in second half
        }
    }

    private ListNode reverseList(ListNode head) {
        // Initialize previous pointer to null (end of reversed list)
        ListNode prev = null;

        // Start current pointer at the head of the list
        ListNode curr = head;

        // Traverse the list and reverse the links
        while (curr != null) {
            // Store the next node temporarily
            ListNode next = curr.next;

            // Reverse the current node's pointer
            curr.next = prev;

            // Move prev and curr one step forward
            prev = curr;
            curr = next;
        }

        // prev will be the new head of the reversed list
        return prev;
    }
}
