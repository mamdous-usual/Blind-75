/* 
 * LeetCode #212: Word Search II
 * https://leetcode.com/problems/word-search-ii/
 */

// ========================================
// Approach: Trie + DFS (Backtracking)
// Time: O(m · n · 3^L), where m · n is the board size and L is the maximum length of a word.
// Space: O(T + L), where T is the total number of characters in the Trie and L is the recursion stack depth.
// ========================================

import java.util.*;

class Solution {

  // Inner class representing each node in the Trie
  class TrieNode {
    TrieNode[] children = new TrieNode[26]; // Child nodes for 'a' to 'z'
    String word = null; // Stores the complete word if this node marks end of a word
  }

  private TrieNode root = new TrieNode(); // Root node of the Trie
  private List<String> result = new ArrayList<>(); // List to store found words
  private int rows, cols; // Dimensions of the board

  // Main method to find all words from the list that exist on the board
  public List<String> findWords(char[][] board, String[] words) {

    // Step 1: Build Trie from all words
    for (String word : words) {
      insert(word); // Insert each word into the Trie
    }

    rows = board.length;
    cols = board[0].length;

    // Step 2: Start DFS from every cell on the board
    for (int r = 0; r < rows; r++) {
      for (int c = 0; c < cols; c++) {
        dfs(board, r, c, root); // Begin search from this cell
      }
    }

    return result; // Return all found words
  }

  // Inserts a word into the Trie
  private void insert(String word) {
    TrieNode current = root; // Start from root

    for (int i = 0; i < word.length(); i++) {
      char ch = word.charAt(i);
      int index = ch - 'a'; // Convert character to index (0–25)

      if (current.children[index] == null) { // Create node if not present
        current.children[index] = new TrieNode();
      }

      current = current.children[index]; // Move to next node
    }

    current.word = word; // Store full word at the end node
  }

  // DFS helper method to explore the board with backtracking
  private void dfs(char[][] board, int r, int c, TrieNode node) {

    // Boundary check: if out of board, stop
    if (r < 0 || c < 0 || r >= rows || c >= cols) return;

    char ch = board[r][c]; // Current character on board

    // If cell already visited OR no matching child in Trie, stop search
    if (ch == '#' || node.children[ch - 'a'] == null) return;

    node = node.children[ch - 'a']; // Move to the child node in Trie

    // If this node represents end of a word, add it to result
    if (node.word != null) {
      result.add(node.word); // Found a valid word
      node.word = null; // Set to null to avoid duplicate entries
    }

    // Mark current cell as visited (so it won't be reused in this path)
    board[r][c] = '#';

    // Explore all 4 adjacent directions
    dfs(board, r + 1, c, node); // Down
    dfs(board, r - 1, c, node); // Up
    dfs(board, r, c + 1, node); // Right
    dfs(board, r, c - 1, node); // Left

    // Restore the original character (backtracking)
    board[r][c] = ch;
  }
}
