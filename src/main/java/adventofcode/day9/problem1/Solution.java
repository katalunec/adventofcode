package adventofcode.day9.problem1;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Solution {

   private static int PREAMBLE = 25;

   public static void main(String[] args) throws Exception {
      Path path = Paths
            .get("src/main/java/adventofcode/day9/problem1/input.txt");

      List<Long> numbers = Files.lines(path).map(Long::parseLong)
            .collect(Collectors.toList());

      for (int i = 0; i < numbers.size(); i++) {
         if (i < PREAMBLE) {
            continue;
         }

         if (!isValid(numbers, i)) {
            long number = numbers.get(i);
            System.out.println("number: " + number + ", index: " + i);
            break;
         }
      }
   }

   private static boolean isValid(List<Long> numbers, int index) {
      long sum = numbers.get(index);

      for (int j = index - PREAMBLE; j < index; j++) {
         for (int k = j + 1; k < index; k++) {
            long n1 = numbers.get(j);
            long n2 = numbers.get(k);
            if (n1 + n2 == sum && n1 != n2) {
               return true;
            }
         }
      }

      return false;
   }
}
