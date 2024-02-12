package org.task.ai;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Arrays;
import java.util.Scanner;

public class Solution {
    public static PriorityQueue<State> pq = new PriorityQueue<State>();
    public static ArrayList<State> expanded = new ArrayList<State>();
    public static String[][] goal;

    public Solution(State first) {
        if (first == null) {
            System.out.println("Please provide an input");
        }
        pq.add(first);
        ArrayList<State> list = new ArrayList<State>();
        while (!pq.isEmpty()) {
            int visited;
            State current = pq.poll();
            expanded.add(current);
            if (Arrays.deepEquals(current.blocks, goal)) {
                break;
            }
            list = current.expand(current);

            for (State l : list) {
                visited = 0;
                for (State e : expanded) {
                    if (Arrays.deepEquals(l.blocks, e.blocks)) {
                        visited = 1;
                    }
                }
                if (visited == 1)
                    continue;
                pq.add(l);
            }
        }
    }

    public static void main(String args[]) {
        String a[][];
        int i, j, rows, columns;
        rows = columns = 3;
        Scanner sc = new Scanner(System.in);
        a = new String[rows][columns];
        goal = new String[rows][columns];
        System.out.println("Please input the elements for initial state :");

        for (i = 0; i < a.length; i++) {
            for (j = 0; j < a.length; j++) {
                a[i][j] = sc.nextLine();
                if (a[i][j].length() != 1 || (a[i][j].charAt(0) < '1' && a[i][j].charAt(0) != ' ')
                        || a[i][j].charAt(0) > '8') {
                    System.out.println(
                            "Error: Input should be any number between 1 to 8 or a single space\nProgram Terminated");
                    return;
                }
            }
        }

        System.out.println("Please input the Goal state:");

        for (i = 0; i < goal.length; i++) {
            for (j = 0; j < goal.length; j++) {
                goal[i][j] = sc.nextLine();
                if (goal[i][j].length() != 1 || (goal[i][j].charAt(0) < '1' && goal[i][j].charAt(0) != ' ')
                        || goal[i][j].charAt(0) > '8') {
                    System.out.println(
                            "Error: Input should be any number between 1 to 8 or a single space\nProgram Terminated");
                    return;
                }
            }
        }

        long startTime = System.currentTimeMillis();
        State state = new State(a, 0);
        new Solution(state);

        for (int k = 0; k < expanded.size(); k++) {
            State states = expanded.get(k);
            for (int l = 0; l < 3; l++) {
                for (int m = 0; m < 3; m++) {
                    System.out.print(states.blocks[l][m] + "\t");
                }
                System.out.println();
            }
            System.out.println("f(n) :" + states.f);
            System.out.println("h(n) :" + (states.f - states.level));
            System.out.println("g(n) :" + (states.level));
            if (k < expanded.size() - 1) {
                System.out.println("MOVED: " + getMoveDirection(states, expanded.get(k + 1)));
            }
            System.out.println('\n');
        }

        System.out.println("Total Nodes expanded :" + expanded.size());
        System.out.println("Total Nodes generated:" + (expanded.size() + pq.size()));

        long endTime = System.currentTimeMillis();
        System.out.println("Time Taken in milli seconds: " + (endTime - startTime));
    }

    private static String getMoveDirection(State currentState, State nextState) {
        if (currentState.level == nextState.level - 1) {
            // Determine the direction of the move
            int[] currentPosition = findEmpty(currentState);
            int[] nextPosition = findEmpty(nextState);

            if (currentPosition[0] == nextPosition[0] && currentPosition[1] == nextPosition[1] + 1) {
                return "LEFT";
            } else if (currentPosition[0] == nextPosition[0] && currentPosition[1] == nextPosition[1] - 1) {
                return "RIGHT";
            } else if (currentPosition[0] == nextPosition[0] + 1 && currentPosition[1] == nextPosition[1]) {
                return "UP";
            } else if (currentPosition[0] == nextPosition[0] - 1 && currentPosition[1] == nextPosition[1]) {
                return "DOWN";
            }
        }
        return "";
    }

    private static int[] findEmpty(State state) {
        int[] position = new int[2];
        for (int i = 0; i < state.blocks.length; i++) {
            for (int j = 0; j < state.blocks[i].length; j++) {
                if (state.blocks[i][j].trim().isEmpty()) {
                    position[0] = i;
                    position[1] = j;
                    return position;
                }
            }
        }
        return position;
    }
}
package org.a_star_algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Solution {
    public static PriorityQueue<State> pq = new PriorityQueue<>();
    public static ArrayList<State> expanded = new ArrayList<>();
    public static String[][] goal;

    // Constructor for initializing A* search
    public Solution(State first) {
        if (first == null) {
            System.out.println("Please provide an input");
            return;
        }
        pq.add(first);
        while (!pq.isEmpty()) {
            State current = pq.poll();
            expanded.add(current);
            if (Arrays.deepEquals(current.blocks, goal)) // Check if current state is the goal state
                break;
            ArrayList<State> children = current.expand();
            for (State child : children) {
                if (!expanded.contains(child)) // Check if child state is not already expanded
                    pq.add(child);
            }
        }
    }

    // Main method
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input handling
        System.out.println("Please input the elements for initial state:");
        String[][] initialState = new String[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                initialState[i][j] = sc.next();
            }
        }

        System.out.println("Please input the elements for goal state:");
        String[][] goalState = new String[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                goalState[i][j] = sc.next();
            }
        }

        // Replace whitespaces with underscores in goal state
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (goalState[i][j].equals(" ")) {
                    goalState[i][j] = "_";
                }
            }
        }

        // A* search initialization
        goal = goalState;
        State initialStateObj = new State(initialState, 0, "");
        Solution astar = new Solution(initialStateObj);

        // Output handling
        for (State state : expanded) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    System.out.print(state.blocks[i][j] + "\t");
                }
                System.out.println();
            }
            System.out.println("Direction: " + state.direction); // Print direction
            System.out.println("f(n): " + state.f);
            System.out.println("h(n): " + (state.f - state.level));
            System.out.println("g(n): " + state.level);
            System.out.println();
        }

        // Calculate and print total path cost
        int totalPathCost = expanded.get(expanded.size() - 1).level - expanded.get(0).level;
        System.out.println("Total path cost: " + totalPathCost);
    }
}

