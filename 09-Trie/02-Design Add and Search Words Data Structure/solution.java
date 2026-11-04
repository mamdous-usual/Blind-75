/*
 * LeetCode #211: Design Add and Search Words Data Structure
 * https://leetcode.com/problems/design-add-and-search-words-data-structure/
 */

// ========================================
// Trie Using Fixed-Size Array (26 children)
// ========================================
class WordDictionary {

  // Inner class representing a node in the Trie data structure
  class TrieNode {
    TrieNode[] children = new TrieNode[26]; // Array to store references to child nodes (26 letters: a-z)
    boolean isEndOfWord = false; // Flag to indicate if this node marks the end of a valid word
  }

  private TrieNode root; // Root node of the Trie

  // Constructor: initializes the WordDictionary with an empty Trie
  public WordDictionary() {
    root = new TrieNode();
  }

  // Adds a word to the dictionary by inserting it into the Trie
  // Time: O(L)
  // Space: O(L)
  // where L is the length of the word being added
  public void addWord(String word) {
    TrieNode current = root; // Start traversal from the root node

    for (int i = 0; i < word.length(); i++) { // Iterate through each character in the word
      char ch = word.charAt(i);
      int index = ch - 'a'; // Convert character to array index (0-25 for 'a'-'z')

      if (current.children[index] == null) { // If the child node doesn't exist, create a new one
        current.children[index] = new TrieNode();
      }

      current = current.children[index]; // Move to the child node for the next character
    }

    current.isEndOfWord = true; // Mark the last node as the end of a word
  }

  // Searches for a word in the dictionary (supports '.' as wildcard for any letter)
  // Case 1: No dots (.)
  // Time: O(L)
  // Space: O(L)
  //
  // Case 2: With dots (.)
  // Time: O(26^W × L)
  // Space: O(L)
  //
  // where:
  // L = length of the word
  // W = number of '.' wildcard characters
  public boolean search(String word) {
    return dfs(word, 0, root); // Start DFS from root at index 0
  }

  // Helper method: performs depth-first search to find word with wildcard support
  private boolean dfs(String word, int index, TrieNode current) {
    if (current == null)
      return false; // Node doesn't exist, word not found

    if (index == word.length()) { // Reached end of word
      return current.isEndOfWord; // Return true only if this is a valid word ending
    }

    char ch = word.charAt(index); // Get current character

    if (ch != '.') { // If not a wildcard, follow the specific path
      return dfs(word, index + 1, current.children[ch - 'a']);
    }

    for (TrieNode child : current.children) { // Wildcard: try all possible children
      if (child != null && dfs(word, index + 1, child)) { // If any path succeeds, return true
        return true;
      }
    }

    return false; // No valid path found
  }
}
