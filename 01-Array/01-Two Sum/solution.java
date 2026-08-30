/*
 * LeetCode #1: Two Sum
 * https://leetcode.com/problems/two-sum/
 */

// ========================================
// Approach 1: Brute Force (Nested Loops)
// Time: O(n²) | Space: O(1)
// ========================================
/*
class Solution {
    public int[] twoSum(int[] nums, int target) {
        // Outer loop: iterate through each element as the first number
        for (int i = 0; i < nums.length - 1; i++) {
            // Inner loop: iterate through remaining elements as the second number
            for (int j = i + 1; j < nums.length; j++) {
                // Check if this pair sums to the target
                if (nums[i] + nums[j] == target) {
                    // Found the pair! Return their indices
                    return new int[] { i, j };
                }
            }
        }
        // No pair found that sums to target - return empty array
        return new int[] {};
    }
}
*/

// ========================================
// Approach 2: Hash Map (Single Pass)
// Time: O(n) | Space: O(n)
// ========================================
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int[] twoSum(int[] nums, int target) {
        // Create a HashMap to store each number as key and its index as value
        // This allows O(1) lookup time to find complements
        Map<Integer, Integer> numToIndex = new HashMap<>();
        
        // Iterate through the array once
        for (int i = 0; i < nums.length; i++) {
            // Calculate what number we need to find to reach the target
            // complement + nums[i] = target, so complement = target - nums[i]
            int complement = target - nums[i];
            
            // Check if we've already seen the complement in our map
            if (numToIndex.containsKey(complement)) {
                // Found the pair! Return the indices of the complement and current number
                // The complement's index comes first since it was encountered earlier
                return new int[] { numToIndex.get(complement), i };
            }
            
            // Store the current number and its index in the map for future lookups
            numToIndex.put(nums[i], i);
        }
        
        // No solution found (though problem guarantees exactly one solution exists)
        return new int[] {};
    }
}
