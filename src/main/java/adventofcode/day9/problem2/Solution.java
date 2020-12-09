package adventofcode.day9.problem2;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class Solution {

   private static int PREAMBLE = 25;

   public static void main(String[] args) throws Exception {
      Path path = Paths
            .get("src/main/java/adventofcode/day9/problem1/input.txt");

      List<Long> numbers = Files.lines(path).map(Long::parseLong)
            .collect(Collectors.toList());

      long sum = -1;

      for (int i = 0; i < numbers.size(); i++) {
         if (i < PREAMBLE) {
            continue;
         }

         if (!isValid(numbers, i)) {
            sum = numbers.get(i);
            break;
         }
      }

      List<Long> contiguous = findContiguousNumbers(numbers, sum);

      long max = contiguous.stream()
            .max(Comparator.comparingLong(Long::longValue))
            .orElseThrow(NoSuchElementException::new);

      long min = contiguous.stream()
            .min(Comparator.comparingLong(Long::longValue))
            .orElseThrow(NoSuchElementException::new);

      System.out.println(min + max);
   }

   private static List<Long> findContiguousNumbers(List<Long> numbers,
         long sum) {
      for (int i = 0; i < numbers.size(); i++) {
         long curSum = numbers.get(i);

         for (int j = i + 1; j < numbers.size(); j++) {
            if (curSum == sum) {
               return numbers.subList(i, j);
            }

            if (curSum > sum) {
               break;
            }

            curSum += numbers.get(j);
         }

      }

      return Collections.emptyList();
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
