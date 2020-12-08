package adventofcode.day8.problem2;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
   public static void main(String[] args) throws Exception {
      Pattern p = Pattern.compile("(?<op>\\w+)\\s(?<sign>.)(?<number>\\d+)");

      Path path = Paths
            .get("src/main/java/adventofcode/day8/problem1/input.txt");

      List<String> ops = new ArrayList<>();
      List<Integer> numbers = new ArrayList<>();

      Files.lines(path).forEach(s -> {
         Matcher m = p.matcher(s);

         if (!m.matches()) {
            throw new RuntimeException("Unexpected parser error.");
         }

         String op = m.group("op");
         Integer sign = m.group("sign").equals("-") ? -1 : 1;
         Integer number = Integer.parseInt(m.group("number"));

         ops.add(op);
         numbers.add(sign * number);
      });

      int acc = findAccFromNonCyclicRoute(ops, numbers);

      System.out.println(acc);
   }

   private static int findAccFromNonCyclicRoute(List<String> ops, List<Integer> numbers) {

      int opIndex = 0;

      while (true) {

         boolean[] visited = new boolean[ops.size()];
         boolean cyclicRoute = false;
         int acc = 0;

         for (int i = 0, curOpIndex = 0; i < ops.size(); ) {
            if (visited[i]) {
               cyclicRoute = true;
               break;
            }
            visited[i] = true;

            String op = ops.get(i);
            Integer number = numbers.get(i);

            switch (op) {
            case "acc":
               acc += number;
               i++;
               break;
            case "jmp":
               if (opIndex == curOpIndex++) {
                  // do nothing
                  i++;
               } else {
                  i += number;
               }
               break;
            case "nop":
               if (opIndex == curOpIndex++) {
                  i += number;
               } else {
                  // do nothing
                  i++;
               }
               break;
            default:
               throw new RuntimeException("Unexpected operation exception.");
            }
         }

         if (!cyclicRoute) {
            return acc;
         }

         opIndex++;
      }
   }
}
