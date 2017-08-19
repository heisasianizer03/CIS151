/**
 * DecryptingWriter borrowed from Horstmann
 * @author Paul Diaz
 * CS 151
 * Prof. Kim
 */

import java.io.Reader;
import java.io.IOException;

/** 
   A decorator class for decrypting text after reading it.
*/
public class DecryptingReader extends Reader
{
   /** 
      Construct an decrypting reader that decorates a given reader
      @param reader the reader to decorate
   */
   public DecryptingReader(Reader reader)
   {
      this.reader = reader;
   }

   /** 
      Read the specified characters into a buffer
      @param cbuf the buffer to read into
      @param off the offset within the buffer to start reading
      @param len the number of characters to read
   */
   public int read(char[] cbuf, int off, int len) throws IOException
   {
      int result = reader.read(cbuf, off, len);

      CaesarCipher cipher = new CaesarCipher();

      cipher.decrypt(cbuf, off, len);

      return result;
   }

   /** 
      Close the reader
   */
   public void close() throws IOException
   {
      reader.close();
   }

   private Reader reader;
}