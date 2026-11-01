/*
 * LeetCode #39: Combination Sum
 * https://leetcode.com/problems/combination-sum/
 */

// ========================================
// Approach: Backtracking – DFS (With Pruning)
// Time: O(2^T)   — where T = target
// Space: O(T)    — where T = target (recursion depth + combination storage)
// ========================================

import java.util.ArrayList;
import java.util.List;

class Solution {

    // Main method to find all unique combinations that sum to target
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        // Initialize result list to store all valid combinations
        List<List<Integer>> result = new ArrayList<>();

        // Start backtracking from index 0 with an empty current combination
        backtrack(candidates, target, 0, new ArrayList<>(), result);

        return result;
    }

    // Recursive backtracking helper method
    private void backtrack(int[] candidates, int target, int start, List<Integer> current, List<List<Integer>> result) {
        // Base case 1: Success! We found a valid combination
        if (target == 0) { // When target becomes exactly 0, the current combination sums to original target
            // Add a copy of current combination to result. 
            result.add(new ArrayList<>(current)); // We create a new ArrayList to avoid reference issues
            return;
        }

        // Base case 2: Failure - we've overshot the target. 
        if (target < 0) { // If target becomes negative, this path cannot lead to a solution
            return;
        }

        // Explore all possible candidates starting from 'start' index
        // Starting from 'start' ensures we don't create duplicate combinations
        // For example, [2,3] and [3,2] are considered the same
        for (int i = start; i < candidates.length; i++) {

            // Step 1: Make a choice - include current candidate in combination
            current.add(candidates[i]);

            // Step 2: Recurse with updated state
            // - Subtract current candidate from target (target - candidates[i])
            // - Pass 'i' (not i+1) to allow reusing the same element
            // - This is the key difference from Combination Sum II
            backtrack(candidates, target - candidates[i], i, current, result);

            // Step 3: Backtrack - undo the choice we made
            // Remove the last element to try other possibilities
            // This restores 'current' to its state before we added candidates[i]
            current.remove(current.size() - 1);
        }
    }
}
