/*
 * LeetCode #1143: Longest Common Subsequence
 * https://leetcode.com/problems/longest-common-subsequence/
 */

import java.util.*;


/* ================================================================
   Approach 1: Brute Force — Recursion
   Time:  O(2^(m+n))
   Space: O(m+n)  (recursion stack)
   ================================================================ */
/*
class Solution {

    // Main method to find the length of the longest common subsequence
    public int longestCommonSubsequence(String text1, String text2) {
        // Start the recursive search from the beginning of both strings (index 0, 0)
        return lcs(text1, text2, 0, 0);
    }

    // Recursive helper method to compute LCS
    // i: current index in text1
    // j: current index in text2
    private int lcs(String text1, String text2, int i, int j) {
        // Base case: if we've reached the end of either string,
        // no more characters can be matched, so return 0
        if (i == text1.length() || j == text2.length()) {
            return 0;
        }

        // If characters at current positions match,
        // include this character in LCS and move both pointers forward
        if (text1.charAt(i) == text2.charAt(j)) {
            return 1 + lcs(text1, text2, i + 1, j + 1);
        }

        // If characters don't match, try two options:
        // Option 1: Skip current character in text1, advance i
        int skipText1 = lcs(text1, text2, i + 1, j);

        // Option 2: Skip current character in text2, advance j
        int skipText2 = lcs(text1, text2, i, j + 1);

        // Return the maximum length from both options
        return Math.max(skipText1, skipText2);
    }
}
*/


/* ================================================================
   Approach 2: Dynamic Programming — Memoization (Top-Down)
   Time:  O(m * n)
   Space: O(m * n) + O(m+n recursion stack)
   ================================================================ */
/*
class Solution {

    // Main method to find the length of the longest common subsequence
    public int longestCommonSubsequence(String text1, String text2) {
        // Create a memoization table to store computed results
        // memo[i][j] will store the LCS length starting from index i in text1 and j in text2
        // Initialize with -1 to indicate uncomputed states
        int[][] memo = new int[text1.length()][text2.length()];
        for (int i = 0; i < text1.length(); i++) {
            for (int j = 0; j < text2.length(); j++) {
                memo[i][j] = -1;
            }
        }

        // Start the recursive search from the beginning of both strings
        return lcs(text1, text2, 0, 0, memo);
    }

    // Recursive helper method to compute LCS with memoization
    // i: current index in text1
    // j: current index in text2
    // memo: 2D array to cache previously computed results
    private int lcs(String text1, String text2, int i, int j, int[][] memo) {
        // Base case: if we've reached the end of either string,
        // no more characters can be matched, so return 0
        if (i == text1.length() || j == text2.length()) {
            return 0;
        }

        // Check if we've already computed this subproblem
        // If so, return the cached result to avoid redundant computation
        if (memo[i][j] != -1) {
            return memo[i][j];
        }

        // If characters at current positions match,
        // include this character in LCS and move both pointers forward
        if (text1.charAt(i) == text2.charAt(j)) {
            memo[i][j] = 1 + lcs(text1, text2, i + 1, j + 1, memo);
            return memo[i][j];
        }

        // If characters don't match, try two options:
        // Option 1: Skip current character in text1, advance i
        int skipText1 = lcs(text1, text2, i + 1, j, memo);

        // Option 2: Skip current character in text2, advance j
        int skipText2 = lcs(text1, text2, i, j + 1, memo);

        // Store the maximum length from both options in memo table
        memo[i][j] = Math.max(skipText1, skipText2);

        // Return the computed result
        return memo[i][j];
    }
}
*/


/* ================================================================
   Approach 3: Dynamic Programming — Bottom-Up (Tabulation)
   Time:  O(m * n)
   Space: O(m * n)
   ================================================================ */
class Solution {

    public int longestCommonSubsequence(String text1, String text2) {
        // Get the lengths of both input strings
        int m = text1.length();
        int n = text2.length();

        // Create a 2D DP table with dimensions (m+1) x (n+1)
        // dp[i][j] represents the length of LCS of text1[0...i-1] and text2[0...j-1]
        // Extra row and column (index 0) represent empty string base cases
        int[][] dp = new int[m + 1][n + 1];

        // Iterate through each character of text1 (starting from index 1 in dp table)
        for (int i = 1; i <= m; i++) {
            // Iterate through each character of text2 (starting from index 1 in dp table)
            for (int j = 1; j <= n; j++) {
                // Check if current characters match
                // Note: i-1 and j-1 because dp table is 1-indexed but strings are 0-indexed
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    // Characters match: extend the LCS from the previous diagonal cell
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    // Characters don't match: take the maximum of two possibilities
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        // dp[m][n] contains the final LCS length
        return dp[m][n];
    }
}
