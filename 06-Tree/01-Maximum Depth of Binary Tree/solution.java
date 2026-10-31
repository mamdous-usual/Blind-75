/*
 * LeetCode #104: Maximum Depth of Binary Tree
 * https://leetcode.com/problems/maximum-depth-of-binary-tree/
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
// Time: O(n) | Space: O(h)
// where h is the height of the tree
// ========================================
class Solution {
    public int maxDepth(TreeNode root) {
        // Base case: if the current node is null, return depth of 0
        if (root == null) {
            return 0;
        }

        // Recursively calculate the maximum depth of the left subtree
        int leftDepth = maxDepth(root.left);

        // Recursively calculate the maximum depth of the right subtree
        int rightDepth = maxDepth(root.right);

        // Return the maximum of left and right depths plus 1 for the current level
        return Math.max(leftDepth, rightDepth) + 1;
    }
}
