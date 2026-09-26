/*
 * LeetCode #190: Reverse Bits
 * https://leetcode.com/problems/reverse-bits/
 */

// ========================================
// Approach: Brute Force (Bit-by-Bit)
// Time: O(1) | Space: O(1)
// ========================================
class Solution {
    public int reverseBits(int n) {
        int result = 0;

        for (int i = 0; i < 32; i++) {
            // Get the least significant bit (LSB) of n
            int lsb = n & 1;

            // Shift result one bit to the left (same as result <<= 1)
            result = result << 1;

            // Add the LSB to result (same as result |= lsb)
            result = result | lsb;

            // Shift n one bit to the right (same as n >>>= 1)
            n = n >>> 1;
        }

        return result;
    }
}
