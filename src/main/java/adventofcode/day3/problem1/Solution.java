package adventofcode.day3.problem1;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Solution {

   private static final int ROW_MOVE = 1;
   private static final int COL_MOVE = 3;
   private static final char TREE = '#';

   public static void main(String[] args) throws Exception {
      Path path = Paths
            .get("src/main/java/adventofcode/day3/problem1/input.txt");

      char[][] table = Files.lines(path).map(s -> {
         char[] cols = new char[s.length()];

         for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            cols[i] = c;
         }

         return cols;
      }).toArray(char[][]::new);

      int rowLength = table.length;
      int colLength = table[0].length;

      int curRow = 0, curCol = 0, trees = 0;

      while (true) {
         curCol = (COL_MOVE + curCol) % colLength;
         curRow += ROW_MOVE;

         if (curRow >= rowLength) {
            break;
         }

         char c = table[curRow][curCol];

         if (c == TREE) {
            trees++;
         }
      }

      System.out.println(trees);
   }
}
