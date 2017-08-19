/**
 * CaesarCipher borrowed from Horstmann Chapter 5
 * Paul Diaz
 * CS 151
 * Prof. Kim
 */

/**
   Encrypts and decrypts using the Caesar cipher 
*/
public class CaesarCipher implements Cipher
{
   public void encrypt(char[] cbuf, int off, int len)
   {
      for(int i = off; i < off + len; i++)
      {
         char c = cbuf[i];
         if (Character.isLetter(c))
         {
            c = (char) ((int) c + CAESAR_OFFSET);
            if (!Character.isLetter(c))
               c = (char) ((int) c - ALPHABET_SIZE);
               // C s no longer in alphabet.  Move it back in.
            cbuf[i] = c; 
         }
      }
   }
   
   public void decrypt(char[] cbuf, int off, int len)
   {
      for(int i = off; i < off + len; i++)
      {
         char c = cbuf[i];
         if (Character.isLetter(c))
         {
            c = (char) ((int) c - CAESAR_OFFSET);
            if (!Character.isLetter(c))
               c = (char) ((int) c + ALPHABET_SIZE);
               // C is no longer in the alphabet.  Move it back in.

            cbuf[i] = c;  
         }
      }  
   }

   static final int CAESAR_OFFSET = 3;
   static final int ALPHABET_SIZE = 26;
}