package adventofcode.day2.problem1;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Solution {
   public static void main(String[] args) throws Exception {
      Path path = Paths
            .get("src/main/java/adventofcode/day2/problem1/input.txt");

      Pattern pattern = Pattern.compile(
            "(?<min>\\d+)-(?<max>\\d+) (?<symbol>[a-z]): (?<password>[a-z]+)");

      int valid = 0;

      List<String> lines = Files.lines(path).collect(Collectors.toList());

      for (String line : lines) {
         Matcher m = pattern.matcher(line);

         if (!m.matches()) {
            throw new IllegalArgumentException("Unexpected pattern.");
         }

         int min = Integer.parseInt(m.group("min"));
         int max = Integer.parseInt(m.group("max"));
         char symbol = m.group("symbol").charAt(0);
         String password = m.group("password");

         int occurances = occurances(password, symbol);

         if (occurances >= min && occurances <= max) {
            valid++;
         }
      }

      System.out.println("valid: " + valid);
   }

   private static int occurances(String s, char c) {
      int occurances = 0;

      for (int i = 0; i < s.length(); i++) {
         char cur = s.charAt(i);

         if (cur == c) {
            occurances++;
         }
      }

      return occurances;
   }
}


