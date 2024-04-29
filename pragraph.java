import java.util.ArrayList;
import java.util.LinkedList;

public class pragraph {
    private int V;
    ArrayList<ArrayList<Integer>> adj;

    pragraph(int V){
        this.V=V;
        adj=new ArrayList<ArrayList<Integer>>(V);

        for (int i = 0; i <V; i++) {
            adj.add(new ArrayList<Integer>());

        }
    }
    void addEdge(int v,int s){
        adj.get(v).add(s);
    }
    
    void BFS(int s){
        boolean[] visited=new boolean[V];
        LinkedList<Integer> queue=new LinkedList<>();

        visited[s]=true;
        queue.add(s);
        
        System.out.println("BFS Traversal (starting from vertex " + s + "):");
        while (queue.isEmpty()) {
            s=queue.poll();
            System.out.print(s + " ");

            for (Integer neighbor : adj.get(s)) {
                if (!visited[neighbor]) {
                    visited[neighbor]=true;
                    queue.add(neighbor);
                }
            }

        }
    } 
    void  dfsUtil(int v,boolean[] visited){
        visited[v]=true;
        System.out.print(v + " ");
        for (int b : adj.get(v)) {
            if (!visited[b]) {
                dfsUtil(b, visited);
            }
        }
}
    void DFS(int s){
        boolean[] visited=new boolean[V];
        System.out.println("\nDFS Traversal (starting from vertex " + s + "):");
        dfsUtil(s, visited);

    }
    public static void main(String[] args) {
        Graph g = new Graph(4);

        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);
    
        g.BFS(2);
        g.DFS(2);
    
    
    }





}