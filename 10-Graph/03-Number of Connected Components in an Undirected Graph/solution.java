/*
 * LeetCode #323: Number of Connected Components in an Undirected Graph
 * https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph/
 */

import java.util.*;

// ========================================
// Approach: DFS (Recursive)
// Time: O(n + e) | Space: O(n + e)
// where n = number of nodes
//       e = number of edges
// ========================================

class Solution {

  // Counts the number of connected components in an undirected graph
  // n: number of nodes (labeled 0 to n-1)
  // edges: array of edges where each edge connects two nodes
  public int countComponents(int n, int[][] edges) {
    // Build an adjacency list representation of the graph
    List<List<Integer>> graph = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      graph.add(new ArrayList<>()); // Initialize empty list for each node
    }

    // Add edges to the graph (undirected, so add both directions)
    for (int[] edge : edges) {
      graph.get(edge[0]).add(edge[1]); // Add edge from node 0 to node 1
      graph.get(edge[1]).add(edge[0]); // Add edge from node 1 to node 0
    }

    // Track which nodes have been visited during DFS
    boolean[] visited = new boolean[n]; // All initially false
    int count = 0; // Number of connected components found

    // Iterate through all nodes to find connected components
    for (int i = 0; i < n; i++) {
      // If this node hasn't been visited, it's a new component
      if (!visited[i]) {
        dfs(graph, visited, i); // Explore entire component
        count++; // Increment component counter
      }
    }

    return count; // Return total number of components
  }

  // Performs depth-first search to mark all nodes in a connected component as visited
  // graph: adjacency list representation of the graph
  // visited: array tracking which nodes have been visited
  // node: current node being explored
  private void dfs(List<List<Integer>> graph, boolean[] visited, int node) {
    // Mark the current node as visited
    visited[node] = true; // Prevents revisiting this node

    // Recursively visit all unvisited neighbors
    for (int neighbor : graph.get(node)) { // Check each adjacent node
      if (!visited[neighbor]) { // Only visit if not already visited
        dfs(graph, visited, neighbor); // Recursive call for neighbor
      }
    }
  }
}
