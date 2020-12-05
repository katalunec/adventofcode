package adventofcode.day5.problem1;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

public class Solution {

   private static final int ROWS = 128;
   private static final int COLS = 8;

   public static void main(String[] args) throws Exception {
      Path path = Paths
            .get("src/main/java/adventofcode/day5/problem1/input.txt");

      Optional<Integer> max = Files.lines(path)
            .map(String::toCharArray)
            .map(Solution::findSeatId)
            .max(Integer::compare);

      System.out.println(max.orElse(0));
   }

   private static int findSeatId(char[] directions) {
      int firstRow = 0, lastRow = ROWS - 1;

      int curRow = 0;

      int i = 0;

      for (; i < 7; i++) {
         char direction = directions[i];

         curRow = (firstRow + lastRow) / 2;

         if (direction == 'F') {
            lastRow = curRow;
         } else {
            // B
            firstRow = curRow + 1;
         }

         curRow = (firstRow + lastRow) / 2;
      }

      int firstCol = 0, lastCol = COLS - 1;
      int curCol = 0;

      for (; i < 10; i++) {
         char direction = directions[i];

         curCol = (firstCol + lastCol) / 2;

         if (direction == 'L') {
            lastCol = curCol;
         } else {
            // R
            firstCol = curCol + 1;
         }

         curCol = (firstCol + lastCol) / 2;
      }

      return (8 * curRow) + curCol;
   }
}
