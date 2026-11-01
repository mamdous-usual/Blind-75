/*
 * LeetCode #55: Jump Game
 * https://leetcode.com/problems/jump-game/
 */

import java.util.*;

/* ================================================================
   Approach 1: Dynamic Programming — Bottom-Up (Tabulation)
   Time:  O(n^2)
   Space: O(n)
   ================================================================ */
/*
class Solution {
    public boolean canJump(int[] nums) {
        // Get the length of the array
        int n = nums.length;

        // Create a DP array where dp[i] indicates if position i is reachable
        boolean[] dp = new boolean[n];

        // Base case: we start at position 0, so it's always reachable
        dp[0] = true;

        // Iterate through each position in the array
        for (int i = 0; i < n; i++) {

            // If current position is not reachable, we cannot proceed further
            // Since we process positions left to right, if we can't reach position i, 
            // we can't reach any position beyond it either
            if (!dp[i]) {
                return false;
            }

            // Early termination: if from current position we can jump to 
            // or beyond the last index then we've found a path to the end
            if (i + nums[i] >= n - 1) {
                return true;
            }

            // Mark all positions reachable from current position as true
            // j represents the jump size (from 1 to the maximum allowed at position i)
            for (int j = 1; j <= nums[i]; j++) {

                // Only mark positions within array bounds
                if (i + j < n) {
                    dp[i + j] = true;
                }
            }
        }

        // Return whether the last position is reachable
        return dp[n - 1];
    }
}
*/


/* ================================================================
   Approach 2: Greedy — Backward Traversal (Optimal)
   Time:  O(n)
   Space: O(1)
   ================================================================ */
class Solution {
    public boolean canJump(int[] nums) {

        // Start with last index as goal
        int goal = nums.length - 1;

        // Traverse backwards
        for (int i = nums.length - 2; i >= 0; i--) {

            // If from index i we can reach or cross the goal
            if (i + nums[i] >= goal) {
                goal = i; // Shift goalpost to this index
            }
        }

        // If goal moved to 0, start can reach the end
        return goal == 0;
    }
}
