/*
 * LeetCode #49: Group Anagrams
 * https://leetcode.com/problems/group-anagrams/
 */

import java.util.*;

// ========================================
// Approach 1: HashMap with Sorted String Key
// Time: O(n * k log k) | Space: O(n * k)
// ========================================
/*
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        // HashMap to store sorted word as key and list of anagrams as value
        Map<String, List<String>> anagramGroups = new HashMap<>();

        // Iterate through each word in the input array
        for (int i = 0; i < strs.length; i++) {
            String word = strs[i];

            // Step 1: Sort the characters of the word
            char[] chars = word.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars); // sorted word is the key

            // Step 2: Store the word in HashMap under its sorted key
            if (!anagramGroups.containsKey(key)) {
                anagramGroups.put(key, new ArrayList<>());
            }

            anagramGroups.get(key).add(word);
        }

        // Step 3: Return all grouped anagrams
        return new ArrayList<>(anagramGroups.values());
    }
}
*/

// ========================================
// Approach 2: HashMap with Frequency String Key
// Time: O(n * k) | Space: O(n * k)
// ========================================
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        // HashMap to store groups of anagrams
        // Key: frequency string representation of characters
        // Value: list of strings that are anagrams (have the same frequency string)
        Map<String, List<String>> anagramGroups = new HashMap<>();

        // Iterate through each string in the input array
        for (int i = 0; i < strs.length; i++) {
            String str = strs[i];

            // Generate a unique key based on character frequencies. Anagrams will have the same frequency key
            String key = getFrequencyString(str);

            // If this is the first string with this frequency pattern, create a new list for this group of anagrams
            if (!anagramGroups.containsKey(key)) {
                anagramGroups.put(key, new ArrayList<>());
            }

            anagramGroups.get(key).add(str); // Add the current string to its corresponding anagram group
        }

        // Convert the map values to a list of lists for the return format
        return new ArrayList<>(anagramGroups.values());
    }

    private String getFrequencyString(String str) {
        // Array to count frequency of each lowercase letter (a-z)
        // Index 0 = 'a', Index 1 = 'b', etc.
        int[] charFrequency = new int[26];

        // Count the frequency of each character in the string
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            // Convert character to array index (a=0, b=1, c=2, etc.)
            int index = c - 'a';
            charFrequency[index]++;
        }

        // Build the frequency string representation
        StringBuilder frequencyStr = new StringBuilder();
        for (int i = 0; i < charFrequency.length; i++) {
            // Only include characters that appear in the string
            if (charFrequency[i] > 0) {
                // Append character followed by its count (e.g., "a2", "b1")
                frequencyStr.append((char) ('a' + i)).append(charFrequency[i]);
            }
        }

        return frequencyStr.toString();
    }
}
