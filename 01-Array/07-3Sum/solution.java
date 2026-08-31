/*
 * LeetCode #15: 3Sum
 * https://leetcode.com/problems/3sum/
 */

import java.util.*;

// ========================================
// Approach: Sorting + Two Pointers
// Time: O(n²) | Space: O(n)
// ========================================
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>(); // Initialize result list to store all unique triplets that sum to zero

        Arrays.sort(nums); // Sort array for two-pointer technique and easier duplicate handling

        for (int i = 0; i < nums.length - 2; i++) { // Iterate, fixing first element (stop at length-2 for triplets)
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue; // Skip duplicate first elements to avoid duplicate triplets
            }

            int left = i + 1; // Left pointer after current element
            int right = nums.length - 1; // Right pointer at end of array

            while (left < right) { // Two-pointer search for remaining pair
                int sum = nums[i] + nums[left] + nums[right]; // Calculate sum of current triplet

                if (sum == 0) { // Triplet found
                    result.add(Arrays.asList(nums[i], nums[left], nums[right])); // Add triplet to result

                    while (left < right && nums[left] == nums[left + 1]) {
                        left++; // Skip duplicates on left
                    }
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--; // Skip duplicates on right
                    }

                    left++; // Move left pointer inward
                    right--; // Move right pointer inward
                } else if (sum < 0) { // Sum too small
                    left++; // Move left pointer right to increase sum
                } else { // Sum too large
                    right--; // Move right pointer left to decrease sum
                }
            }
        }

        return result; // Return list of all unique triplets
    }
}
