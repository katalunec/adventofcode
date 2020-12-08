package adventofcode.day7.problem1;

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
      Pattern p = Pattern.compile("(?<color>\\w+ \\w+) bag[\\s,.s]?");

      Path path = Paths
            .get("src/main/java/adventofcode/day7/problem1/input.txt");

      List<List<String>> bags = Files.lines(path).map(s -> {
         Matcher m = p.matcher(s);

         List<String> colors = new ArrayList<>();

         while (m.find()) {
            colors.add(m.group("color"));
         }

         return colors;
      }).collect(Collectors.toList());

      Map<String, Set<String>> parentsByChild = new HashMap<>();

      for (List<String> colors : bags) {
         Iterator<String> it = colors.iterator();

         String parent = it.next();

         while (it.hasNext()) {
            String child = it.next();

            Set<String> parents = parentsByChild
                  .computeIfAbsent(child, k -> new HashSet<>());

            parents.add(parent);
         }
      }

      String child = "shiny gold";

      Set<String> parents = new HashSet<>();

      findParents(child, parentsByChild, parents);

      System.out.println(parents.size());
   }

   private static void findParents(String child,
         Map<String, Set<String>> parentsByChild, Set<String> out) {

      Set<String> parents = parentsByChild.get(child);

      if (parents == null) {
         return;
      }

      out.addAll(parents);

      for (String parent : parents) {
         findParents(parent, parentsByChild, out);
      }
   }
}
