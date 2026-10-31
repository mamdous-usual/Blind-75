/*
 * LeetCode #98: Validate Binary Search Tree
 * https://leetcode.com/problems/validate-binary-search-tree/
 */

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
// Approach: Recursive Bounds Checking (with Min-Max)
// Time: O(n) | Space: O(h)
// where h is the height of the tree
// ========================================
class Solution {

    public boolean isValidBST(TreeNode root) {
        // Start validation with the full range of possible values
        // Using Long.MIN_VALUE and Long.MAX_VALUE to handle edge cases
        // where node values might be Integer.MIN_VALUE or Integer.MAX_VALUE
        return validate(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean validate(TreeNode node, long minVal, long maxVal) {
        // Base case: empty subtree is considered a valid BST
        if (node == null) {
            return true;
        }

        // Check if current node's value violates BST property
        // Node value must be strictly greater than minVal and strictly less than maxVal
        if (node.val <= minVal || node.val >= maxVal) {
            return false;
        }

        // Recursively validate left and right subtrees with updated bounds:
        // - Left subtree: all values must be less than current node's value
        //   so maxVal becomes node.val (minVal stays the same)
        // - Right subtree: all values must be greater than current node's value
        //   so minVal becomes node.val (maxVal stays the same)
        return validate(node.left, minVal, node.val) &&
               validate(node.right, node.val, maxVal);
    }
}
