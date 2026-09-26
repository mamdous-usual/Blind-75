/*
 * LeetCode #268: Missing Number
 * https://leetcode.com/problems/missing-number/
 */

// ========================================
// Approach 1: Brute Force (Linear Search)
// Time: O(n²) | Space: O(1)
// ========================================
/*
class Solution {
    public int missingNumber(int[] nums) {
        int n = nums.length;

        // Check each number from 0 to n
        for (int i = 0; i <= n; i++) {
            boolean found = false;

            // Search for number i in the array
            for (int j = 0; j < nums.length; j++) {
                if (nums[j] == i) {
                    found = true;
                    break;
                }
            }

            // If number i is not found, it's the missing number
            if (!found) {
                return i;
            }
        }

        // This should never be reached given the problem constraints
        return -1;
    }
}
*/

// ========================================
// Approach 2: Sum Formula (Gauss Formula)
// Time: O(n) | Space: O(1)
// ========================================
/*
class Solution {
    public int missingNumber(int[] nums) {
        int n = nums.length; // Total number of elements (excluding the missing one)

        // Calculate the expected sum of numbers from 0 to n using the formula n(n + 1)/2
        int expectedSum = n * (n + 1) / 2;

        int actualSum = 0;
        // Iterate through the array using a traditional for loop
        for (int i = 0; i < n; i++) {
            actualSum = actualSum + nums[i];
        }

        // The missing number is the difference between expected and actual sum
        return expectedSum - actualSum;
    }
}
*/

// ========================================
// Approach 3: XOR
// Time: O(n) | Space: O(1)
// ========================================
class Solution {
    public int missingNumber(int[] nums) {
        int n = nums.length;

        // XOR all numbers from 0 to n (complete expected range)
        int xorExpected = 0;
        for (int i = 0; i <= n; i++) {
            xorExpected = xorExpected ^ i;
        }

        // XOR all numbers in the array
        int xorActual = 0;
        for (int i = 0; i < nums.length; i++) {
            xorActual = xorActual ^ nums[i];
        }

        // XOR both results to get the missing number
        return xorExpected ^ xorActual;
    }
}
