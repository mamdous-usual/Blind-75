/*
 * LeetCode #102: Binary Tree Level Order Traversal
 * https://leetcode.com/problems/binary-tree-level-order-traversal/
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
// Approach: Iterative BFS (with a Queue)
// Time: O(n) | Space: O(n)
// ========================================
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>(); // Initialize result list to store all levels

        // Handle edge case: empty tree
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>(); // Use a queue for BFS traversal
        queue.offer(root);

        // Process nodes level by level
        while (!queue.isEmpty()) {
            int levelSize = queue.size(); // Get the number of nodes at current level
            List<Integer> currentLevel = new ArrayList<>(); // List to store current level values

            for (int i = 0; i < levelSize; i++) { // Process all nodes at the current level
                TreeNode node = queue.poll(); // Remove and process the next node from queue
                currentLevel.add(node.val); // Add current node's value to current level list

                if (node.left != null) {
                    queue.offer(node.left); // Add left child to queue for next level
                }
                if (node.right != null) {
                    queue.offer(node.right); // Add right child to queue for next level
                }
            }

            result.add(currentLevel); // Add the completed current level to result
        }

        // Return the level-order traversal result
        return result;
    }
}
