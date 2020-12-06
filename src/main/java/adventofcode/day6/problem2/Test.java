package adventofcode.day6.problem2;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Test {
   public static void main(String[] args) {
      Set<Character> s1 = new HashSet<>(Arrays.asList('a'));

      Set<Character> s2 = new HashSet<>();

      System.out.println(s1);

      s1.retainAll(s2);

      System.out.println(s1);
   }
}
