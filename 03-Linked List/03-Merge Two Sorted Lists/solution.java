/*
 * LeetCode #21: Merge Two Sorted Lists
 * https://leetcode.com/problems/merge-two-sorted-lists/
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
// Time: O(n + m) | Space: O(n + m) (call stack)
// ========================================
/*
class Solution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // Base case: if list1 is empty, return list2
        if (list1 == null)
            return list2;

        // Base case: if list2 is empty, return list1
        if (list2 == null)
            return list1;

        // Recursive case:
        // If list1's value is smaller or equal, link it and recurse on the rest of list1
        if (list1.val <= list2.val) {
            list1.next = mergeTwoLists(list1.next, list2);
            return list1;
        }
        // If list2's value is smaller, link it and recurse on the rest of list2
        else {
            list2.next = mergeTwoLists(list1, list2.next);
            return list2;
        }
    }
}
*/

// ========================================
// Approach 2: Iterative (Two Pointers)
// Time: O(n + m) | Space: O(1)
// ========================================
class Solution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(-1); // Dummy node to simplify edge cases
        ListNode current = dummy; // Pointer to build the merged list

        while (list1 != null && list2 != null) { // Traverse both lists
            if (list1.val < list2.val) {
                current.next = list1; // Link the smaller node to merged list
                list1 = list1.next; // Move pointer in list1
            } else {
                current.next = list2; // Link the smaller/equal node from list2
                list2 = list2.next; // Move pointer in list2
            }
            current = current.next; // Move the merged list pointer
        }

        if (list1 != null) {
            current.next = list1; // Attach remaining nodes of list1
        } else {
            current.next = list2; // Attach remaining nodes of list2
        }

        return dummy.next; // Return head of merged list
    }
}
