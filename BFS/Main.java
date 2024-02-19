import java.util.*;

class Graph {

    private int V;
    private LinkedList<Integer> adj[];

    Graph(int v) {
        V = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; ++i)
            adj[i] = new LinkedList();
    }

    void addEdge(int v, int w) {
        adj[v].add(w);
    }

    void BFS(int s) {
        boolean visited[] = new boolean[V];
        LinkedList<Integer> queue = new LinkedList<Integer>();
        queue.add(s);
        BFSUtil(visited, queue);
    }

    // Recursive BFS utility function
    void BFSUtil(boolean visited[], LinkedList<Integer> queue) {
        if (queue.isEmpty())
            return;

        int s = queue.poll();
        visited[s] = true;
        System.out.print(s + " ");

        Iterator<Integer> i = adj[s].listIterator();
        while (i.hasNext()) {
            int n = i.next();
            if (!visited[n]) {
                visited[n] = true;
                queue.add(n);
            }
        }

        BFSUtil(visited, queue);
    }

    public static void main(String args[]) {
        Graph g = new Graph(5);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 4);
        g.addEdge(1, 3);
        g.addEdge(3, 4);

        System.out.println("Following is Breadth First Traversal "
                + "(starting from vertex 2)");

        g.BFS(0);
    }
}
