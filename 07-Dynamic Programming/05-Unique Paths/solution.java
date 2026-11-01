/*
 * LeetCode #62: Unique Paths
 * https://leetcode.com/problems/unique-paths/
 */

// ======================================================================
// Approach 1: Dynamic Programming — Bottom-up (Tabulation)
// Time:  O(m * n)
// Space: O(m * n)
// ======================================================================
/*
public int uniquePaths(int m, int n) {
  // Create a 2D DP table to store number of paths to each cell
  // dp[i][j] = number of unique paths to reach cell (i, j)
  int[][] dp = new int[m][n];

  // Base case: Fill first row with 1s
  // There's only 1 way to reach any cell in first row (move right only)
  for (int j = 0; j < n; j++) {
    dp[0][j] = 1;
  }

  // Base case: Fill first column with 1s
  // There's only 1 way to reach any cell in first column (move down only)
  for (int i = 0; i < m; i++) {
    dp[i][0] = 1;
  }

  // Fill the rest of the DP table
  // For each cell, paths = paths from above + paths from left
  for (int i = 1; i < m; i++) {
    for (int j = 1; j < n; j++) {
      // Current cell can be reached from:
      // 1. Cell above: dp[i-1][j]
      // 2. Cell to the left: dp[i][j-1]
      dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
    }
  }

  // Bottom-right cell contains total unique paths
  return dp[m - 1][n - 1];
}
*/

// ======================================================================
// Approach 2: Mathematical (Combinatorics)
// Time:  O(min(m, n))
// Space: O(1)
// ======================================================================
public int uniquePaths(int m, int n) {
  // Problem: Find number of unique paths from top-left (0,0) to bottom-right (m-1,n-1)
  // Can only move RIGHT or DOWN

  // Mathematical insight:
  // - Need exactly (m-1) RIGHT moves and (n-1) DOWN moves
  // - Total moves = (m-1) + (n-1) = m+n-2
  // - Question becomes: "In how many ways can we arrange these moves?"
  // - Answer: Combination formula C(m+n-2, m-1) = (m+n-2)! / ((m-1)! × (n-1)!)

  int totalMoves = m + n - 2; // Total number of moves needed
  int k = Math.min(m - 1, n - 1); // Use smaller value to optimize calculation

  long result = 1; // Use long to prevent overflow during calculation

  // Calculate combination C(totalMoves, k) using optimized formula
  // Instead of calculating large factorials, we use:
  // C(n,k) = (n × (n-1) × ... × (n-k+1)) / (k × (k-1) × ... × 1)
  //
  // We multiply and divide in same loop to keep numbers manageable
  for (int i = 0; i < k; i++) {
    // Multiply by (totalMoves - i) from numerator
    // Divide by (i + 1) from denominator
    // Order matters: multiply first to maintain integer division accuracy
    result = result * (totalMoves - i) / (i + 1);
  }

  // Cast back to int (safe because grid paths fit in int range)
  return (int) result;
}
