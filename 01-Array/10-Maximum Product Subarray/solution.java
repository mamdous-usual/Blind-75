/*
 * LeetCode #152: Maximum Product Subarray
 * https://leetcode.com/problems/maximum-product-subarray/
 */

// ========================================
// Approach 1: Brute Force (Optimized)
// Time: O(n²) | Space: O(1)
// ========================================
/*
class Solution {
    public int maxProduct(int[] nums) {
        int n = nums.length;

        // Initialize maxProduct to the smallest possible integer value
        // This ensures any actual product will be larger
        int maxProduct = Integer.MIN_VALUE;

        // Outer loop: try every possible starting position for a subarray
        for (int i = 0; i < n; i++) {
            // Reset product to 1 for each new starting position
            int currentProduct = 1;

            // Inner loop: extend the subarray from position i to the end
            for (int j = i; j < n; j++) {
                // Multiply current product by the next element in the subarray
                currentProduct = currentProduct * nums[j];

                // Update maxProduct if current subarray product is larger
                maxProduct = Math.max(maxProduct, currentProduct);
            }
        }

        // Return the maximum product found among all possible subarrays
        return maxProduct;
    }
}
*/

// ========================================
// Approach 2: Modified Kadane’s Algorithm (Dynamic Programming)
// Track Min & Max Products
// Time: O(n) | Space: O(1)
// ========================================
class Solution {
    public int maxProduct(int[] nums) {
        // Global maximum product found
        int maxSoFar = nums[0];

        // Max product ending at current position
        int maxEndingHere = nums[0];

        // Min product ending at current position (for negative handling)
        int minEndingHere = nums[0];

        for (int i = 1; i < nums.length; i++) {
            int current = nums[i];

            // Find max of: current element, current * maxEndingHere, current * minEndingHere
            int tempMax = Math.max(
                current,
                Math.max(maxEndingHere * current, minEndingHere * current)
            );

            // Find min of: current element, current * maxEndingHere, current * minEndingHere
            int tempMin = Math.min(
                current,
                Math.min(maxEndingHere * current, minEndingHere * current)
            );

            // Update max ending here
            maxEndingHere = tempMax;

            // Update min ending here
            minEndingHere = tempMin;

            // Update global max
            maxSoFar = Math.max(maxSoFar, maxEndingHere);
        }

        return maxSoFar;
    }
}
