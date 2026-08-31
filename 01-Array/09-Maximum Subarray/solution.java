/*
 * LeetCode #53: Maximum Subarray
 * https://leetcode.com/problems/maximum-subarray/
 */

// ========================================
// Approach 1: Brute Force (Optimised)
// Time: O(n²) | Space: O(1)
// ========================================
/*
class Solution {
    public int maxSubArray(int[] nums) {
        // Initialize maxSum to the smallest possible integer value
        // This ensures any subarray sum will be larger
        int maxSum = Integer.MIN_VALUE;

        // Outer loop: iterate through each possible starting position
        for (int i = 0; i < nums.length; i++) {
            // Reset the current sum for each new starting position
            int currentSum = 0;

            // Inner loop: extend the subarray from position i to j
            for (int j = i; j < nums.length; j++) {
                // Add the current element to expand the subarray [i, j]
                currentSum += nums[j];

                // Update maxSum if we found a larger subarray sum
                maxSum = Math.max(maxSum, currentSum);
            }
        }

        // Return the maximum subarray sum found
        return maxSum;
    }
}
*/

// ========================================
// Approach 2: Kadane’s Algorithm (Dynamic Programming)
// Time: O(n) | Space: O(1)
// ========================================
class Solution {
    public int maxSubArray(int[] nums) {
        // Current running sum of the subarray we're considering
        int currentSum = 0;

        // Maximum sum found so far - initialized to smallest possible integer
        // to handle cases where all numbers are negative
        int maxSum = Integer.MIN_VALUE;

        // Iterate through each element in the array
        for (int i = 0; i < nums.length; i++) {
            // Add current element to our running sum.
            // This extends the current subarray to include nums[i]
            currentSum = currentSum + nums[i];

            // Update maxSum if current subarray sum is larger.
            // This captures the best subarray ending at position i
            maxSum = Math.max(maxSum, currentSum);

            // Key insight of Kadane's algorithm:
            // If current sum becomes negative, it's better to start fresh
            // from the next element rather than carrying the negative sum
            // forward because any future subarray would benefit from NOT
            // including the negative prefix
            if (currentSum < 0) {
                currentSum = 0; // Reset to start a new potential subarray
            }
        }

        // Return the maximum sum found across all possible subarrays
        return maxSum;
    }
}
