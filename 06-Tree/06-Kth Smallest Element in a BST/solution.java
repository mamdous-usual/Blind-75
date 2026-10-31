/*
 * LeetCode #230: Kth Smallest Element in a BST
 * https://leetcode.com/problems/kth-smallest-element-in-a-bst/
 */

import java.util.*;

// Definition for a binary tree node.
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

// ========================================
// Approach 1: Recursive DFS (Inorder with a List)
// Time: O(n) | Space: O(n)
// ========================================
/*
class Solution {
    public int kthSmallest(TreeNode root, int k) {
        // Create a list to store the inorder traversal result
        List<Integer> inorderList = new ArrayList<>();

        // Perform inorder traversal to get sorted sequence of values
        inorder(root, inorderList);

        // Return the kth smallest element (convert from 1-indexed to 0-indexed)
        return inorderList.get(k - 1); // kth smallest (1-indexed)
    }

    private void inorder(TreeNode node, List<Integer> list) {
        // Base case: if node is null, return (end of branch reached)
        if (node == null)
            return;

        // Recursively traverse the left subtree first
        inorder(node.left, list);

        // Process current node by adding its value to the list
        list.add(node.val);

        // Recursively traverse the right subtree last
        inorder(node.right, list);
    }
}
*/

// ========================================
// Approach 2: Recursive DFS (Inorder with a Counter)
// Time: O(h + k) | Space: O(h)
// where h is the height of the tree
// and k is the index of the smallest element to find (1-indexed)
// ========================================
class Solution {

    public int kthSmallest(TreeNode root, int k) {
        // Use arrays to maintain state across recursive calls (pass-by-reference simulation)
        int[] count = { 0 }; // Counter to track how many nodes we've processed
        int[] result = { 0 }; // Store the kth smallest value when found

        // Perform inorder traversal to find the kth smallest element
        inorder(root, k, count, result);

        // Return the kth smallest element
        return result[0];
    }

    private void inorder(TreeNode node, int k, int[] count, int[] result) {
        // Base case: if node is null, return (end of branch reached)
        if (node == null)
            return;

        // Step 1: Recursively traverse the left subtree first (smaller values)
        inorder(node.left, k, count, result);

        // Step 2: Process current node
        count[0]++; // Increment the counter for visited nodes
        if (count[0] == k) { // Check if this is the kth smallest element
            result[0] = node.val; // Store the kth smallest value
            return; // Early termination - we found what we're looking for
        }

        // Step 3: Recursively traverse the right subtree last (larger values)
        // Only reached if we haven't found the kth element yet
        inorder(node.right, k, count, result);
    }
}
