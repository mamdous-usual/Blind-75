/*
 * LeetCode #105: Construct Binary Tree from Preorder and Inorder Traversal
 * https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
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
// Approach: Recursive DFS (Preorder)
// (with HashMap Optimization)
// Time: O(n) | Space: O(n)
// ========================================
class Solution {

    // Index to track current position in preorder array during traversal
    private int preorderIndex = 0;

    // HashMap to store value-to-index mapping for inorder array for O(1) lookup
    private Map<Integer, Integer> inorderMap = new HashMap<>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // Create a map for quick lookup of indices in inorder array
        // This optimization reduces time complexity from O(n²) to O(n)
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }

        // Start building tree from the entire inorder range
        return buildTreeHelper(preorder, 0, inorder.length - 1);
    }

    private TreeNode buildTreeHelper(int[] preorder, int inorderStart, int inorderEnd) {
        // Base case: if start index exceeds end index, no more nodes to process
        if (inorderStart > inorderEnd) {
            return null;
        }

        // The current root is the next element in preorder traversal
        // Preorder visits root first, so we increment the index after using it
        int rootVal = preorder[preorderIndex++];
        TreeNode root = new TreeNode(rootVal);

        // Find the root's position in inorder array using our HashMap
        // This tells us how to split left and right subtrees
        int rootIndex = inorderMap.get(rootVal);

        // Recursively build left subtree
        // Left subtree contains elements before root in inorder array
        root.left = buildTreeHelper(preorder, inorderStart, rootIndex - 1);

        // Recursively build right subtree
        // Right subtree contains elements after root in inorder array
        root.right = buildTreeHelper(preorder, rootIndex + 1, inorderEnd);

        // Return the constructed root node
        return root;
    }
}
