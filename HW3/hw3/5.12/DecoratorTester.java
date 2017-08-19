
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * DecoratorTester borrowed from Horstmann
 * @author Paul Diaz
 * CS 151
 * Prof. Kim
 */
public class DecoratorTester {
    public static void main(String[]args) throws IOException{

        SimpleWriter e1 = new SimpleWriter(new FileWriter("test1.out"));
        String str1a = "abcdefghijklmnopqrstuvwxyz1abcdefghijklmnopqrstuvwxyz";
        e1.write(str1a.toCharArray(), 20, 27);
        String str1b = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        e1.write(str1b.toCharArray(), 20, 27);

        PrintWriter w1 = new PrintWriter(e1, true);  // autoflush mode
        w1.println("abcdefghijkl2mnopqrstuvwxyz");
        w1.println("ABCDEFGHIJKL2MNOPQRSTUVWXYZ");
        
        EncryptingWriter e2 = new EncryptingWriter(new FileWriter("test2.out"));
        String str2a = "abcdefghijklmnopqrstuvwxyz1abcdefghijklmnopqrstuvwxyz";
        e2.write(str2a.toCharArray(), 20, 27);
        String str2b = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        e2.write(str2b.toCharArray(), 20, 27);

        PrintWriter w2 = new PrintWriter(e2, true);  // autoflush mode
        w2.println("abcdefghijkl2mnopqrstuvwxyz");
        w2.println("ABCDEFGHIJKL2MNOPQRSTUVWXYZ");
        
        char inChars1[] = 
           "----------------------------------------------".toCharArray();
       
        DecryptingReader r1 = new DecryptingReader(new FileReader("test1.out"));
        r1.read(inChars1, 10, 27);
        System.out.println(inChars1);
        r1.read(inChars1, 10, 27);
        System.out.println(inChars1);

        BufferedReader b1 = new BufferedReader(r1);
        String s1 = b1.readLine();
        System.out.println(s1);
        s1 = b1.readLine();
        System.out.println(s1);
        
        System.out.println("\n------------------------------------------------\n");
        
        char inChars2[] = 
           "----------------------------------------------".toCharArray();

        DecryptingReader r2 = new DecryptingReader(new FileReader("test2.out"));
        r2.read(inChars2, 10, 27);
        System.out.println(inChars2);
        r2.read(inChars2, 10, 27);
        System.out.println(inChars2);

        BufferedReader b = new BufferedReader(r2);
        String s2 = b.readLine();
        System.out.println(s2);
        s2 = b.readLine();
        System.out.println(s2);
    }
}
