/*
 * LeetCode #261: Graph Valid Tree
 * https://leetcode.com/problems/graph-valid-tree/
 */

import java.util.*;

// ========================================
// Approach: DFS (Recursive)
// Time: O(n + e) | Space: O(n + e)
// where n = number of nodes
//       e = number of edges
// ========================================

class Solution {

  // Validates if the given graph forms a valid tree
  // A valid tree must have exactly n-1 edges, be connected, and have no cycles
  // n: number of nodes (labeled 0 to n-1)
  // edges: array of edges where each edge connects two nodes
  public boolean validTree(int n, int[][] edges) {
    // A tree with n nodes must have exactly n-1 edges
    if (edges.length != n - 1) {
      return false; // Too many or too few edges
    }

    // Build an adjacency list representation of the graph
    List<List<Integer>> graph = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      graph.add(new ArrayList<>()); // Initialize empty list for each node
    }

    for (int[] edge : edges) {
      graph.get(edge[0]).add(edge[1]); // Add edge from node 0 to node 1
      graph.get(edge[1]).add(edge[0]); // Add edge from node 1 to node 0
    }

    // Track which nodes have been visited during DFS
    boolean[] visited = new boolean[n]; // All initially false

    // Check if the graph has a cycle starting from node 0
    if (hasCycle(graph, 0, -1, visited)) {
      return false; // Trees cannot have cycles
    }

    // Check if all nodes are connected (visited)
    for (boolean v : visited) {
      if (!v)
        return false; // Graph is disconnected, not a tree
    }

    return true; // Graph is a valid tree
  }

  // Detects if there's a cycle in the graph using DFS
  // graph: adjacency list representation of the graph
  // node: current node being explored
  // parent: parent node of the current node (to avoid false cycle detection)
  // visited: array tracking which nodes have been visited
  private boolean hasCycle(List<List<Integer>> graph, int node, int parent, boolean[] visited) {
    visited[node] = true; // Mark current node as visited

    // Check all neighbors of the current node
    for (int neighbor : graph.get(node)) {
      if (!visited[neighbor]) { // If neighbor not visited, explore it
        if (hasCycle(graph, neighbor, node, visited)) {
          return true; // Cycle found in recursive call
        }
      } else if (neighbor != parent) { // If visited and not parent, cycle exists
        return true; // Found a back edge, indicating a cycle
      }
    }

    return false; // No cycle found from this node
  }
}
