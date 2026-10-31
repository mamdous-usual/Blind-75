/*
 * LeetCode #124: Binary Tree Maximum Path Sum
 * https://leetcode.com/problems/binary-tree-maximum-path-sum/
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
// Approach: Recursive DFS (Postorder)
// (with Global Variable)
// Time: O(n) | Space: O(h)
// where n is number of nodes, h is height of the tree
// ========================================
class Solution {

    // Global variable to track the maximum path sum found so far
    private int maxSum;

    public int maxPathSum(TreeNode root) {
        // Initialize maxSum to minimum possible value to handle negative numbers
        maxSum = Integer.MIN_VALUE;
        helper(root);
        return maxSum;
    }

    private int helper(TreeNode node) {
        // Base case: null nodes contribute 0 to any path
        if (node == null) {
            return 0;
        }

        // Get max path sum from left subtree
        // Use Math.max with 0 to ignore negative paths (better to take no path than a negative one)
        int lps = Math.max(helper(node.left), 0);

        // Get max path sum from right subtree
        // Similarly, ignore negative contributions
        int rps = Math.max(helper(node.right), 0);

        // Calculate the maximum path sum that passes through the current node
        // This path includes: left subtree + current node + right subtree
        int cmp = node.val + lps + rps;

        // Update global maximum if this path is better
        maxSum = Math.max(maxSum, cmp);

        // Return the maximum single-branch path sum for the parent node
        // Parent can only extend ONE branch (left OR right), not both
        // This ensures we maintain a valid path (no branching allowed when moving up)
        return node.val + Math.max(lps, rps);
    }
}
