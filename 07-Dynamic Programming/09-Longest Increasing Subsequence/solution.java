/*
 * LeetCode #300: Longest Increasing Subsequence
 * https://leetcode.com/problems/longest-increasing-subsequence/
 */

import java.util.*;

/* ================================================================
   Approach 1: Brute Force — Recursion
   Time:  O(2^n)
   Space: O(n)   (recursion stack)
   ================================================================ */
/*
class Solution {

    // Finds the length of the longest increasing subsequence in the array.
    // This is the main entry point that starts the recursive process.
    public int lengthOfLIS(int[] nums) {
        // Start recursion from index 0 with -1 as the previous index
        // -1 indicates no previous element has been selected yet
        return helper(nums, 0, -1);
    }

    // Recursive helper method that explores all possible subsequences
    // index: current position being considered in the array
    // prevIndex: index of the last element included in the subsequence (-1 if none)
    private int helper(int[] nums, int index, int prevIndex) {
        // Base case: reached the end of the array
        // No more elements to consider, so return 0
        if (index == nums.length) {
            return 0;
        }

        // Option 1: Skip the current element and move to the next
        // Continue searching without including nums[index]
        int skip = helper(nums, index + 1, prevIndex);

        // Option 2: Include the current element (if valid)
        int include = 0;

        // Check if we can include the current element:
        // - prevIndex == -1: no previous element, so we can always include the first one
        // - nums[index] > nums[prevIndex]: current element is greater than the last included element
        if (prevIndex == -1 || nums[index] > nums[prevIndex]) {
            // Include current element: add 1 to the length and update prevIndex to current index
            include = 1 + helper(nums, index + 1, index);
        }

        // Return the maximum length between including and skipping the current element
        return Math.max(include, skip);
    }
}
*/

/* ================================================================
   Approach 2: Dynamic Programming — Memoization (Top-Down)
   Time:  O(n^2)
   Space: O(n^2) + O(n recursion stack)
   ================================================================ */
/*
class Solution {

    // Finds the length of the longest increasing subsequence in the array.
    // This is the main entry point that starts the recursive process with memoization.
    public int lengthOfLIS(int[] nums) {
        // Create a memoization table to store computed results
        // memo[index][prevIndex + 1] stores the LIS length from 'index' with previous element at 'prevIndex'
        // We use prevIndex + 1 because prevIndex can be -1, so we shift it to make it 0-indexed
        int[][] memo = new int[nums.length][nums.length + 1];

        // Initialize memo table with -1 to indicate uncomputed states
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j <= nums.length; j++) {
                memo[i][j] = -1;
            }
        }

        // Start recursion from index 0 with -1 as the previous index
        return helper(nums, 0, -1, memo);
    }

    // Recursive helper method with memoization
    // index: current position being considered in the array
    // prevIndex: index of the last element included in the subsequence (-1 if none)
    // memo: 2D array to cache previously computed results
    private int helper(int[] nums, int index, int prevIndex, int[][] memo) {
        // Base case: reached the end of the array
        if (index == nums.length) {
            return 0;
        }

        // Check if this state has already been computed
        // Shift prevIndex by 1 to handle -1 case (map -1 to 0, 0 to 1, etc.)
        if (memo[index][prevIndex + 1] != -1) {
            return memo[index][prevIndex + 1];
        }

        // Option 1: Skip the current element and move to the next
        int skip = helper(nums, index + 1, prevIndex, memo);

        // Option 2: Include the current element (if valid)
        int include = 0;

        // Check if we can include the current element:
        // - prevIndex == -1: no previous element, so we can include this one
        // - nums[index] > nums[prevIndex]: current element is strictly greater
        if (prevIndex == -1 || nums[index] > nums[prevIndex]) {
            // Include current element and update prevIndex to current index
            include = 1 + helper(nums, index + 1, index, memo);
        }

        // Store the result in memo table before returning
        // This prevents recomputation of the same state
        memo[index][prevIndex + 1] = Math.max(include, skip);

        return memo[index][prevIndex + 1];
    }
}
*/

/* ================================================================
   Approach 3: Dynamic Programming — Bottom-Up (Tabulation)
   Time:  O(n^2)
   Space: O(n)
   ================================================================ */
class Solution {

    public int lengthOfLIS(int[] nums) {
        int n = nums.length;

        // dp[i] represents the length of the longest increasing subsequence ending at index i
        int[] dp = new int[n];

        // Initialize each position with length 1 (each element is a subsequence of length 1)
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
        }

        // Track the maximum length found so far
        int maxLen = 1;

        // For each element, check all previous elements
        for (int i = 1; i < n; i++) {
            // Compare current element with all previous elements
            for (int j = 0; j < i; j++) {
                // If current element is greater, we can extend the subsequence ending at j
                if (nums[i] > nums[j]) {
                    // Update dp[i] to be the max of its current value or extending from j
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            // Update the overall maximum length
            maxLen = Math.max(maxLen, dp[i]);
        }

        // Return the length of the longest increasing subsequence
        return maxLen;
    }
}
