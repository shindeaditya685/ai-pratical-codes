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
