/*
 * LeetCode #213: House Robber II
 * https://leetcode.com/problems/house-robber-ii/
 */

// ========================================
// Approach 1: Dynamic Programming (Bottom-Up, Tabulation)
// Time: O(n) | Space: O(n)
// ========================================
/*
class Solution {
    public int rob(int[] nums) {
        // Base case: if there's only one house, rob it
        if (nums.length == 1) {
            return nums[0];
        }

        // Create two scenarios to handle the circular constraint:
        // 1. Exclude the last house (can include first house)
        int[] numsExcludingLast = new int[nums.length - 1];
        // 2. Exclude the first house (can include last house)
        int[] numsExcludingFirst = new int[nums.length - 1];

        // Populate both arrays
        for (int i = 0; i < nums.length - 1; i++) {
            numsExcludingLast[i] = nums[i];       // Houses 0 to n-2
            numsExcludingFirst[i] = nums[i + 1];  // Houses 1 to n-1
        }

        // Return the maximum of robbing either scenario
        return Math.max(
            robLinear(numsExcludingLast),
            robLinear(numsExcludingFirst)
        );
    }

    private int robLinear(int[] nums) {
        // Space-optimized DP for linear house arrangement
        int prev2 = 0; // Maximum money robbed up to i-2
        int prev1 = 0; // Maximum money robbed up to i-1

        // For each house, decide whether to rob it or skip it
        for (int i = 0; i < nums.length; i++) {
            // Either skip current house (prev1) or rob it (prev2 + nums[i])
            int current = Math.max(prev1, prev2 + nums[i]);

            // Shift the DP values for next iteration
            prev2 = prev1;
            prev1 = current;
        }

        // prev1 contains the maximum money that can be robbed
        return prev1;
    }
}
*/

// ========================================
// Approach 2: Dynamic Programming (Bottom-Up, Space Optimized)
// Time: O(n) | Space: O(1)
// ========================================
class Solution {
    public int rob(int[] nums) {
        int n = nums.length;

        // Edge case: only one house, rob it
        if (n == 1)
            return nums[0];

        // Edge case: two houses, rob the one with more money
        if (n == 2)
            return Math.max(nums[0], nums[1]);

        // Houses are in a circle, so rob either:
        // 1) Houses 0 to n-2 (exclude last), or
        // 2) Houses 1 to n-1 (exclude first)
        return Math.max(
            robLinear(nums, 0, n - 2),
            robLinear(nums, 1, n - 1)
        );
    }

    private int robLinear(int[] nums, int start, int end) {
        // Track max money from i-2 houses back
        int prev2 = 0;
        // Track max money from i-1 houses back
        int prev1 = 0;

        // Process each house in the range [start, end]
        for (int i = start; i <= end; i++) {
            // Either skip current house (prev1) or rob it (prev2 + nums[i])
            int current = Math.max(prev1, prev2 + nums[i]);

            // Slide the DP window forward
            prev2 = prev1;
            prev1 = current;
        }

        // Return max money that can be robbed from this linear range
        return prev1;
    }
}
