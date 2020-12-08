package adventofcode.day7.problem2;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Solution {

   public static void main(String[] args) throws Exception {
      Pattern p = Pattern
            .compile("(?<count>\\d+)?\\s?(?<color>\\w+ \\w+) bag[\\s,.s]?");

      Path path = Paths
            .get("src/main/java/adventofcode/day7/problem2/input.txt");

      Map<String, List<ChildBag>> childsByParent = new HashMap<>();

      Files.lines(path).forEach(s -> {
         Matcher m = p.matcher(s);
         List<ChildBag> childBags = new ArrayList<>();

         if (m.find()) {
            String color = m.group("color");
            childsByParent.put(color, childBags);
         }

         while (m.find()) {
            String color = m.group("color");
            String count = m.group("count");

            if (count == null) {
               // no other bags
               continue;
            }

            childBags.add(new ChildBag(color, Integer.parseInt(count)));
         }
      });

      String target = "shiny gold";

      int bags = findChildBags(target, childsByParent);

      System.out.println(bags - 1);
   }

   private static int findChildBags(String parent,
         Map<String, List<ChildBag>> childsByParent) {

      List<ChildBag> childBags = childsByParent.get(parent);

      if (childBags.isEmpty()) {
         return 1;
      }

      int bags = 0;

      for (ChildBag childBag : childBags) {
         bags += childBag.count * findChildBags(childBag.name, childsByParent);
      }

      return bags + 1;
   }

   private static final class ChildBag {
      final String name;
      final int count;

      ChildBag(String name, int count) {
         this.name = name;
         this.count = count;
      }
   }
}