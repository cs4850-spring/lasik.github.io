using System;

public class ExampleProgramTwo
{
    public bool Bot = true;
    public bool Bof = false;
    public double D = 2.1;
    public int Integer = 10;
    public long Lo = 10L;
    public byte By = 123;
    public short Sh = 432;
    public char Ch = 'c';
    public String St = "test";
    public String FooNull = null;
    public bool L = 5 < 6;
    public bool G = 5 > 6;
    public bool Le = 5 <= 6;
    public bool Ge = 5 >= 6;
    public bool E = 5 == 6;
    public bool Ne = 5 != 6;
    public bool And = true && false;
    public bool Or = true || false;
    public int Add = 5 + 6;
    public int Sub = 5 - 6;
    public int Mult = 5 * 6;
    public int Div = 5 / 6;
    public int Mod = 5 % 6;
    public int Lshift = 5 << 6;
    public int Rshift = 5 >> 6;
    public int Xor = 5 ^ 6;
    public int Bor = 5 | 6;
    public int Band = 5 & 6;
    public static void Main(String[] args)
    {
        CompoundAssignments();
        String[] words = CreateWords();
        ForLoops(words);
        MultiDimensionalArrays();
        try
        {
            ThrowException();
        }
        catch (Exception e)
        {
            Console.WriteLine(e);
        }
        finally
        {
            Console.WriteLine("Finally done!");
        }
    }

    public static void CompoundAssignments()
    {
        int baz = 5;
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
        int neg = -2;
        int comp = ~6;
        int lpp = ++neg;
        int lmm = --neg;
        int rpp = neg++;
        int rmm = neg--;
        Ignore(neg);
        Ignore(comp);
        Ignore(lpp);
        Ignore(lmm);
        Ignore(rpp);
        Ignore(rmm);
        Console.WriteLine("Baz before if: " + baz);
        if (baz >= 5)
        {
            baz = 8;
        }
        else if (baz >= 8)
        {
            baz = 4;
        }
        else if (baz < 4)
        {
            baz = 3;
        }
        else
        {
            baz = 0;
        }

        Console.WriteLine("Baz after if: " + baz);
    }

    private static void Ignore(int i)
    {
    }

    public static String[] CreateWords()
    {
        String[] empty = new String[10];
        String[] words = {"cs4850", "is", "a", "", "class"};
        words[3] = "great";
        return words;
    }

    public static void ForLoops(String[] words)
    {
        for (int i = 0; i < words.Length; i++)
        {
            String word = words[i];
            Console.Write(word + " ");
        }

        Console.WriteLine();
        foreach (String word in words)
        {
            Console.Write(word + " ");
        }

        Console.WriteLine();
    }

    public static void MultiDimensionalArrays()
    {
        int rows = 3;
        int cols = 4;
        double[, ] m = new double[rows, cols];
        for (int row = 0; row < rows; row++)
        {
            for (int col = 0; col < cols; col++)
            {
                m[row, col] = (row + 1) * (col + 1);
                if (col % 2 == 1)
                {
                    m[row, col] *= -1;
                }
            }
        }

        double q1 = m[2, 2] / m[1, 1];
        Console.WriteLine("Double division of 9.0 / -4.0 = " + q1);
        int q1_trnc = (int)(q1);
        Console.WriteLine("Truncated/Typecast value of q1 = " + q1_trnc);
        int q2 = (int)((m[2, 2] / m[1, 1]));
        Console.WriteLine("Integer division of 9.0 / -4.0 = " + q2);
        int e = (int)(m[0, 2]);
        int mod_result = (int)(m[1, 3]) % e;
        Console.WriteLine("-8 modulo 3 = " + mod_result);
    }

    public static void ThrowException()
    {
        throw new Exception("wow! Exceptions are cool!");
    }
}
