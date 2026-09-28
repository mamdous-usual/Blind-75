/*
 * LeetCode #54: Spiral Matrix
 * https://leetcode.com/problems/spiral-matrix/
 */

import java.util.*;

// ========================================
// Approach: Layer by Layer (Boundary)
// Time: O(m * n) | Space: O(1) extra (excluding output)
// ========================================
class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        // Initialize the list to store the spiral order result
        List<Integer> output = new ArrayList<>();

        // Initialize variables to keep track of the current boundaries
        int rowBegin = 0;
        int rowEnd = matrix.length - 1;
        int colBegin = 0;
        int colEnd = matrix[0].length - 1;

        // Traverse the matrix in a spiral manner
        while (rowBegin <= rowEnd && colBegin <= colEnd) {

            // Traverse Right - move across the top row
            for (int k = colBegin; k <= colEnd; k++) {
                output.add(matrix[rowBegin][k]);
            }
            rowBegin++; // Move the top boundary down

            // Traverse Down - move down the rightmost column
            for (int k = rowBegin; k <= rowEnd; k++) {
                output.add(matrix[k][colEnd]);
            }
            colEnd--; // Move the right boundary left

            // Traverse Left - move across the bottom row if rows remain
            if (rowBegin <= rowEnd) {
                for (int k = colEnd; k >= colBegin; k--) {
                    output.add(matrix[rowEnd][k]);
                }
                rowEnd--; // Move the bottom boundary up
            }

            // Traverse Up - move up the leftmost column if columns remain
            if (colBegin <= colEnd) {
                for (int k = rowEnd; k >= rowBegin; k--) {
                    output.add(matrix[k][colBegin]);
                }
                colBegin++; // Move the left boundary right
            }
        }

        // Return the collected elements in spiral order
        return output;
    }
}
