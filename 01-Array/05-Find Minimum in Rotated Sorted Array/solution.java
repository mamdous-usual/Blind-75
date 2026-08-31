/*
 * LeetCode #153: Find Minimum in Rotated Sorted Array
 * https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/
 */

// ========================================
// Approach 1: Brute Force (Linear Scan)
// Time: O(n) | Space: O(1)
// ========================================
/*
class Solution {
    public int findMin(int[] nums) {
        // Handle edge case: array with only one element
        if (nums.length == 1) {
            return nums[0];
        }

        // Iterate through the array to find the rotation point where nums[i] > nums[i+1]
        for (int i = 0; i < nums.length - 1; i++) {
            // If current element is greater than next element, we found the rotation point
            if (nums[i] > nums[i + 1]) {
                // The next element (nums[i+1]) is the minimum
                return nums[i + 1];
            }
        }

        // If no rotation point found, array is not rotated
        // The first element is the minimum
        return nums[0];
    }
}
*/

// ========================================
// Approach 2: Binary Search
// Time: O(log n) | Space: O(1)
// ========================================
class Solution {
    public int findMin(int[] nums) {
        // Initialize pointers for binary search
        int left = 0, right = nums.length - 1;

        // Continue searching while we haven't narrowed down to a single element
        while (left < right) {
            // Calculate mid point to avoid integer overflow
            int mid = left + (right - left) / 2;

            // If mid element is greater than rightmost element,
            // the minimum must be in the right half (after the rotation point)
            if (nums[mid] > nums[right]) {
                left = mid + 1; // Search right half, excluding mid
            } else {
                // If mid element is less than or equal to rightmost element,
                // the minimum could be at mid or in the left half
                right = mid; // Search left half, including mid
            }
        }

        // When left == right, we've found the minimum element
        return nums[left];
    }
}
