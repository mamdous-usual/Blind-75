/*
 * LeetCode #20: Valid Parentheses
 * https://leetcode.com/problems/valid-parentheses/
 */

import java.util.Stack;

// ========================================
// Approach 1: Brute Force (Pair Elimination)
// Time: O(n²) | Space: O(n)
// ========================================
/*
class Solution {
    public boolean isValid(String s) {
        // Keep replacing matching pairs until no changes are made
        // The loop continues as long as there is at least one valid pair in the string
        while (s.contains("()") || s.contains("[]") || s.contains("{}")) {
            // Remove all occurrences of "()" in the string
            s = s.replace("()", "");
            // Remove all occurrences of "[]" in the string
            s = s.replace("[]", "");
            // Remove all occurrences of "{}" in the string
            s = s.replace("{}", "");
            // After these three replacements, some innermost pairs may have been removed
            // The while condition checks again if there are any remaining pairs
        }

        // If all brackets were successfully removed, the string will be empty.
        // Otherwise, some unmatched brackets remain
        return s.isEmpty();
    }
}
*/

// ========================================
// Approach 2: Bracket Validation (using a Stack)
// Time: O(n) | Space: O(n)
// ========================================
class Solution {
    public boolean isValid(String s) {
        // Stack to keep track of expected closing brackets
        Stack<Character> stack = new Stack<>();

        // Iterate through each character in the string
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            // If it's an opening bracket, push the corresponding closing bracket onto the stack
            if (c == '(') {
                stack.push(')');
            } else if (c == '{') {
                stack.push('}');
            } else if (c == '[') {
                stack.push(']');
            } else {
                // This block handles closing brackets
                // If stack is empty, there is no matching opening bracket
                if (stack.isEmpty())
                    return false;

                // Pop the expected closing bracket and check if it matches the current character
                if (stack.pop() != c)
                    return false;
            }
        }

        // If stack is empty, all brackets matched correctly
        return stack.isEmpty();
    }
}
