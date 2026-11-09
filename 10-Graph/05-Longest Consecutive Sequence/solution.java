/*
 * LeetCode #128: Longest Consecutive Sequence
 * https://leetcode.com/problems/longest-consecutive-sequence/
 */

import java.util.*;

// ========================================
// Approach: Start Point Detection (Using a HashSet)
// Time: O(n) | Space: O(n)
// where n = number of elements in the array
// ========================================

class Solution {

  public int longestConsecutive(int[] nums) {
    if (nums.length == 0) { // Handle edge case: empty array has no consecutive sequence
      return 0;
    }

    HashSet<Integer> numSet = new HashSet<>(); // Create a HashSet for O(1) lookup of numbers

    for (int i = 0; i < nums.length; i++) { // Populate the set with all array elements (removes duplicates automatically)
      numSet.add(nums[i]);
    }

    int longestStreak = 0; // Track the longest consecutive sequence found

    // Iterate through the HashSet instead of the original array
    // This ensures each unique number is processed only once
    for (int num : numSet) {

      // Check if this number is the START of a sequence
      // A number is a start if (num - 1) does NOT exist in the set
      if (!numSet.contains(num - 1)) {

        int currentNum = num;       // Initialize sequence counter starting from this number
        int currentStreak = 1;      // Start with streak of 1 (the number itself)

        // Count how long the consecutive sequence extends from this start
        while (numSet.contains(currentNum + 1)) {
          currentNum++;             // Move to next consecutive number
          currentStreak++;          // Increment the streak length
        }

        // Update the longest streak if current sequence is longer
        longestStreak = Math.max(longestStreak, currentStreak);
      }
    }

    return longestStreak; // Return the maximum consecutive sequence length found
  }
}
