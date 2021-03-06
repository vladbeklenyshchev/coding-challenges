import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {
    // Fast solution based on BinarySearch for the 2D array
    private static boolean isObstacleFound(int i, int j, int[][] obstacles) {
        int lo = 0; int hi = obstacles.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if      (i < obstacles[mid][0]) hi = mid - 1;
            else if (i > obstacles[mid][0]) lo = mid + 1;
            else {
                if        (j > obstacles[mid][1]) {
                    for (int k = mid; k < obstacles.length && obstacles[k][0] == i 
                    && obstacles[k][1] <= j; k++)        
                        if (j == obstacles[k][1]) return true;
                    return false;
                } else if (j < obstacles[mid][1]) {
                    for (int k = mid; k >= 0 && obstacles[k][0] == i 
                    && obstacles[k][1] >= j ; --k)
                        if (j == obstacles[k][1]) return true;
                    return false;
                } else                            return true;
            }
        }

        return false;
    }

    // Complete the queensAttack function below.
    static int queensAttack(int n, int k, int r_q, int c_q, int[][] obstacles) {
        if (n <= 0 || n > Math.pow(10, 5) 
            || k < 0 || k > Math.pow(10, 5))
            throw new IllegalArgumentException();
        for (int i = 0; i < obstacles.length; i++) {
            if (r_q == obstacles[i][0] && c_q == obstacles[i][1])
                throw new IllegalArgumentException();
        }

        Arrays.sort(obstacles, new java.util.Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                if      (Integer.compare(a[0], b[0]) < 0) return -1;
                else if (Integer.compare(a[0], b[0]) > 0) return +1;
                else return Integer.compare(a[1], b[1]);
            }
        });

        for (int i = 0; i < k; i++) {
            System.out.println(obstacles[i][0] + " " + obstacles[i][1]);
        }

        int nOfSquaresQueenCanAttack = 0;
        // Top left corner.
        /*
            a x x
            x Q x
            x x x
        */
        int i = r_q + 1;
        int j = c_q - 1;
        // Indexing starts at 1.
        
        for (; j >= 1 && i <= n; i++, --j) {
            if (!isObstacleFound(i, j, obstacles))
                nOfSquaresQueenCanAttack++;
            else break;
        }
        
         
        
        // Left middle corner.
        /*
            x x x
            a Q x
            x x x
        */
        i = r_q;
        j = c_q - 1;
        for (; j >= 1; --j) {
            if (!isObstacleFound(i, j, obstacles))
                nOfSquaresQueenCanAttack++;
            else break;
        }

        
        // Bottom left corner.
        /*
            x x x
            x Q x
            a x x
        */
        i = r_q - 1;
        j = c_q - 1;
        for (; j >= 1 && i >= 1; --i, --j) {
            if (!isObstacleFound(i, j, obstacles))
                nOfSquaresQueenCanAttack++;
            else break;
        }

        
        // Bottom middle corner.
        /*
            x x x
            x Q x
            x a x
        */
        i = r_q - 1;
        j = c_q;
        for (; i >= 1; --i) {
            if (!isObstacleFound(i, j, obstacles))
                nOfSquaresQueenCanAttack++;
            else break;
        }

        // Bottom right corner.
        /*
            x x x
            x Q x
            x x a
        */
        i = r_q - 1;
        j = c_q + 1;
        for (; i >= 1 && j <= n; --i, j++) {
            if (!isObstacleFound(i, j, obstacles))
                nOfSquaresQueenCanAttack++;
            else break;
        }

        // Right middle corner.
        /*
            x x x
            x Q a
            x x x
        */
        i = r_q;
        j = c_q + 1;
        for (; j <= n; j++) {
            if (!isObstacleFound(i, j, obstacles))
                nOfSquaresQueenCanAttack++;
            else break;
        }

        // Top right corner.
        /*
            x x a
            x Q x
            x x x
        */
        i = r_q + 1;
        j = c_q + 1;
        for (; i <= n && j <= n; i++, j++) {
            if (!isObstacleFound(i, j, obstacles))
                nOfSquaresQueenCanAttack++;
            else break;
        }

        // Top middle corner.
        /*
            x a x
            x Q x
            x x x
        */
        i = r_q + 1;
        j = c_q;
        for (; i <= n; i++) {
            if (!isObstacleFound(i, j, obstacles))
                nOfSquaresQueenCanAttack++;
            else break;
        }

        return nOfSquaresQueenCanAttack;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nk = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nk[0]);

        int k = Integer.parseInt(nk[1]);

        String[] r_qC_q = scanner.nextLine().split(" ");

        int r_q = Integer.parseInt(r_qC_q[0]);

        int c_q = Integer.parseInt(r_qC_q[1]);

        int[][] obstacles = new int[k][2];

        for (int i = 0; i < k; i++) {
            String[] obstaclesRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 2; j++) {
                int obstaclesItem = Integer.parseInt(obstaclesRowItems[j]);
                obstacles[i][j] = obstaclesItem;
            }
        }

        int result = queensAttack(n, k, r_q, c_q, obstacles);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
