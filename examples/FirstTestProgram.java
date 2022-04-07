// Kyle Buie & Dylan Wright
// Java Test Program #1
// Paradigms: Variable Declaration & Assignment, If/Else Construct, For Loop, Comments

public class firstTestProgram {
   public static void main(String[] args) {
      String v = "Value = ";
      int n = 0;
      for (int i = 0; i < 5; i++) {
         if (i == 4) {
            n += 6;
         }
         else {
            n++;
         }
      }
      v += n;
      System.out.println(v);
   }
}
