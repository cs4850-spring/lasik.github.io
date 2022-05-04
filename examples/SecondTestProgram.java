// Java Test Program #2
// Paradigms: Assignment, operators, if statements, Arrays, Multi-D Arrays, For loops, try catch finally, exceptions

public class ExampleProgramTwo {
  
  	// Basic types
 	public boolean bot = true;
    public boolean bof = false;
    public double d = 2.1;
    //public float fl = 4.3f; Currently bugged, will be fixed in polish
    public int integer = 10;
    public long lo = 10L;
    public byte by = 123;
    public short sh = 432;
    public char ch = 'c';
    public String st = "test";
    public String fooNull = null;

  	// Equality operators
    public boolean l = 5 < 6;
    public boolean  g = 5 > 6;
    public boolean le = 5 <= 6;
    public boolean ge = 5 >= 6;
    public boolean e = 5 == 6;
    public boolean ne = 5 != 6;
    public boolean and = true && false;
    public boolean or = true || false;

  	// Arithmetic operators
    public int add = 5 + 6;
    public int sub = 5 - 6;
    public int mult = 5 * 6;
    public int div = 5 / 6;
    public int mod = 5 % 6;

  	// Shift operators
    public int lshift = 5 << 6;
    public int rshift = 5 >> 6;

  	// Bitwise operators
    public int xor = 5 ^ 6;
    public int bor = 5 | 6;
    public int band = 5 & 6;


 

    public static void main(String[] args) {
      	compoundAssignments();

        // We can assign returned values 
        String[] words = createWords();

        // We can pass values as parameters
        forLoops(words);

        multiDimensionalArrays();

        // We can do a try-catch-finally block
        try {
            throwException();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            System.out.println("Finally done!");
        }
    }
  
  	public static void compoundAssignments() {
       int baz = 5;
      
       // Compound Assignments
       baz += 10; 
       baz -= 10; 
       baz *= 10; 
       baz /= 10; 
       baz %= 10; 
       baz >>= 10; 
       baz <<= 10; 
       baz ^= 10; 

       baz |= 10; 
       baz &= 10; 
      
       // Unary Operators
  	   int neg = -2;
       int comp = ~6;
       int lpp = ++neg;
       int lmm = --neg;
       int rpp = neg++;
       int rmm = neg--;
      
        ignore(neg);
        ignore(comp);
        ignore(lpp);
        ignore(lmm);
        ignore(rpp);
        ignore(rmm);

      
       System.out.println("Baz before if: " + baz);
       if (baz >= 5) {
         baz = 8;
       } else if (baz >= 8) {
         baz = 4;
       } else if (baz < 4) {
         baz = 3;
       } else {
         baz = 0;
       } 
      
       System.out.println("Baz after if: " + baz);
    }
  
    private static void ignore(int i) {}
  
  	public static String[] createWords() {
        // We can initialize arrays using the new syntax
        String[] empty = new String[10];

        // Or we can initialize arrays with the literal syntax
        String[] words = { "cs4850", "is", "a", "", "class" };

        // We can assign values to an array index
        words[3] = "great";

        // We can return values from a function
        return words;
    }

    public static void forLoops(String[] words) {

        // We can iterate using a c-style for loop
        for (int i = 0; i < words.length; i++) {
            // We can read the array at an index
            String word = words[i];

            System.out.print(word + " ");
        }

        System.out.println();

        // We can also iterate using a for-each loop
        for(String word : words) {
            System.out.print(word + " ");
        }

        System.out.println();
    }

    public static void multiDimensionalArrays() {
        // Initialize m: 
        // [{1.0, -2.0, 3.0, -4.0},{2.0, -4.0, 6.0, -8.0},{3.0, -6.0, 9.0, -12.0}]
        int rows = 3;
      	int cols = 4;

        // We can initialize a new multi-dimensional array
      	double[][] m = new double[rows][cols];
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                m[row][col] = (row + 1.0) * (col + 1.0);
                if (col % 2 == 1) {
                    m[row][col] *= -1;
                }
            }
        }

        // Perform arithmetic on array elements
        double q1 = m[2][2] / m[1][1];
        System.out.println("Double division of 9.0 / -4.0 = " + q1);

        // We can cast one type to another
        int q1_trnc = (int) q1;
        System.out.println("Truncated/Typecast value of q1 = " + q1_trnc);

        // We can wrap expressions in parentheses
        int q2 = (int) (m[2][2] / m[1][1]);
        System.out.println("Integer division of 9.0 / -4.0 = " + q2);

        int e = (int) m[0][2];
        
        int mod_result = (int) m[1][3] % e;
        System.out.println("-8 modulo 3 = " + mod_result);
    }

    // We can throw exceptions from a function
    public static void throwException() throws Exception {
        throw new Exception("wow! Exceptions are cool!");
    }
}
