package adventofcode.day2.problem2;

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
            "(?<pos1>\\d+)-(?<pos2>\\d+) (?<symbol>[a-z]): (?<password>[a-z]+)");

      int valid = 0;

      List<String> lines = Files.lines(path).collect(Collectors.toList());

      for (String line : lines) {
         Matcher m = pattern.matcher(line);

         if (!m.matches()) {
            throw new IllegalArgumentException("Unexpected pattern.");
         }

         // substract 1 to make them zero based
         int pos1 = Integer.parseInt(m.group("pos1")) - 1;
         int pos2 = Integer.parseInt(m.group("pos2")) - 1;

         char expectedSymbol = m.group("symbol").charAt(0);
         String password = m.group("password");

         char symbol1 = password.charAt(pos1);
         char symbol2 = password.charAt(pos2);

         if (symbol1 == expectedSymbol && symbol2 == expectedSymbol) {
            continue;
         }

         if (symbol1 == expectedSymbol || symbol2 == expectedSymbol) {
            valid++;
         }
      }

      System.out.println("valid: " + valid);
   }
}


