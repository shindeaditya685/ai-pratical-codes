import java.util.*;

class Job implements Comparable<Job> {
    char id;
    int deadline;
    int profit;
    
    Job(char id, int deadline, int profit) {
        this.id = id;
        this.deadline = deadline;
        this.profit = profit;
    }
    
    @Override
    public  int compareTo(Job other) {
        return other.profit - this.profit;
    }
}

public class JobScheduling {
    public static void jobScheduling(Job[] jobs) {
        Arrays.sort(jobs);
        
        int n = jobs.length;
        
        int[] result = new int[n];
        boolean[] slot = new boolean[n];
        int totalProfit = 0;
        
        Arrays.fill(slot, false);
        
        for (int i = 0; i < n; i++) {
            for (int k = Math.min(n, jobs[i].deadline) - 1; k >= 0; k--) {
                if (!slot[k]) {
                    result[k] = i;
                    slot[k] = true;
                    totalProfit += jobs[i].profit;
                    break;
                }
            }
        }
        
        System.out.println("Job sequence: ");
        for (int i = 0; i < n; i++) {
            if (slot[i]) {
                System.out.print(jobs[result[i]].id + " ");
            }
        }
        System.out.println("\nTotal profit: " + totalProfit);
    }
    
    
    
    public static void main (String[] args) {
        Job[] jobs = {
            new Job('a', 2, 100),
            new Job('b', 1, 19),
            new Job('c', 2, 27),
            new Job('d', 1, 25),
            new Job('e', 3, 15)
        };
        
        System.out.println("Original Job Sequence: ");
        for (Job job : jobs) {
            System.out.print(job.id + " ");
        }
        System.out.println();
        
        jobScheduling(jobs);
        
        
    }
}
