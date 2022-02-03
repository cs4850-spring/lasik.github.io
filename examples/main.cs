// Kyle Buie & Dylan Wright
// C# Test Program #1
// Paradigms: Variable Declaration & Assignment, If/Else Construct, For Loop, Comments

//using System;

class FirstTestProgram {
   public static void Main (string[] args) {
      string V = "Value = ";
      int N = 0;
      for (int i = 0; i < 5; i++) {
         if (i == 4) {
            N += 6;
         }
         else {
            N++;
         }
      }
      V += N;
      //Console.WriteLine(V);
   }
}