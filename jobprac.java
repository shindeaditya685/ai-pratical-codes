import java.lang.reflect.Array;
import java.util.Arrays;

class job implements Comparable<job> {
    int profit;
    char id;
    int deadline;

    job(char id,int deadline,int profit){
        this.id=id;
        this.deadline=deadline;
        this.profit=profit;

    }
    @Override
    public int compareTo(job other){
        return other.profit-this.profit;
    }
    
}
public class jobprac {
    public static void jobscheduling(job[] j){
        Arrays.sort(j);

        int n=j.length;
        int[] result=new int[n];
        boolean[] slot=new boolean[n];

        int totalprofit=0;

        Arrays.fill(slot, false);

        for (int i = 0; i <n; i++) {
            for (int k = Math.min(n, j[i].deadline)-1; k>=0; k--) {
                if (!slot[k]) {
                    slot[k]=true;
                    result[k]=i;
                    totalprofit+=j[i].profit;
                    break;
                }
            
            }
        }
        System.out.println("Job sequence: ");
        for (int i = 0; i < n; i++)
            if (slot[i])
                System.out.print(j[result[i]].id + " ");
        System.out.println("\nTotal Profit: " + totalprofit);
    
    }
    public static void main(String[] args) {
        job[] jobs = {
            new job('a', 2, 100),
            new job('b', 1, 19),
            new job('c', 2, 27),
            new job('d', 1, 25),
            new job('e', 3, 15)
            
    };

    System.out.println("Original job sequence: ");
    for (job job : jobs) {
        System.out.print(job.id + " ");
    }
    System.out.println();

    // Apply Job Scheduling algorithm
    jobscheduling(jobs);
    }
}
