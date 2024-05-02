import java.util.*;

class GraphTraversal {
    static ArrayList<Integer>[] graph;
    static boolean[] visited;
    
    static void dfs(int node) {
        visited[node] = true;
        System.out.print(node + " ");
        
        for (int neighbor : graph[node]) {
            if (!visited[neighbor]) {
                dfs(neighbor);
            }
        }
    }
    
    static void bfs(int startNode) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[graph.length];
        queue.add(startNode);
        visited[startNode] = true;
        
        while (!queue.isEmpty()) {
            int u = queue.poll();
            System.out.print(u + " ");
            
            for (int v : graph[u]) {
                if (!visited[v]) {
                    visited[v] = true;
                    queue.add(v);
                }
            }
        }
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Enter number of nodes: ");
        int numNodes = scanner.nextInt();
        
        System.out.println("Enter number of edges: ");
        int numEdges = scanner.nextInt();
        
        graph = new ArrayList[numNodes];
        for (int i = 0; i < numNodes; i++) {
            graph[i] = new ArrayList<>();
        }
        
        System.out.println("Enter edges: ");
        for (int i = 0; i < numEdges; i++) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            graph[u].add(v);
            graph[v].add(u);
        }
        
        System.out.println("Enter the starting node for DFS and BFS: ");
        int startNode = scanner.nextInt();
        
        System.out.print("DFS: ");
        visited = new boolean[numNodes];
        dfs(startNode);
        System.out.println();

        System.out.print("BFS: ");
        bfs(startNode);
    }
}
