/*
 * LeetCode #79: Word Search
 * https://leetcode.com/problems/word-search/
 */

// ========================================
// Approach: Brute Force (DFS Backtracking)
// Time: O(m * n * 4^L) | Space: O(L)
// where L is the length of the word
// ========================================
class Solution {

    public boolean exist(char[][] board, String word) {
        // Get the number of rows
        int m = board.length;

        // Get the number of columns
        int n = board[0].length;

        // Loop through each cell in the board
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // If the current cell matches the first character of the word
                if (board[i][j] == word.charAt(0)) {
                    // Start DFS from this cell to check if the word exists
                    if (dfs(board, i, j, word, 0)) {
                        return true; // word found
                    }
                }
            }
        }

        // word not found after checking all paths
        return false;
    }

    private boolean dfs(char[][] board, int i, int j, String word, int index) {
        // If we've matched all characters in the word, return true
        if (index == word.length()) {
            return true;
        }

        // If out of bounds or current cell doesn't match the current character, return false
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length ||
            board[i][j] != word.charAt(index)) {
            return false;
        }

        // Store the current character and mark the cell as visited
        char temp = board[i][j];
        board[i][j] = '#';

        // Recursively explore all four directions (down, right, up, left)
        boolean found =
                dfs(board, i + 1, j, word, index + 1) ||
                dfs(board, i, j + 1, word, index + 1) ||
                dfs(board, i - 1, j, word, index + 1) ||
                dfs(board, i, j - 1, word, index + 1);

        // Restore the original character after backtracking
        board[i][j] = temp;

        // Return true if the word was found in any direction
        return found;
    }
}
