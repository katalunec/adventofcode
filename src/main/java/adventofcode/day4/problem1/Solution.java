package adventofcode.day4.problem1;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Solution {
   private static final int BYR_MASK = 1;
   private static final int IYR_MASK = 1 << 1;
   private static final int EYR_MASK = 1 << 2;
   private static final int HGT_MASK = 1 << 3;
   private static final int HCL_MASK = 1 << 4;
   private static final int ECL_MASK = 1 << 5;
   private static final int PID_MASK = 1 << 6;
   private static final int CID_MASK = 1 << 7;

   private static final int REQUIRED_MASK =
         BYR_MASK | IYR_MASK | EYR_MASK | HGT_MASK | HCL_MASK | ECL_MASK
               | PID_MASK;

   public static void main(String[] args) throws Exception {
      Path path = Paths
            .get("src/main/java/adventofcode/day4/problem1/input.txt");

      List<String> lines = Files.lines(path).collect(Collectors.toList());

      System.out.println(validPassports(lines, REQUIRED_MASK));
   }

   private static int validPassports(List<String> lines, int requiredMask) {
      List<String> cur = new ArrayList<>();

      int valid = 0;

      for (String line : lines) {
         if (line.isEmpty()) {
            valid += isValid(cur, requiredMask) ? 1 : 0;
            cur = new ArrayList<>();
            continue;
         }

         String[] fields = line.split(" ");

         for (String field : fields) {
            String[] parts = field.split(":");

            String key = parts[0];
            cur.add(key);
         }
      }

      if (!cur.isEmpty()) {
         valid += isValid(cur, requiredMask) ? 1 : 0;
      }

      return valid;
   }

   private static boolean isValid(List<String> fields, int requiredMask) {

      int result = 0;

      for (String field : fields) {
         switch (field) {
         case "byr":
            result |= BYR_MASK;
            break;
         case "iyr":
            result |= IYR_MASK;
            break;
         case "eyr":
            result |= EYR_MASK;
            break;
         case "hgt":
            result |= HGT_MASK;
            break;
         case "hcl":
            result |= HCL_MASK;
            break;
         case "ecl":
            result |= ECL_MASK;
            break;
         case "pid":
            result |= PID_MASK;
            break;
         case "cid":
            result |= CID_MASK;
            break;
         }
      }

      return (result & requiredMask) == requiredMask;
   }
}
