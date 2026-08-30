/*
 * LeetCode #121: Best Time to Buy and Sell Stock
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
 */

// ========================================
// Approach 1: Brute Force (Nested Loops)
// Time: O(n²) | Space: O(1)
// ========================================
/*
class Solution {
    public int maxProfit(int[] prices) {
        // Initialize maximum profit to 0 (no profit if no valid transactions)
        int maxProfit = 0;

        // Iterate through each possible buy day
        for (int i = 0; i < prices.length - 1; i++) {
            // For each buy day, check all possible sell days after it
            for (int j = i + 1; j < prices.length; j++) {
                // Calculate profit for buying on day i and selling on day j
                int currentProfit = prices[j] - prices[i];

                // Update maximum profit if current profit is better
                maxProfit = Math.max(maxProfit, currentProfit);
            }
        }

        // Return the maximum profit found
        return maxProfit;
    }
}
*/

// ========================================
// Approach 2: One Pass (Min-Price Tracking)
// Time: O(n) | Space: O(1)
// ========================================
class Solution {
    public int maxProfit(int[] prices) {
        // Track the maximum profit we can achieve
        int maxProfit = 0;

        // Track the minimum price we've seen so far (best day to buy)
        int minPrice = Integer.MAX_VALUE;

        // Iterate through each day's price
        for (int i = 0; i < prices.length; i++) {
            int currentPrice = prices[i];

            // Update the minimum price if current price is lower
            if (currentPrice < minPrice) {
                minPrice = currentPrice;
            } 
            // Otherwise, check if selling today gives better profit
            else {
                int currentProfit = currentPrice - minPrice;
                maxProfit = Math.max(maxProfit, currentProfit);
            }
        }

        // Return the maximum profit found (0 if no profit possible)
        return maxProfit;
    }
}
