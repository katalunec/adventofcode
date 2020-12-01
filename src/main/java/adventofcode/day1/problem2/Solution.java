package adventofcode.day1.problem2;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Solution {
   public static void main(String[] args) throws Exception {
      Path path = Paths.get("src/main/java/adventofcode/day1/problem2/input.txt");

      long target = 2020;

      List<Long> numbers = Files.lines(path).map(Long::valueOf)
            .collect(Collectors.toList());

      for (long number : numbers) {
         long mul = findMulOfNumbers(numbers, target - number);

         if (mul != -1) {
            System.out.println(number * mul);
            break;
         }
      }
   }

   private static long findMulOfNumbers(List<Long> numbers, long target) {
      Map<Long, Long> additions = new HashMap<>();

      for (long number : numbers) {
         if (additions.containsKey(number)) {
            long addition = additions.get(number);
            return addition * number;
         } else {
            long addition = target - number;
            additions.put(addition, number);
         }
      }

      return -1;
   }
}
