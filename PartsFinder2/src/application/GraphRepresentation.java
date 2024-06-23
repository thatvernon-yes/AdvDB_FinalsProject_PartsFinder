package application;

import java.util.*;

public class GraphRepresentation {

    private Map<String, List<Pair>> adjacencyList;

    public GraphRepresentation() {
        this.adjacencyList = new HashMap<>();
    }

    // Method to add edge to the graph
    public void addEdge(String u, String v, int weight) {
        adjacencyList.putIfAbsent(u, new ArrayList<>());
        adjacencyList.putIfAbsent(v, new ArrayList<>());
        adjacencyList.get(u).add(new Pair(v, weight));
        adjacencyList.get(v).add(new Pair(u, weight)); // If the graph is undirected
    }

    // Method to find shortest distances using Dijkstra's algorithm
    public Map<String, Integer> dijkstra(String startVertex) {
        // Priority queue to select the vertex with the minimum distance
        PriorityQueue<Pair> pq = new PriorityQueue<>(Comparator.comparingInt(pair -> pair.weight));
        // Map to store the shortest distance to each vertex
        Map<String, Integer> distances = new HashMap<>();
        // Set to keep track of visited vertices
        Set<String> visited = new HashSet<>();
        
        // Initialize distances to infinity
        for (String vertex : adjacencyList.keySet()) {
            distances.put(vertex, Integer.MAX_VALUE);
        }
        distances.put(startVertex, 0);
        pq.add(new Pair(startVertex, 0));

        while (!pq.isEmpty()) {
            Pair current = pq.poll();
            String currentVertex = current.vertex;
            if (visited.contains(currentVertex)) continue;
            visited.add(currentVertex);

            for (Pair neighbor : adjacencyList.get(currentVertex)) {
                String neighborVertex = neighbor.vertex;
                int edgeWeight = neighbor.weight;
                if (distances.get(currentVertex) + edgeWeight < distances.get(neighborVertex)) {
                    distances.put(neighborVertex, distances.get(currentVertex) + edgeWeight);
                    pq.add(new Pair(neighborVertex, distances.get(neighborVertex)));
                }
            }
        }
        return distances;
    }

    // Method to get sorted list of vertices based on distances from the start vertex
    public List<Map.Entry<String, Integer>> getSortedVerticesWithDistances(String startVertex) {
        Map<String, Integer> distances = dijkstra(startVertex);
        List<Map.Entry<String, Integer>> sortedList = new ArrayList<>(distances.entrySet());
        sortedList.sort(Map.Entry.comparingByValue());
        return sortedList;
    }

//    public static void main(String[] args) {
//        GraphRepresentation graph = new GraphRepresentation();
//        
//        // Example graph
//        graph.addEdge("A", "B", 4);
//        graph.addEdge("A", "C", 1);
//        graph.addEdge("B", "C", 2);
//        graph.addEdge("B", "D", 5);
//        graph.addEdge("C", "D", 8);
//        graph.addEdge("C", "E", 10);
//        graph.addEdge("D", "E", 2);
//
//        // Get sorted vertices and their distances from vertex "A"
//        List<Map.Entry<String, Integer>> sortedVertices = graph.getSortedVerticesWithDistances("A");
//        System.out.println("Vertices and distances from A, sorted from nearest to farthest:");
//        for (Map.Entry<String, Integer> entry : sortedVertices) {
//            System.out.println("Vertex: " + entry.getKey() + ", Distance: " + entry.getValue());
//        }
//    }

    // Pair class to represent edges and weights
    private static class Pair {
        String vertex;
        int weight;

        Pair(String vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }
    }
}