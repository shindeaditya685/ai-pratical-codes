import java.util.*;

public class GraphTraversal {
    static final int MAX_NODES = 100; // maximum number of nodes

    static ArrayList<Integer>[] adjacencyList = new ArrayList[MAX_NODES]; // adjacency list
    static int[] distances = new int[MAX_NODES]; // distance array for BFS
    static boolean[] visited = new boolean[MAX_NODES]; // visited array for DFS

    // DFS function
    static void depthFirstSearch(int node) {
        visited[node] = true;
        System.out.print(node + " ");

        for (int neighbor : adjacencyList[node]) {
            if (!visited[neighbor]) {
                depthFirstSearch(neighbor);
            }
        }
    }

    // BFS function
    static void breadthFirstSearch(int node) {
        Arrays.fill(distances, -1);
        distances[node] = 0;

        Queue<Integer> queue = new LinkedList<>();
        queue.add(node);

        while (!queue.isEmpty()) {
            int u = queue.poll();
            System.out.print(u + " ");

            for (int v : adjacencyList[u]) {
                if (distances[v] == -1) {
                    distances[v] = distances[u] + 1;
                    queue.add(v);
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numNodes, numEdges; // number of nodes and edges
        System.out.print("Enter the number of nodes: ");
        numNodes = scanner.nextInt();
        System.out.print("Enter the number of edges: ");
        numEdges = scanner.nextInt();

        // initialize adjacency list
        for (int i = 0; i < MAX_NODES; i++) {
            adjacencyList[i] = new ArrayList<>();
        }

        // add edges to the adjacency list
        System.out.println("Enter the edges:");
        for (int i = 0; i < numEdges; i++) {
            int u, v;
            System.out.print("Enter edge " + (i+1) + " (u v): ");
            u = scanner.nextInt();
            v = scanner.nextInt();
            adjacencyList[u].add(v);
            adjacencyList[v].add(u); // Assuming undirected graph
        }

        // perform DFS and BFS on the graph
        int startNode;
        System.out.print("Enter the starting node for DFS and BFS: ");
        startNode = scanner.nextInt();

        System.out.print("DFS: ");
        Arrays.fill(visited, false);
        depthFirstSearch(startNode);
        System.out.println();

        System.out.print("BFS: ");
        breadthFirstSearch
