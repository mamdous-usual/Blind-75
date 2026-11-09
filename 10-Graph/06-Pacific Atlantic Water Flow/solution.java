/*
 * LeetCode #417: Pacific Atlantic Water Flow
 * https://leetcode.com/problems/pacific-atlantic-water-flow/
 */

import java.util.*;

// ========================================
// Approach: Reverse DFS (From Oceans)
// Time: O(m × n)
// Space: O(m × n)
// where m = number of rows, n = number of columns
// ========================================

class Solution {

  public List<List<Integer>> pacificAtlantic(int[][] heights) {
    List<List<Integer>> result = new ArrayList<>(); // final list of reachable coordinates

    if (heights == null || heights.length == 0 || heights[0].length == 0) { // handle edge case
      return result; // return empty list
    }

    int m = heights.length; // number of rows
    int n = heights[0].length; // number of columns

    boolean[][] pacific = new boolean[m][n];  // cells reachable from Pacific
    boolean[][] atlantic = new boolean[m][n]; // cells reachable from Atlantic

    // Pacific ocean DFS from left edge
    for (int i = 0; i < m; i++) {
      dfs(heights, pacific, i, 0); // first column borders Pacific
    }

    // Pacific ocean DFS from top edge
    for (int j = 0; j < n; j++) {
      dfs(heights, pacific, 0, j); // first row borders Pacific
    }

    // Atlantic ocean DFS from right edge
    for (int i = 0; i < m; i++) {
      dfs(heights, atlantic, i, n - 1); // last column borders Atlantic
    }

    // Atlantic ocean DFS from bottom edge
    for (int j = 0; j < n; j++) {
      dfs(heights, atlantic, m - 1, j); // last row borders Atlantic
    }

    // collect cells reachable by both oceans
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (pacific[i][j] && atlantic[i][j]) { // reachable from both
          result.add(Arrays.asList(i, j)); // add coordinates
        }
      }
    }

    return result; // return the final list
  }

  private void dfs(int[][] heights, boolean[][] visited, int row, int col) {
    int m = heights.length; // rows
    int n = heights[0].length; // columns

    visited[row][col] = true; // mark as visited

    int[][] directions = { {1, 0}, {0, 1}, {-1, 0}, {0, -1} }; // 4-directional movement

    for (int[] dir : directions) { // explore neighbors
      int newRow = row + dir[0]; // next row
      int newCol = col + dir[1]; // next column

      // boundary or already visited check
      if (newRow < 0 || newRow >= m || newCol < 0 || newCol >= n || visited[newRow][newCol]) {
        continue; // skip invalid or visited cells
      }

      // move only if next cell height is >= current (water can flow backwards)
      if (heights[newRow][newCol] >= heights[row][col]) {
        dfs(heights, visited, newRow, newCol); // DFS call
      }
    }
  }
}
