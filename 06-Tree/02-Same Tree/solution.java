/*
 * LeetCode #100: Same Tree
 * https://leetcode.com/problems/same-tree/
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
    public boolean isSameTree(TreeNode p, TreeNode q) {
        // BASE CASE 1: Both nodes are null (empty subtrees)
        if (p == null && q == null) {
            return true; // Two empty trees are identical
        }

        // BASE CASE 2: One node is null, the other is not
        if (p == null || q == null) {
            return false; // Different structure - one tree has a node, other doesn't
        }

        // BASE CASE 3: Both nodes exist but have different values
        if (p.val != q.val) {
            return false; // Same structure but different values
        }

        // RECURSIVE CASE: Both nodes exist and have same values
        // Check if left subtrees are identical AND right subtrees are identical
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
