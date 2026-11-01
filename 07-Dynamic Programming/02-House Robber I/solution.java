/*
 * LeetCode #198: House Robber
 * https://leetcode.com/problems/house-robber/
 */

// ========================================
// Approach 1: Brute Force (Recursion)
// Time: O(2^n) | Space: O(n) recursion stack
// ========================================
/*
class Solution {
    public int rob(int[] nums) {
        // Start robbing from the first house (index 0)
        return robFrom(nums, 0);
    }

    private int robFrom(int[] nums, int i) {
        // Base case: if we've gone past the last house, no more money to rob
        if (i >= nums.length) {
            return 0;
        }

        // Option 1: Rob current house and skip next (move to i+2)
        int rob = nums[i] + robFrom(nums, i + 2);

        // Option 2: Skip current house and consider next (move to i+1)
        int skip = robFrom(nums, i + 1);

        // Return the maximum of both options
        return Math.max(rob, skip);
    }
}
*/

// ========================================
// Approach 2: Dynamic Programming (Memoization / Top-Down)
// Time: O(n) | Space: O(n)
// ========================================
/*
class Solution {
    public int rob(int[] nums) {
        // Create memoization array to store results for each index
        Integer[] memo = new Integer[nums.length];
        // Start robbing from the first house (index 0)
        return robFrom(nums, 0, memo);
    }

    private int robFrom(int[] nums, int i, Integer[] memo) {
        // Base case: if we've gone past the last house, no more money to rob
        if (i >= nums.length)
            return 0;

        // If result for this index is already computed, return it (memoization)
        if (memo[i] != null)
            return memo[i];

        // Option 1: Rob current house and skip next (move to i+2)
        int rob = nums[i] + robFrom(nums, i + 2, memo);

        // Option 2: Skip current house and consider next (move to i+1)
        int skip = robFrom(nums, i + 1, memo);

        // Store and return the maximum of both options
        return memo[i] = Math.max(rob, skip);
    }
}
*/

// ========================================
// Approach 3: Dynamic Programming (Bottom-Up Tabulation)
// Time: O(n) | Space: O(n)
// ========================================
/*
class Solution {
    public int rob(int[] nums) {
        // Edge case: empty array
        if (nums.length == 0)
            return 0;

        // Edge case: only one house to rob
        if (nums.length == 1)
            return nums[0];

        // DP array where dp[i] represents max money that can be robbed up to index i
        int[] dp = new int[nums.length];

        // Base case: first house - can only rob this house
        dp[0] = nums[0];

        // Base case: second house - rob the house with more money
        dp[1] = Math.max(nums[0], nums[1]);

        // Fill the dp array for remaining houses
        for (int i = 2; i < nums.length; i++) {
            // Either skip current house (dp[i-1]) or rob it (dp[i-2] + nums[i])
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }

        // Return the maximum money that can be robbed from all houses
        return dp[nums.length - 1];
    }
}
*/

// ========================================
// Approach 4: Dynamic Programming (Bottom-Up, Space Optimized)
// Time: O(n) | Space: O(1)
// ========================================
class Solution {
    public int rob(int[] nums) {
        // Edge case: empty array
        if (nums.length == 0)
            return 0;

        // Edge case: only one house to rob
        if (nums.length == 1)
            return nums[0];

        // Initialize: max money from first house
        int prev2 = nums[0]; // dp[i-2]

        // Initialize: max money from first two houses (rob either first or second)
        int prev1 = Math.max(nums[0], nums[1]); // dp[i-1]

        // For each subsequent house, decide whether to rob it or skip it
        for (int i = 2; i < nums.length; i++) {
            // Either skip current house (prev1) or rob it (prev2 + nums[i])
            int current = Math.max(prev1, prev2 + nums[i]);

            // Slide the window: shift variables for next iteration
            prev2 = prev1;
            prev1 = current;
        }

        // Return the maximum money that can be robbed
        return prev1;
    }
}
