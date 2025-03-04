
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 1) Read grid dimensions
        int N = sc.nextInt(); // number of rows
        int M = sc.nextInt(); // number of columns
        sc.nextLine(); // consume leftover newline

        // 2) Read the grid
        char[][] grid = new char[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                grid[i][j] = sc.next().charAt(0);
            }
        }

        // 3) Build row strings
        String[] rowStrings = new String[N];
        for (int i = 0; i < N; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < M; j++) {
                sb.append(grid[i][j]);
            }
            rowStrings[i] = sb.toString();
        }

        // 4) Build column strings
        String[] colStrings = new String[M];
        for (int j = 0; j < M; j++) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < N; i++) {
                sb.append(grid[i][j]);
            }
            colStrings[j] = sb.toString();
        }

        // 5) Read the number of words
        int T = sc.nextInt();
        // We'll store the results and print later
        StringBuilder result = new StringBuilder();

        // 6) Check each word in rows or columns
        for (int w = 0; w < T; w++) {
            String word = sc.next();

            boolean found = false;

            // Check rows
            for (int i = 0; i < N; i++) {
                if (rowStrings[i].contains(word)) {
                    found = true;
                    break;
                }
            }

            // If not found in rows, check columns
            if (!found) {
                for (int j = 0; j < M; j++) {
                    if (colStrings[j].contains(word)) {
                        found = true;
                        break;
                    }
                }
            }

            // Append result
            if (found) {
                result.append("Yes");
            } else {
                result.append("No");
            }
            if (w < T - 1) {
                result.append(" ");
            }
        }

        // 7) Print the results
        System.out.println(result.toString());

        sc.close();
    }
}





problem 2 import java.util.*;

public class Solution {
    public static void funcSubstring(String inputStr) {
        int len = inputStr.length();
        String bestPalindrome = "";
        
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j <= len; j++) {
                String sub = inputStr.substring(i, j);
                if (isPalindrome(sub)) {
                    if (sub.length() > bestPalindrome.length() || 
                       (sub.length() == bestPalindrome.length() && sub.compareTo(bestPalindrome) < 0)) {
                        bestPalindrome = sub;
                    }
                }
            }
        }
        
        System.out.println(bestPalindrome.isEmpty() ? "None" : bestPalindrome);
    }

    private static boolean isPalindrome(String s) {
        return s.equals(new StringBuilder(s).reverse().toString());
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String inputStr = in.next();
        funcSubstring(inputStr);
    }
}
