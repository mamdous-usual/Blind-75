/*
 * LeetCode #208: Implement Trie (Prefix Tree)
 * https://leetcode.com/problems/implement-trie-prefix-tree/
 */

// ========================================
// Trie Using Fixed-Size Array (26 children)
// ========================================
class Trie { // Main Trie data structure class

    class TrieNode { // Inner class representing each node in the trie
        TrieNode[] children; // Array to store child nodes (26 for a-z)
        boolean isEndOfWord; // Flag to mark if this node represents end of a word

        public TrieNode() { // Constructor for TrieNode
            children = new TrieNode[26]; // Initialize array for 26 lowercase letters
            isEndOfWord = false; // Initially not an end of word
        }
    }

    private TrieNode root; // Root node of the trie

    public Trie() { // Constructor for Trie
        root = new TrieNode(); // Initialize root node
    }

    // ----------------------------------------
    // insert
    // Time: O(L)
    // Space: O(L)
    // where L is the length of the word being inserted
    // ----------------------------------------
    public void insert(String word) { // Method to insert a word into the trie
        TrieNode current = root; // Start traversal from root
        for (int i = 0; i < word.length(); i++) { // Iterate through each character
            char ch = word.charAt(i); // Get current character
            int index = ch - 'a'; // Convert char to array index (a=0, b=1, etc.)
            if (current.children[index] == null) { // If path doesn't exist
                current.children[index] = new TrieNode(); // Create new node
            }
            current = current.children[index]; // Move to child node
        }
        current.isEndOfWord = true; // Mark end of word at final node
    }

    // ----------------------------------------
    // search
    // Time: O(L)
    // Space: O(1)
    // where L is the length of the word being searched
    // ----------------------------------------
    public boolean search(String word) { // Method to search for a complete word
        TrieNode current = root; // Start traversal from root
        for (int i = 0; i < word.length(); i++) { // Iterate through each character
            char ch = word.charAt(i); // Get current character
            int index = ch - 'a'; // Convert char to array index
            if (current.children[index] == null) { // If path doesn't exist
                return false; // Word not found
            }
            current = current.children[index]; // Move to child node
        }
        return current.isEndOfWord; // Return true only if it's a complete word
    }

    // ----------------------------------------
    // startsWith
    // Time: O(P)
    // Space: O(1)
    // where P is the length of the prefix
    // ----------------------------------------
    public boolean startsWith(String prefix) { // Method to check if any word starts with prefix
        TrieNode current = root; // Start traversal from root
        for (int i = 0; i < prefix.length(); i++) { // Iterate through each character
            char ch = prefix.charAt(i); // Get current character
            int index = ch - 'a'; // Convert char to array index
            if (current.children[index] == null) { // If path doesn't exist
                return false; // No words with this prefix
            }
            current = current.children[index]; // Move to child node
        }
        return true; // Prefix exists in trie
    }
}
