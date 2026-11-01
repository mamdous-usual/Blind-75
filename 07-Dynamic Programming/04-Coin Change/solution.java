/*
 * LeetCode #322: Coin Change
 * https://leetcode.com/problems/coin-change/
 */

// ========================================
// Approach: Dynamic Programming (Bottom-up, Tabulation)
// Time: O(amount × n)   — where n = number of coin types
// Space: O(amount)
// ========================================

import java.util.Arrays;

class Solution {
    public int coinChange(int[] coins, int amount) {
        // dp[i] represents minimum coins needed to make amount i
        int[] dp = new int[amount + 1];

        // Initialize with a value larger than any possible answer
        // amount + 1 works because we can't need more than 'amount' coins
        Arrays.fill(dp, amount + 1);

        // Base case: 0 coins needed to make amount 0
        dp[0] = 0;

        // Build up solutions for all amounts from 1 to target
        for (int i = 1; i <= amount; i++) {
            // Try each coin
            for (int j = 0; j < coins.length; j++) {
                int coin = coins[j];
                // If this coin can be used (doesn't exceed current amount)
                if (coin <= i) {
                    int remainingAmount = i - coin;
                    int coinsNeededForRemaining = dp[remainingAmount];
                    int totalCoinsWithThisCoin = coinsNeededForRemaining + 1;

                    // Take minimum of current value or using this coin
                    dp[i] = Math.min(dp[i], totalCoinsWithThisCoin);
                }
            }
        }

        // If dp[amount] is still the initial value, amount cannot be formed
        return dp[amount] > amount ? -1 : dp[amount];
    }
}
