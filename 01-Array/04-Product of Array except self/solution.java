/*
 * LeetCode #238: Product of Array Except Self
 * https://leetcode.com/problems/product-of-array-except-self/
 */

// ========================================
// Approach 1: Prefix-Suffix Products
// Time: O(n) | Space: O(n)
// ========================================
/*
class Solution {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;

        // Create arrays to store prefix and suffix products, plus result array
        int[] prefixProducts = new int[n];
        int[] suffixProducts = new int[n];
        int[] result = new int[n];

        // Build prefix products array
        // prefixProducts[i] contains the product of all elements before index i
        prefixProducts[0] = 1; // No elements before index 0, so product is 1
        for (int i = 1; i < n; i++) {
            prefixProducts[i] = prefixProducts[i - 1] * nums[i - 1];
        }

        // Build suffix products array
        // suffixProducts[i] contains the product of all elements after index i
        suffixProducts[n - 1] = 1; // No elements after last index, so product is 1
        for (int i = n - 2; i >= 0; i--) {
            suffixProducts[i] = suffixProducts[i + 1] * nums[i + 1];
        }

        // Calculate final result by multiplying prefix and suffix products
        // For each index i, result[i] = product of all elements except nums[i]
        for (int i = 0; i < n; i++) {
            result[i] = prefixProducts[i] * suffixProducts[i];
        }

        return result;
    }
}
*/

// ========================================
// Approach 2: Prefix-Suffix Products (Optimized)
// Time: O(n) | Space: O(1) extra
// ========================================
class Solution {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];

        // First pass: Calculate prefix products
        // For each position i, result[i] will contain the product of all elements before index i
        int prefixProduct = 1;
        for (int i = 0; i < n; i++) {
            result[i] = prefixProduct; // Store product of all elements to the left of i
            prefixProduct = prefixProduct * nums[i]; // Update prefix product to include nums[i]
        }

        // Second pass: Calculate suffix products and multiply with prefix products
        // For each position i, multiply the existing prefix product with the product of all elements after index i
        int suffixProduct = 1;
        for (int i = n - 1; i >= 0; i--) {
            result[i] = result[i] * suffixProduct; // Multiply prefix product with suffix product
            suffixProduct = suffixProduct * nums[i]; // Update suffix product to include nums[i]
        }

        return result;
    }
}
