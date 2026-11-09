/*
 * LeetCode #200: Number of Islands
 * https://leetcode.com/problems/number-of-islands/
 */

import java.util.*;

// ========================================
// Approach: DFS (Recursive)
// Time: O(m × n) | Space: O(m × n)
// where m = number of rows
//       n = number of columns
// ========================================

class Solution {

  // Counts the number of islands in a 2D grid where '1' represents land and '0' represents water
  // An island is formed by connecting adjacent lands horizontally or vertically
  // Returns the total count of distinct islands found in the grid
  public int numIslands(char[][] grid) {
    // Handle edge cases: null or empty grid
    if (grid == null || grid.length == 0) {
      return 0;
    }

    // Get grid dimensions
    int m = grid.length; // number of rows
    int n = grid[0].length; // number of columns
    int count = 0; // track number of islands found

    // Iterate through every cell in the grid
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        // When we find land ('1'), we've found a new island
        if (grid[i][j] == '1') {
          count++;
          // Use DFS to mark all connected land as visited
          dfs(grid, i, j);
        }
      }
    }

    return count;
  }

  // Performs depth-first search to explore and mark all connected land cells
  // starting from position (i, j). Marks visited cells by changing '1' to '0'
  // This ensures each land cell is only counted once as part of one island
  private void dfs(char[][] grid, int i, int j) {
    // Get grid dimensions
    int m = grid.length;
    int n = grid[0].length;

    // Base case: stop if out of bounds or current cell is water ('0')
    if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] == '0') {
      return;
    }

    // Mark current land cell as visited by changing it to '0'
    grid[i][j] = '0';

    // Recursively explore all 4 adjacent cells (down, up, right, left)
    dfs(grid, i + 1, j); // down
    dfs(grid, i, j + 1); // right
    dfs(grid, i - 1, j); // up
    dfs(grid, i, j - 1); // left
  }
}
