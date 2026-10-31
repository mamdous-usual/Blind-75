/*
 * LeetCode #236: Lowest Common Ancestor of a Binary Tree
 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/
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
// Approach 1: Recursive DFS (Path Comparison)
// Time: O(n) | Space: O(n)
// ========================================
/*
class Solution {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // Find paths from root to both nodes
        List<TreeNode> pathToP = new ArrayList<>();
        List<TreeNode> pathToQ = new ArrayList<>();

        // Get the path from root to node p
        findPath(root, p, pathToP);
        // Get the path from root to node q
        findPath(root, q, pathToQ);

        // Find the last common node in both paths
        TreeNode lca = null;
        int minLength = Math.min(pathToP.size(), pathToQ.size());

        // Compare nodes at each level until paths diverge
        for (int i = 0; i < minLength; i++) {
            if (pathToP.get(i) == pathToQ.get(i)) {
                // Nodes are the same, update LCA
                lca = pathToP.get(i);
            } else {
                // Paths have diverged, stop searching
                break;
            }
        }

        return lca;
    }

    private boolean findPath(TreeNode root, TreeNode target, List<TreeNode> path) {
        // Base case: if current node is null, target not found
        if (root == null) {
            return false;
        }

        // Add current node to path (pre-order traversal)
        path.add(root);

        // If we found the target node, return true
        if (root == target) {
            return true;
        }

        // Recursively search in left and right subtrees
        // If target is found in either subtree, return true
        if (findPath(root.left, target, path) || findPath(root.right, target, path)) {
            return true;
        }

        // If target not found in this path, backtrack by removing current node
        // This ensures the path only contains nodes leading to the target
        path.remove(path.size() - 1);
        return false;
    }
}
*/

// ========================================
// Approach 2: Recursive DFS (Bi-directional Subtree Search)
// Time: O(n) | Space: O(h)
// where h is the height of the tree
// ========================================
class Solution {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // Base case: null node or found one of the target nodes
        if (root == null || root == p || root == q) {
            return root;
        }

        // Search for p and q in left subtree
        TreeNode left = lowestCommonAncestor(root.left, p, q);

        // Search for p and q in right subtree
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        // If both subtrees contain p or q, current node is LCA
        if (left != null && right != null) {
            return root;
        }

        // Return whichever subtree found a node (or LCA)
        return left != null ? left : right;
    }
}
