package adventofcode.day4.problem2;

import java.util.regex.Pattern;

public class Test {

   public static void main(String[] args) {
      String t = "337605855";

      Pattern p = Pattern.compile("^(([a-f]|\\d){6})$");

      System.out.println(p.matcher(t).matches());
   }
}
