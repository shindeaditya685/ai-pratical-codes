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


