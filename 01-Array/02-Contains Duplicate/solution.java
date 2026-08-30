/*
 * LeetCode #217: Contains Duplicate
 * https://leetcode.com/problems/contains-duplicate/
 */

import java.util.*;

// ========================================
// Approach 1: Brute Force (Nested Loops)
// Time: O(n²) | Space: O(1)
// ========================================
/*
class Solution {
    public boolean containsDuplicate(int[] nums) {
        // Outer loop: iterate through each element as the reference element
        for (int i = 0; i < nums.length - 1; i++) {
            // Inner loop: compare the reference element with all subsequent elements
            for (int j = i + 1; j < nums.length; j++) {
                // If we find a match, we've found a duplicate
                if (nums[i] == nums[j]) {
                    return true; // Duplicate found
                }
            }
        }
        // No duplicates found
        return false;
    }
}
*/

// ========================================
// Approach 2: Sorting + Adjacent Check
// Time: O(n log n) | Space: O(1) (ignoring sort space)
// ========================================
/*
class Solution {
    public boolean containsDuplicate(int[] nums) {
        // Sort the array so duplicates become adjacent
        Arrays.sort(nums);

        // Check adjacent elements for duplicates
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                return true; // Duplicate found
            }
        }

        // No duplicates found
        return false;
    }
}
*/

// ========================================
// Approach 3: HashSet (Optimal)
// Time: O(n) | Space: O(n)
// ========================================
class Solution {
    public boolean containsDuplicate(int[] nums) {
        // HashSet to store elements we've already seen
        Set<Integer> visited = new HashSet<>();

        // Iterate through the array
        for (int i = 0; i < nums.length; i++) {
            // If element already exists, duplicate found
            if (visited.contains(nums[i])) {
                return true;
            }
            // Otherwise, add element to set
            visited.add(nums[i]);
        }

        // No duplicates found
        return false;
    }
}
