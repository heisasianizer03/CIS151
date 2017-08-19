/**
 * SimpleReader borrowed from Horstmann
 * @author Paul Diaz
 * CS 151
 * Prof. Kim
 */
import java.io.IOException;
import java.io.Reader;

/**
 *
 * @author Paul
 */
public class SimpleReader extends Reader{
    Reader reader;
    
 /** 
      Construct an decrypting reader that decorates a given reader
      @param reader the reader to decorate
   */
   public SimpleReader(Reader reader)
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

      return result;
   }

   /** 
      Close the reader
   */
   public void close() throws IOException
   {
      reader.close();
   }

}
