import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the countingValleys function below.
    static int countingValleys(int n, String s) {
        if (n < 2 || n > Math.pow(10, 6))
            throw new IllegalArgumentException();
        
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != 'U' && s.charAt(i) != 'D')
                throw new IllegalArgumentException();
        }

        int numberOfValleys = 0;
        int level = 0;
        boolean isHikerAtValley = false;
        for (int i = 0; i < s.length(); i++) {
            
            if (s.charAt(i) == 'U') level++;
            else                    --level;
            
            if      (level < 0 && !isHikerAtValley) {
                numberOfValleys++;
                isHikerAtValley = true;
            } 
            else if (level == 0)
                isHikerAtValley = false;

        }

        return numberOfValleys;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        String s = scanner.nextLine();

        int result = countingValleys(n, s);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
