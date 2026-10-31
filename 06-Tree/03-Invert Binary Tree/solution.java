/*
 * LeetCode #226: Invert Binary Tree
 * https://leetcode.com/problems/invert-binary-tree/
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
// Approach: Recursive DFS (Preorder)
// Time: O(n) | Space: O(h)
// where h is the height of the tree
// ========================================
class Solution {
    public TreeNode invertTree(TreeNode root) {
        // Base case: if the current node is null, return null
        if (root == null) {
            return null;
        }

        // Store the left child temporarily
        TreeNode temp = root.left;

        // Swap the left and right children
        root.left = root.right;
        root.right = temp;

        // Recursively invert the left subtree
        invertTree(root.left);

        // Recursively invert the right subtree
        invertTree(root.right);

        // Return the root of the inverted tree
        return root;
    }
}
