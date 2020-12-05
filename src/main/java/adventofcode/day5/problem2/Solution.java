package adventofcode.day5.problem2;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Solution {

   private static final int ROWS = 128;
   private static final int COLS = 8;

   public static void main(String[] args) throws Exception {
      Path path = Paths
            .get("src/main/java/adventofcode/day5/problem1/input.txt");

      List<Integer> seatIds = Files.lines(path)
            .map(String::toCharArray)
            .map(Solution::findSeatId)
            .sorted()
            .collect(Collectors.toList());

      int expected = seatIds.get(0);

      for (int seatId : seatIds) {
         if (seatId != expected) {
            System.out.println(expected);
            expected++;
         }

         expected++;
      }
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
