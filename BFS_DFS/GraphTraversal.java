import java.util.*;

class GraphTraversal {
    static final int MAX_NODES  = 100;
    static ArrayList<Integer>[] graph = new ArrayList[MAX_NODES];
    static int[] distances = new int[MAX_NODES];
    static boolean[] visited = new boolean[MAX_NODES];
    
    static void dfs(int node) {
        visited[node] = true;
        System.out.print(node + " ");
        
        for (int neighbor : graph[node]) {
            if (!visited[neighbor]) {
                dfs(neighbor);
            }
        }
    }
    
    
    static void bfs(int node) {
        Arrays.fill(distances, -1);
        distances[node] = 0;
        
        Queue<Integer> queue = new LinkedList<>();
        queue.add(node);
        
        
        while (!queue.isEmpty()) {
            int u = queue.poll();
            System.out.print(u + " ");
            
            for (int v : graph[u]) {
                if (distances[v] == -1) {
                    distances[v] = distances[u] + 1;
                    queue.add(v);
                }
            }
        }
    }
    
    public static void main (String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Enter number of nodes: ");
        int numNodes = scanner.nextInt();
        
        System.out.println("Enter the number of edges: ");
        int numEdges = scanner.nextInt();
        
        for (int i = 0; i < MAX_NODES; i++) {
            graph[i] = new ArrayList<>();
        }
        
        System.out.println("Enter the edges: ");
        for (int i = 0; i < numEdges; i++) {
            System.out.println("Enter edges " + (i+1) + " (u v): ");
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            graph[u].add(v);
            graph[v].add(u);
        }
        
        System.out.println("Enter the starting node for DFS and BFS: ");
        int startNode = scanner.nextInt();
        
        System.out.print("DFS: ");
        Arrays.fill(visited, false);
        dfs(startNode);
        System.out.println();

        System.out.print("BFS: ");
        bfs(startNode);
    }
}
