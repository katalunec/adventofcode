package adventofcode.day3.problem2;

import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Solution {
   private static final char TREE = '#';

   public static void main(String[] args) throws Exception {

      BigInteger i1 = new BigInteger(String.valueOf(findTrees(1,1)));
      BigInteger i2 = new BigInteger(String.valueOf(findTrees(3,1)));
      BigInteger i3 = new BigInteger(String.valueOf(findTrees(5,1)));
      BigInteger i4 = new BigInteger(String.valueOf(findTrees(7,1)));
      BigInteger i5 = new BigInteger(String.valueOf(findTrees(1,2)));

      BigInteger mul = i1.multiply(i2).multiply(i3).multiply(i4).multiply(i5);

      System.out.println(mul.toString(10));
   }

   private static int findTrees(int colMove, int rowMove) throws Exception {
      Path path = Paths
            .get("src/main/java/adventofcode/day3/problem2/input.txt");

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
         curCol = (colMove + curCol) % colLength;
         curRow += rowMove;

         if (curRow >= rowLength) {
            break;
         }

         char c = table[curRow][curCol];

         if (c == TREE) {
            trees++;
         }
      }

      return trees;
   }
}
