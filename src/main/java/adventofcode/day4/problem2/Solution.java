package adventofcode.day4.problem2;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Solution {

   private static final Pattern BYR_PATTERN = Pattern.compile("^(19[2-9][0-9]|200[0-2])$");
   private static final Pattern IYR_PATTERN = Pattern.compile("^(201[0-9]|2020)$");
   private static final Pattern EYR_PATTERN = Pattern.compile("^(202[0-9]|2030)$");
   private static final Pattern HGT_PATTERN = Pattern.compile("^(1[5-8][0-9]cm|19[0-3]cm|59in|6[0-9]in|7[0-6]in)$");
   private static final Pattern HCL_PATTERN = Pattern.compile("^(#([a-f]|\\d){6})$");
   private static final Pattern ECL_PATTERN = Pattern.compile("^(amb|blu|brn|gry|grn|hzl|oth)$");
   private static final Pattern PID_PATTERN = Pattern.compile("^(\\d{9})$");

   private static final int BYR_MASK = 1;
   private static final int IYR_MASK = 1 << 1;
   private static final int EYR_MASK = 1 << 2;
   private static final int HGT_MASK = 1 << 3;
   private static final int HCL_MASK = 1 << 4;
   private static final int ECL_MASK = 1 << 5;
   private static final int PID_MASK = 1 << 6;
   private static final int CID_MASK = 1 << 7;

   private static final int REQUIRED_MASK = BYR_MASK | IYR_MASK | EYR_MASK | HGT_MASK | HCL_MASK | ECL_MASK | PID_MASK;

   public static void main(String[] args) throws Exception {
      Path path = Paths
            .get("src/main/java/adventofcode/day4/problem2/input.txt");

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

         Collections.addAll(cur, fields);
      }

      if (!cur.isEmpty()) {
         valid += isValid(cur, requiredMask) ? 1 : 0;
      }

      return valid;
   }

   private static boolean isValid(List<String> fields, int requiredMask) {

      int result = 0;

      for (String field : fields) {
         String[] parts = field.split(":");

         String key = parts[0];
         String value = parts[1];

         switch (key) {
         case "byr":
            if (BYR_PATTERN.matcher(value).matches()) {
               // 1920 - 2002
               result |= BYR_MASK;
            }
            break;
         case "iyr":
            if (IYR_PATTERN.matcher(value).matches()) {
               // 2010 - 2020
               result |= IYR_MASK;
            }
            break;
         case "eyr":
            if (EYR_PATTERN.matcher(value).matches()) {
               // 2020 - 2030
               result |= EYR_MASK;
            }
            break;
         case "hgt":
            if (HGT_PATTERN.matcher(value).matches()) {
               // 150 - 193 cm | 59 - 76 in
               result |= HGT_MASK;
            }
            break;
         case "hcl":
            if (HCL_PATTERN.matcher(value).matches()) {
               // # followed by 6 [0-9] or [a-f]
               result |= HCL_MASK;
            }
            break;
         case "ecl":
            if (ECL_PATTERN.matcher(value).matches()) {
               // amb blu brn gry grn hzl oth
               result |= ECL_MASK;
            }
            break;
         case "pid":
            if (PID_PATTERN.matcher(value).matches()) {
               // 9 digits
               result |= PID_MASK;
            }
            break;
         case "cid":
            result |= CID_MASK;
            break;
         }
      }

      return (result & requiredMask) == requiredMask;
   }
}
