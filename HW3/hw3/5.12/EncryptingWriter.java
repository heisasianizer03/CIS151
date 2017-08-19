/**
 * EncryptingWriter borrowed from Horstmann
 * @author Paul Diaz
 * CS 151
 * Prof. Kim
 */

import java.io.Writer;
import java.io.IOException;

/** 
   A decorator class for encrypting text before writing it.
*/
public class EncryptingWriter extends Writer{
    /** 
      Construct an encrypting writer that decorates a given writer
      @param writer the writer to decorate
    */
    public EncryptingWriter(Writer writer){
        this.writer = writer;
    }
    /** 
      Write the specified characters from a buffer
      @param cbuf the buffer to write
      @param off the offset within the buffer to start writing
      @param len the number of characters to write
    */
    public void write(char[] cbuf, int off, int len) throws IOException{
        CaesarCipher cipher = new CaesarCipher();
        
        //1. Encrypt
        cipher.encrypt(cbuf, off, len);
      
        //2. writer.writer()
        writer.write(cbuf, off, len);
    }

    /** 
        Close the writer
    */
    public void close() throws IOException{
        writer.close();
    }

    /** 
        Flush the writer
    */
    public void flush() throws IOException{
        writer.flush();
    }

    private Writer writer;
}