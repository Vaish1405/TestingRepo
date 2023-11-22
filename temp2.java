import java.util.*;

public class Solution {
    public static int count[] = new int[66];
    public static int puzzle[][] = new int[65][3];
    public static int threadCounts[][] = new int[66][3];
    public static int rotationCount[] = new int[65];

    public static void generatePuzzle() {
        for (int i = 0; i < 65;) {
            for (int j = 0; j < 3;) {
                int output = 1 + (int) (Math.floor(17 * Math.E * k) % 65);
                if (count[output] < 3) {
                    puzzle[i][j] = output;
                    count[output]++;
                    j++;
                }
                k++;
            }
            i++;
        }
    }

    public static void printPuzzle() {
        for (int i = 0; i < 65; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.printf("%3d ", puzzle[i][j]);
            }
            System.out.println();
        }
    }

    public static boolean isValid(int sliceIndex) {
        for (int i = 0; i < 3; i++) {
            int color = puzzle[sliceIndex][i];
            if (threadCounts[color][i] != 0) {
                return false;
            }
        }
        return true;
    }

    public static void addSlice(int sliceIndex) {
        for (int i = 0; i < 3; i++) {
            int color = puzzle[sliceIndex][i];
            threadCounts[color][i] = 1;
        }
    }

    public static void removeSlice(int sliceIndex) {
        for (int i = 0; i < 3; i++) {
            int color = puzzle[sliceIndex][i];
            threadCounts[color][i] = 0;
        }
    }

    public static boolean rotateSlices(int sliceIndex) {
        int temp;
        for (int a = 0; a < 2; a++) {
            temp = puzzle[sliceIndex][0];

            threadCounts[puzzle[sliceIndex][0]][0] = 0;
            puzzle[sliceIndex][0] = puzzle[sliceIndex][1];

            threadCounts[puzzle[sliceIndex][1]][1] = 0;
            puzzle[sliceIndex][1] = puzzle[sliceIndex][2];

            threadCounts[puzzle[sliceIndex][2]][2] = 0;
            puzzle[sliceIndex][2] = temp;

            if (isValid(sliceIndex)) {
                threadCounts[puzzle[sliceIndex][0]][0] = 1;
                threadCounts[puzzle[sliceIndex][1]][1] = 1;
                threadCounts[puzzle[sliceIndex][2]][2] = 1;
                return true;
            }
        }
        return false;
    }

    public static boolean findSolution(int sliceIndex) {
        if (sliceIndex == 65) {
            return true;
        }

        if (isValid(sliceIndex)) {
            addSlice(sliceIndex);
            System.out.printf("%d %d %d\n", puzzle[sliceIndex][0], puzzle[sliceIndex][1], puzzle[sliceIndex][2]);

            if (findSolution(sliceIndex + 1)) {
                return true;
            }

            removeSlice(sliceIndex);
        }

        if (rotateSlices(sliceIndex)) {
            System.out.printf("%d %d %d, this was rotated succesfully\n", puzzle[sliceIndex][0],
                    puzzle[sliceIndex][1], puzzle[sliceIndex][2]);

            if (findSolution(sliceIndex + 1)) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        generatePuzzle();

        // print the generated puzzle
        printPuzzle();

        System.out.println("Solution:");
        if (!findSolution(0)) {
            System.out.println("No solution found.");
        }
    }
}
