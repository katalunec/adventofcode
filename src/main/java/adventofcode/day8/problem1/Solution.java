package adventofcode.day8.problem1;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.UnexpectedException;
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

      int acc = 0;

      boolean[] visited = new boolean[ops.size()];

      for (int i = 0; i < ops.size();) {
         if (visited[i]) {
            break;
         }
         visited[i] = true;

         String op = ops.get(i);
         Integer number = numbers.get(i);

         switch (op) {
         case "acc":
            acc+= number;
            i++;
            break;
         case "jmp":
            i += number;
            break;
         case "nop":
            // do nothing
            i++;
            break;
         default:
            throw new RuntimeException("Unexpected operation exception.");
         }
      }

      System.out.println(acc);
   }
}
