/*
 * LeetCode #191: Number of 1 Bits
 * https://leetcode.com/problems/number-of-1-bits/
 */

// ========================================
// Approach 1: Brute Force (Binary String)
// Time: O(1) | Space: O(1)
// ========================================
/*
class Solution {
    public int hammingWeight(int n) {
        // Convert the integer to a 32-bit binary string with leading zeros
        String binaryStr = String.format(
                "%32s",
                Integer.toBinaryString(n)
        ).replace(' ', '0');

        int count = 0;

        // Loop through all 32 bits
        for (int i = 0; i < 32; i++) {
            // If the current bit is '1', increment the count
            if (binaryStr.charAt(i) == '1') {
                count++;
            }
        }

        // Return the total number of set bits
        return count;
    }
}
*/

// ========================================
// Approach 2: Brute Force (Bitwise AND + Right Shift)
// Time: O(1) | Space: O(1)
// ========================================
/*
class Solution {
    public int hammingWeight(int n) {
        int count = 0;

        // Keep checking the rightmost bit until n becomes 0
        while (n != 0) {
            // Check if rightmost bit is 1
            if ((n & 1) == 1) {
                count++;
            }

            // Right shift to check next bit
            n = n >>> 1;
        }

        return count;
    }
}
*/

// ========================================
// Approach 3: Brian Kernighan’s Algorithm
// Time: O(1) | Space: O(1)
// ========================================
class Solution {
    public int hammingWeight(int n) {
        int count = 0;

        // Keep removing the rightmost set bit until n becomes 0
        while (n != 0) {
            // Remove the rightmost set bit
            n = n & (n - 1);

            // Increment count for each set bit removed
            count++;
        }

        return count;
    }
}
