/*
 * LeetCode #70: Climbing Stairs
 * https://leetcode.com/problems/climbing-stairs/
 */

// ========================================
// Approach 1: Brute Force (Recursion)
// Time: O(2^n) | Space: O(n)   // recursion stack
// ========================================
/*
class Solution {
    public int climbStairs(int n) {
        // Base case: if there are 0 or 1 stairs, there's only one way to climb
        if (n <= 1)
            return 1;

        // Recursive case: total ways = ways to reach (n-1) + ways to reach (n-2)
        // This is because you can reach stair n by taking 1 step from (n-1) or 2 steps from (n-2)
        return climbStairs(n - 1) + climbStairs(n - 2);
    }
}
*/

// ========================================
// Approach 2: Dynamic Programming (Memoization / Top-Down)
// Time: O(n) | Space: O(n)
// ========================================
/*
class Solution {
    // Main method to calculate the number of ways to climb n stairs
    public int climbStairs(int n) {
        // Initialize memoization array to store computed results
        int[] memo = new int[n + 1];
        return climb(n, memo);
    }

    // Recursive helper method with memoization
    private int climb(int n, int[] memo) {
        // Base case: 0 or 1 stairs have only 1 way to climb
        if (n <= 1) {
            return 1;
        }

        // If result already computed, return cached value
        if (memo[n] != 0) {
            return memo[n];
        }

        // Calculate ways to climb n stairs: sum of ways from (n-1) and (n-2)
        memo[n] = climb(n - 1, memo) + climb(n - 2, memo);

        // Return and cache the result
        return memo[n];
    }
}
*/

// ========================================
// Approach 3: Dynamic Programming (Bottom-Up Tabulation)
// Time: O(n) | Space: O(n)
// ========================================
/*
class Solution {
    public int climbStairs(int n) {
        // Base case: if there are 0 or 1 stairs, there's only one way to climb
        if (n <= 1)
            return 1;

        // Create a dynamic programming array to store the number of ways to reach each step 
        // (index represents the step number)
        int[] dp = new int[n + 1];

        // Initialize base cases
        dp[0] = 1; // One way to stay at ground (do nothing)
        dp[1] = 1; // One way to reach the first stair (climb 1 step)

        // Build up the solution using dynamic programming
        // For each stair, the ways to reach it = ways to reach (i-1) + ways to reach (i-2)
        // because you can arrive from either 1 step below or 2 steps below
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        // Return the number of ways to reach the nth stair
        return dp[n];
    }
}
*/

// ========================================
// Approach 4: Dynamic Programming (Bottom-Up, Space Optimized)
// Time: O(n) | Space: O(1)
// ========================================
class Solution {
    public int climbStairs(int n) {
        // Base case: if there are 0 or 1 stairs, there's only one way to climb
        if (n <= 1)
            return 1;

        // Instead of using an array, we only need to track the last two values
        // This optimizes space complexity from O(n) to O(1)
        int prev2 = 1; // Number of ways to reach step (i-2), initially step 0
        int prev1 = 1; // Number of ways to reach step (i-1), initially step 1

        // Build up the solution iteratively
        // For each stair from 2 to n, calculate ways to reach it
        for (int i = 2; i <= n; i++) {
            // Current stair can be reached from either:
            // - Taking 1 step from prev1 (i-1), OR
            // - Taking 2 steps from prev2 (i-2)
            int current = prev1 + prev2;

            // Shift the window forward for the next iteration
            prev2 = prev1; // What was (i-1) becomes (i-2)
            prev1 = current; // Current becomes the new (i-1)
        }

        // prev1 now holds the number of ways to reach the nth stair
        return prev1;
    }
}
