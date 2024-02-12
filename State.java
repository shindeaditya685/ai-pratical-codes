package org.task.ai;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class State implements Comparable<State> {
    public int f;
    public String[][] blocks;
    public int level;

    public State(String[][] a, int level) {
        int N = a.length;
        this.blocks = new String[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                this.blocks[i][j] = a[i][j];
            }
        }
        this.level = level;
        this.f = manhattan() + level;
    }

    private int manhattan() {
        int sum = 0;
        int[] index = new int[2];
        int N = Solution.goal.length;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (this.blocks[i][j].trim().isEmpty()) {
                    continue;
                }
                index = find_index(Integer.parseInt(this.blocks[i][j]));
                sum = sum + (Math.abs(i - index[0]) + Math.abs(j - index[1]));
            }
        }
        return sum;
    }

    private int[] find_index(int a) {
        int[] index = new int[2];
        int N = Solution.goal.length;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (Solution.goal[i][j].trim().isEmpty()) {
                    continue;
                }
                if (Solution.goal[i][j].trim().equals(String.valueOf(a))) {
                    index[0] = i;
                    index[1] = j;
                    return index;
                }
            }
        }
        return index;
    }

    public ArrayList<State> expand(State parent) {
        ArrayList<State> successor = new ArrayList<State>();
        int N = this.blocks.length;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (parent.blocks[i][j].trim().isEmpty()) {
                    if (i - 1 >= 0) {
                        String[][] a = new String[N][N];
                        for (int l = 0; l < N; l++) {
                            for (int m = 0; m < N; m++) {
                                a[l][m] = parent.blocks[l][m];
                            }
                        }
                        a = swap(a, i, j, i - 1, j);
                        State b = new State(a, parent.level + 1);
                        successor.add(b);
                        System.out.println("MOVED: TOP");
                    }
                    if (j - 1 >= 0) {
                        String[][] a = new String[N][N];
                        for (int l = 0; l < N; l++) {
                            for (int m = 0; m < N; m++) {
                                a[l][m] = parent.blocks[l][m];
                            }
                        }
                        a = swap(a, i, j, i, j - 1);
                        State b = new State(a, parent.level + 1);
                        successor.add(b);
                        System.out.println("MOVED: LEFT");
                    }
                    if (i + 1 < N) {
                        String[][] a = new String[N][N];
                        for (int l = 0; l < N; l++) {
                            for (int m = 0; m < N; m++) {
                                a[l][m] = parent.blocks[l][m];
                            }
                        }
                        a = swap(a, i, j, i + 1, j);
                        State b = new State(a, parent.level + 1);
                        successor.add(b);
                        System.out.println("MOVED: DOWN");
                    }
                    if (j + 1 < N) {
                        String[][] a = new String[N][N];
                        for (int l = 0; l < N; l++) {
                            for (int m = 0; m < N; m++) {
                                a[l][m] = parent.blocks[l][m];
                            }
                        }
                        a = swap(a, i, j, i, j + 1);
                        State b = new State(a, parent.level + 1);
                        successor.add(b);
                        System.out.println("MOVED: RIGHT");
                    }
                }
            }
        }
        return successor;
    }

    private String[][] swap(String[][] a, int row1, int col1, int row2, int col2) {
        String[][] copy = a;
        String tmp = copy[row1][col1];
        copy[row1][col1] = copy[row2][col2];
        copy[row2][col2] = tmp;

        return copy;
    }

    @Override
    public int compareTo(State o) {
        if (this.f == o.f) {
            return ((this.manhattan() - o.manhattan()));
        }
        return this.f - o.f;
    }
}


package org.a_star_algorithm;

import java.util.ArrayList;

public class State implements Comparable<State> {
    public String[][] blocks;
    public int level;
    public int f;
    public String direction; // Added attribute to track direction of movement

    // Constructor to initialize state with the given configuration, level, and direction
    public State(String[][] blocks, int level, String direction) {
        this.blocks = blocks;
        this.level = level;
        this.f = manhattan() + level; // Calculate total cost f
        this.direction = direction;
    }

    // Calculate Manhattan distance heuristic for the state
    private int manhattan() {
        int sum = 0;
        int N = blocks.length;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!blocks[i][j].trim().isEmpty() && !blocks[i][j].equals("_")) {
                    int value = Integer.parseInt(blocks[i][j]);
                    int[] index = findIndex(value);
                    sum += Math.abs(i - index[0]) + Math.abs(j - index[1]);
                }
            }
        }
        return sum;
    }

    // Find indices of a particular element in the goal state
    private int[] findIndex(int value) {
        int N = Solution.goal.length;
        int[] index = new int[2];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (Solution.goal[i][j].equals(String.valueOf(value))) {
                    index[0] = i;
                    index[1] = j;
                    return index;
                }
            }
        }
        return index;
    }

    // Generate all possible child states from the current state
    public ArrayList<State> expand() {
        ArrayList<State> successors = new ArrayList<>();
        int N = blocks.length;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (blocks[i][j].equals("_")) {
                    if (i - 1 >= 0)
                        successors.add(createChildState(i, j, i - 1, j, "down")); // Direction: Up
                    if (j - 1 >= 0)
                        successors.add(createChildState(i, j, i, j - 1, "right")); // Direction: Left
                    if (i + 1 < N)
                        successors.add(createChildState(i, j, i + 1, j, "up")); // Direction: Down
                    if (j + 1 < N)
                        successors.add(createChildState(i, j, i, j + 1, "left")); // Direction: Right
                    break;
                }
            }
        }
        return successors;
    }

    // Create a child state by swapping the empty space with a neighboring tile
    private State createChildState(int row1, int col1, int row2, int col2, String direction) {
        String[][] newBlocks = copyBlocks();
        String temp = newBlocks[row1][col1];
        newBlocks[row1][col1] = newBlocks[row2][col2];
        newBlocks[row2][col2] = temp;
        return new State(newBlocks, level + 1, direction); // Pass direction
    }

    // Utility method to copy blocks array
    private String[][] copyBlocks() {
        int N = blocks.length;
        String[][] newBlocks = new String[N][N];
        for (int i = 0; i < N; i++) {
            System.arraycopy(blocks[i], 0, newBlocks[i], 0, N);
        }
        return newBlocks;
    }

    // Calculate the final cost g(n)
    public int finalCost() {
        return level; // Final cost is equal to the level of the state
    }

    // Override the compareTo method to compare State objects based on their total cost 'f'
    @Override
    public int compareTo(State o) {
        if (this.f == o.f) {
            // If total cost 'f' is equal, compare based on final cost 'g(n)'
            return Integer.compare(finalCost(), o.finalCost());
        }
        return Integer.compare(this.f, o.f);
    }
}
