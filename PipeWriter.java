import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import java.io.File;
import java.io.IOException;

/**
 * This allows users to write to a pipe using text file operations.
 *
 * @author Ryan Dawkins
 * @version 1.0
 * @since 2015-02-09
 * @see Pipe
 */
public class PipeWriter extends Pipe {

    public PipeWriter(String pipeName) {
        super(pipeName);
    }

    /**
     * As you would expect this method is used to write a single message into a pipe.
     *
     * @param message
     */
    public void write(String message) {
        try {
            BufferedWriter bf = new BufferedWriter( new OutputStreamWriter( new FileOutputStream(new File(this.getPipeName()))));
            bf.write(message);
            bf.flush();
            bf.close();
        } catch (IOException e) {
        }

    }

}
