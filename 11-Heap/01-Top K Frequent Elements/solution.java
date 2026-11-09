/*
 * LeetCode #347: Top K Frequent Elements
 * https://leetcode.com/problems/top-k-frequent-elements/
 */

import java.util.*;

// ========================================
// Approach 1: Brute Force (Sorting)
// Time: O(n log n) | Space: O(n)
// ========================================
/*
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        // Step 1: Count frequency of each element
        Map<Integer, Integer> frequencyMap = new HashMap<>(); // Create HashMap to store element->frequency mapping
        for (int num : nums) { // Iterate through input array
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1); // Increment frequency count for each element
        }

        // Step 2: Create a list of all unique elements
        List<Integer> uniqueElements = new ArrayList<>(frequencyMap.keySet()); // Extract all unique elements from HashMap keys

        // Step 3: Sort elements by frequency in descending order (brute force sorting)
        uniqueElements.sort((a, b) -> frequencyMap.get(b) - frequencyMap.get(a)); // Sort by frequency: higher frequency first

        // Step 4: Return first k elements
        int[] result = new int[k]; // Create result array of size k
        for (int i = 0; i < k; i++) { // Loop k times to get top k elements
            result[i] = uniqueElements.get(i); // Copy sorted elements to result array
        }

        return result; // Return top k frequent elements
    }
}
*/

// ========================================
// Approach 2: Min-Heap (Of Size K)
// Time: O(n log k) | Space: O(n)
// ========================================
/*
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        // Step 1: Count frequencies
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            frequencyMap.put(nums[i], frequencyMap.getOrDefault(nums[i], 0) + 1);
        }

        // Step 2: Use min heap to keep track of k most frequent elements
        // Heap stores entries ordered by frequency (min frequency at top)
        PriorityQueue<Map.Entry<Integer, Integer>> minHeap =
                new PriorityQueue<>((a, b) -> a.getValue() - b.getValue());

        // Step 3: Process each unique element
        for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
            minHeap.offer(entry);

            // Keep only k elements in heap
            if (minHeap.size() > k) {
                minHeap.poll(); // Remove element with minimum frequency
            }
        }

        // Step 4: Extract results
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = minHeap.poll().getKey();
        }

        return result;
    }
}
*/

// ========================================
// Approach 3: Bucket Sort
// Time: O(n) | Space: O(n)
// ========================================
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        // Step 1: Count frequencies
        Map<Integer, Integer> frequencyMap = new HashMap<>(); // Stores frequency of each number
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i]; // Get the current number
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1); // Increment its count in the map
        }

        // Step 2: Create buckets where index = frequency
        List<Integer>[] buckets = new List[nums.length + 1]; // Buckets for frequencies (index = freq)
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<>(); // Initialize each bucket as an empty list
        }

        // Step 2.5: Place numbers into buckets based on their frequency
        for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
            int num = entry.getKey(); // The number
            int frequency = entry.getValue(); // Its frequency
            buckets[frequency].add(num); // Add it to the corresponding bucket
        }

        // Step 3: Collect k most frequent elements directly into array
        int[] result = new int[k]; // Result array to store top k elements
        int index = 0; // Pointer to fill result array
        
        for (int i = buckets.length - 1; i >= 0 && index < k; i--) { // Traverse buckets from highest frequency
            for (int j = 0; j < buckets[i].size(); j++) { // Traverse current bucket
                result[index++] = buckets[i].get(j); // Add the element to result
                if (index == k) // If we've added k elements, stop
                    break;
            }
        }

        return result; // Return the result array
    }
}
