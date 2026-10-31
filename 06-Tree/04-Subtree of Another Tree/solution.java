/*
 * LeetCode #572: Subtree of Another Tree
 * https://leetcode.com/problems/subtree-of-another-tree/
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
// Approach: Recursive DFS (Preorder + Preorder)
// Time: O(m * n) | Space: O(h1 + h2)
// where h1 = height of root tree, h2 = height of subRoot tree
// ========================================
class Solution {

    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        // Base case: if root is null, subRoot cannot be a subtree
        if (root == null) {
            return false;
        }

        // Check if the current root matches the subRoot exactly
        if (isSameTree(root, subRoot)) {
            return true;
        }

        // Recursively check if subRoot is a subtree of either left or right subtree
        return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }

    private boolean isSameTree(TreeNode p, TreeNode q) {
        // Base case: both nodes are null (reached end of both trees)
        if (p == null && q == null) {
            return true;
        }

        // One node is null but the other isn't - trees are different
        if (p == null || q == null) {
            return false;
        }

        // Both nodes exist but have different values
        if (p.val != q.val) {
            return false;
        }

        // Recursively check if left subtrees match AND right subtrees match
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
