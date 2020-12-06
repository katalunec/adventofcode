package adventofcode.day6.problem1;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Solution {

   public static void main(String[] args) throws Exception {
      Path path = Paths
            .get("src/main/java/adventofcode/day6/problem1/input.txt");

      List<char[]> groups = Files.lines(path)
            .map(String::toCharArray)
            .collect(Collectors.toList());

      Set<Character> set = new HashSet<>();

      int total = 0;

      for (char[] answers : groups) {
         if (answers.length == 0) {
            total += set.size();
            set = new HashSet<>();
            continue;
         }

         for (char answer : answers) {
            set.add(answer);
         }
      }

      total += set.size();

      System.out.println(total);
   }
}
