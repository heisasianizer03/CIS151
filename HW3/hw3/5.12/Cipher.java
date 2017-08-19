/**
 * Cipher borrowed from Horstmann
 * Paul Diaz
 * CS 151
 * Prof. Kim
 */
/**
   An interface for encryption and decryption algorithms
*/
public interface Cipher
{
   /**
     The encryption algorithm
     @param cbuf contains the characters to encrypt
     @param off the offset of the characters to encrypt
     @param len the number of characters to encrypt
   */
   public void encrypt(char[] cbuf, int off, int len);

   /**
     The decryption algorithm
     @param cbuf contains the characters to decrypt
     @param off the offset of the characters to decrypt
     @param len the number of characters to decrypt
   */
   public void decrypt(char[] cbuf, int off, int len);
}