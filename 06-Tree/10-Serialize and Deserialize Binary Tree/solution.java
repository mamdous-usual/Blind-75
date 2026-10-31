/*
 * LeetCode #297: Serialize and Deserialize Binary Tree
 * https://leetcode.com/problems/serialize-and-deserialize-binary-tree/
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
// (with Null Marking)
// Time: O(n) | Space: O(n)
// ========================================
public class Codec {

    // Serializes a binary tree to a single string representation using preorder traversal with null markers
    public String serialize(TreeNode root) {
        StringBuilder serializedTree = new StringBuilder();
        serializeHelper(root, serializedTree);
        return serializedTree.toString();
    }

    // Recursive helper method for tree serialization using preorder traversal with null nodes as "N"
    private void serializeHelper(TreeNode root, StringBuilder serializedTree) {
        // Base case: if current node is null, append null marker and return
        if (root == null) {
            serializedTree.append("N,");
            return;
        }

        // Process current node: append its value followed by comma separator
        serializedTree.append(root.val).append(",");

        // Recursively serialize left subtree (preorder: left before right)
        serializeHelper(root.left, serializedTree);

        // Recursively serialize right subtree
        serializeHelper(root.right, serializedTree);
    }

    // =========================================================

    private int index; // Tracks current position in the serialized data array

    // Deserializes a string back into a binary tree using preorder reconstruction
    public TreeNode deserialize(String data) {
        String[] nodes = data.split(",");
        index = 0; // Reset index for each deserialization
        return deserializeHelper(nodes);
    }

    // Recursive helper method that rebuilds the tree from preorder traversal array
    private TreeNode deserializeHelper(String[] nodes) {
        // Base case: if current element is null marker, increment index and return null
        if (nodes[index].equals("N")) {
            index++;
            return null;
        }

        // Parse current node value and create new TreeNode
        int nodeValue = Integer.parseInt(nodes[index]);
        TreeNode node = new TreeNode(nodeValue);
        index++;

        // Recursively build left subtree (preorder: left before right)
        node.left = deserializeHelper(nodes);

        // Recursively build right subtree
        node.right = deserializeHelper(nodes);

        return node;
    }
}
