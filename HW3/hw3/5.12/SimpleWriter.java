
/**
 * SimpleWriter borrowed from Horstmann
 * @author Paul Diaz
 * CS 151
 * Prof. Kim
 */
import java.io.IOException;
import java.io.Writer;
/**
 *
 * @author Paul
 */
public class SimpleWriter extends Writer{
    private Writer writer;
 
    public SimpleWriter(Writer writer){
        this.writer = writer;
    }

    /** 
      Write the specified characters from a buffer
      @param cbuf the buffer to write
      @param off the offset within the buffer to start writing
      @param len the number of characters to write
   */
    public void write(char[] cbuf, int off, int len) throws IOException {
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
}
