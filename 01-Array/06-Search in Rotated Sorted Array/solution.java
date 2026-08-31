/*
 * LeetCode #33: Search in Rotated Sorted Array
 * https://leetcode.com/problems/search-in-rotated-sorted-array/
 */

// ========================================
// Approach 1: Brute Force (Linear Scan)
// Time: O(n) | Space: O(1)
// ========================================
/*
class Solution {
    public int search(int[] nums, int target) {
        // Iterate through each element in the array
        for (int i = 0; i < nums.length; i++) {
            // Check if the current element matches the target value
            if (nums[i] == target) {
                // Return the index if target is found
                return i;
            }
        }
        // Return -1 if target is not found in the array
        return -1;
    }
}
*/

// ========================================
// Approach 2: Binary Search (Modified)
// Time: O(log n) | Space: O(1)
// ========================================
class Solution {
    public int search(int[] nums, int target) {
        // Initialize two pointers for binary search
        int left = 0;                 // Start of search range
        int right = nums.length - 1;  // End of search range

        // Continue searching while the search space is valid
        while (left <= right) {
            // Calculate middle index (avoiding integer overflow)
            int mid = left + (right - left) / 2; // Safe midpoint calculation

            // Target found at mid position
            if (nums[mid] == target) {
                return mid; // Return index where target found
            }

            // Check if the left half is sorted (not rotated)
            if (nums[left] <= nums[mid]) {
                // Left half is sorted
                // Check if target lies within the sorted left half
                if (nums[left] <= target && target < nums[mid]) {
                    // Target is in the sorted left half, search there
                    right = mid - 1; // Narrow search to left half
                } else {
                    // Target is not in the sorted left half, search right half
                    left = mid + 1; // Narrow search to right half
                }
            } else {
                // Right half must be sorted (since left half contains the rotation point)
                // Check if target lies within the sorted right half
                if (nums[mid] < target && target <= nums[right]) {
                    // Target is in the sorted right half, search there
                    left = mid + 1; // Narrow search to right half
                } else {
                    // Target is not in the sorted right half, search left half
                    right = mid - 1; // Narrow search to left half
                }
            }
        }

        // Target not found in the array
        return -1; // Indicate target not found
    }
}
