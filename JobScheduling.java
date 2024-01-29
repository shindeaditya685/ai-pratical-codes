package org.task.ai;

import java.util.Arrays;

class Job implements Comparable<Job> {
    char id;
    int deadline;
    int profit;

    public Job(char id, int deadline, int profit) {
        this.id = id;
        this.deadline = deadline;
        this.profit = profit;
    }

    // Sort jobs based on decreasing order of profit
    @Override
    public int compareTo(Job other) {
        return other.profit - this.profit;
    }
}

public class JobScheduling {

    public static void scheduleJobs(Job[] jobs) {
        Arrays.sort(jobs); // Sort jobs based on profit (in descending order)

        int n = jobs.length;
        int[] result = new int[n];
        boolean[] slot = new boolean[n];

        // Initialize slots to be empty
        Arrays.fill(slot, false);

        int totalProfit = 0;

        for (int i = 0; i < n; i++) {
            // Find a slot for the job, starting from the deadline and moving to the beginning
            for (int j = Math.min(n, jobs[i].deadline) - 1; j >= 0; j--) {
                if (!slot[j]) {
                    result[j] = i;  // Assign the job to this slot
                    slot[j] = true;
                    totalProfit += jobs[i].profit;
                    break;
                }
            }
        }

        // Print the scheduled jobs and total profit
        System.out.println("Job sequence: ");
        for (int i : result) {
            System.out.print(jobs[i].id + " ");
        }
        System.out.println("\nTotal Profit: " + totalProfit);
    }

    public static void main(String[] args) {
        // Example jobs with id, deadline, and profit
        Job[] jobs = {
                new Job('a', 5, 200),
                new Job('b', 3, 180),
                new Job('c', 3, 190),
                new Job('d', 2, 300),
                new Job('e', 4, 120),
                new Job('f', 2, 100)
        };

        System.out.println("Original job sequence: ");
        for (Job job : jobs) {
            System.out.print(job.id + " ");
        }
        System.out.println();

        // Apply Job Scheduling algorithm
        scheduleJobs(jobs);
    }
}
