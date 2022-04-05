// Kyle Buie & Dylan Wright
// Java Test Program #2
// Paradigms: 2-D Arrays, Type Casting (Integers and Doubles), String Manipulation

public class secondTestProgram {
    public static void main(String[] args) {
        String s1 = "AbcdEfghIjklmnOpqrstUvwxyz";
        String s2 = "aeiou";
        twoDimArray();
        System.out.println();
        strManipulation(s1, s2);
    }

    public static void twoDimArray() {
        // Initialize m: [{1.0, -2.0, 3.0, -4.0},{2.0, -4.0, 6.0, -8.0},{3.0, -6.0, 9.0, -12.0}]
        double[][] m = new double[3][4];
        for (int r = 0; r < m.length; r++) {
            for (int c = 0; c < m[0].length; c++) {
                m[r][c] = (r + 1.0) * (c + 1.0);
                if (c % 2 == 1) {
                    m[r][c] *= -1;
                }
            }
        }

        // Perform arithmetic on array elements
        double q1 = m[2][2] / m[1][1];
        System.out.println("Double division of 9.0 / -4.0 = " + q1);
        int q1_trnc = (int) q1;
        System.out.println("Truncated/Typecast value of q1 = " + q1_trnc);
        int q2 = (int) (m[2][2] / m[1][1]);
        System.out.println("Integer division of 9.0 / -4.0 = " + q2);
        int e = (int) m[0][2];
        int mod_result = (int) m[1][3] % e;
        System.out.println("-8 modulo 3 = " + mod_result);
    }

    public static void strManipulation(String str1, String str2) {
        /*
        String s = "";

        // Construct a 26-character string containing the letters of the alphabet in order by concatenating substrings of str1 and str2
        // First substring parameter is inclusive; second parameter is exclusive
        for (int i = 0; i < 26; i++) {
            switch(i) {
                case 0:
                    s += str2.substring(0, 1);
                    break;
                case 4:
                    s += str2.substring(1, 2);
                    break;
                case 8:
                    s += str2.substring(2, 3);
                    break;
                case 14:
                    s += str2.substring(3, 4);
                    break;
                case 20:
                    s += str2.substring(4, 5);
                    break;
                default:
                    s += str1.substring(i, i+1);
                    break;
            }
        }
        System.out.println(s);

        // Compare "AbcdE" to "abcde"
        int len = s.indexOf("f");
        String new_s = str1.substring(0, len);
        int c  = new_s.compareTo(str1);
        if (c == 0) {
            System.out.println("new_s and str1 are the same");
        }
        else {

            if (c < 0) {
                System.out.println("new_s is less than str1");
            }
            else {
                System.out.println("new_s is greater than str1");
            }
        }
        */

        //String class method testing
        System.out.println();
        String strc = "Hello";
        System.out.println("String to be tested: " + strc);
        System.out.println("Character at index 1 in str: " + strc.charAt(1)); //charAt
        char[] strdata = {' ', 'W', 'o', 'r', 'l', 'd', '!'}; //array creation for copyValueOf
        String strctmp = String.copyValueOf(strdata);//copyValueOf
        String strc2 = strc.concat(strctmp); //concat
        System.out.println("String after concat of ' World!': " + strc2); //concat testing
        strctmp = "hello world!"; //string creation for further function testing
        System.out.println("Comparison of string with 'hello world!': " + strc2.compareTo(strctmp)); //compareTo
        System.out.println("Comparison of string with 'hello world!' ignores case: " + strc2.compareToIgnoreCase(strctmp)); //compareToIgnoreCase
        System.out.println("Check if string contains 'e': " + strc.contains("e")); //contains
        System.out.println("Check if string ends with 'rld!': " + strc2.endsWith("rld!")); //endsWith
        System.out.println("Check if string is equal to 'hello world!': " + strc2.equals(strctmp)); //equals
        System.out.println("Check if string is equal to 'hello world!' ignores case: " + strc2.equalsIgnoreCase(strctmp)); //equalsIgnoreCase
        String strc2f = String.format("Formatted string: %s", strc2); //format
        System.out.println(strc2f); //format testing
        System.out.println("Hash Code of string: " + strc2.hashCode()); //hashCode
        System.out.println("Index of first occurrence of 'l': " + strc2.indexOf('l')); //indexOf(ch)
        System.out.println("Index of first occurrence of 'l' after index 5: " + strc2.indexOf('l', 5)); //indexOf(ch, index)
        System.out.println("Index of first occurrence of 'rld!': " + strc2.indexOf("rld!")); //indexOf(str)
        System.out.println("Index of first occurrence of 'rld!' after index 5: " + strc2.indexOf("rld!", 5)); //indexOf(str, index)
        System.out.println("Canonical representation of string object: " + strc2.intern()); //intern
        System.out.println("Check if str is empty: " + strc2.isEmpty()); //isEmpty
        System.out.println("Index of last occurrence of 'l': " + strc2.lastIndexOf('l')); //lastIndexOf(ch)
        System.out.println("Index of last occurrence of 'l' before index 5: " + strc2.lastIndexOf('l', 5)); //lastIndexOf(ch, index)
        System.out.println("Index of last occurrence of 'll': " + strc2.lastIndexOf("ll")); //lastIndexOf(str)
        System.out.println("Index of last occurrence of 'll' before index 5: " + strc2.lastIndexOf("ll", 5)); //lastIndexOf(str, index)
        System.out.println("Length of string: " + strc2.length()); //length
        String strcr = strc2.replace('l', 'w'); //replace(ch, ch)
        System.out.println("String with 'l' replaced by 'w': " + strcr); //replace testing
        String[] strcsplit = strc2.split(" "); //split
        System.out.print("Elements of array made by splitting string around ' ': "); //split testing
        for(String spl : strcsplit) { //split testing
            System.out.print(spl + ", ");
        }
        System.out.println(); //new line for visual clarity
        System.out.println("Check if string starts with 'Hell': " + strc2.startsWith("Hell")); //startsWith
        System.out.println("Substring of string after index 5: " + strc2.substring(5)); //substring(beginIndex)
        //System.out.println("Substring of string between index 5 and 8: " + strc2.substring(5, 8)); //substring(beginIndex, endIndex)
        char[] strcchrarr = strc2.toCharArray(); //toCharArray
        System.out.print("Elements of char array made from string: "); //toCharArray testing
        for(char chr : strcchrarr) { //toCharArray testing
            System.out.print(chr + ", ");
        }
        System.out.println(); //new line for visual clarity
        System.out.println("String converted to lowercase: " + strc2.toLowerCase()); //toLowerCase
        System.out.println("String converted to uppercase: " + strc2.toUpperCase()); //toUpperCase
        System.out.println("String after trimming: " + strc2.trim()); //trim
    }
}
