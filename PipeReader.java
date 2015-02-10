import java.lang.Runtime;
import java.lang.Process;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * This class is a very simple version of the Pipe class. It is used to read information from a unidirection pipe.
 *
 * @author Ryan Dawkins
 * @version 1.0
 * @since 2015-02-09
 * @see Pipe
 */
public class PipeReader extends Pipe {

    public PipeReader(String pipeName) {
        super(pipeName);
    }

    /**
     * Method uses the cat command to read in info from the pipe
     *
     * @return Returns the output from the pipe
     */
    public String read() {
        String pipeOutput = "";
        try {
            Process process = Runtime.getRuntime().exec("cat " + this.getPipeName());
            process.getOutputStream().flush();
            process.waitFor();

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(process.getInputStream())
            );

            String line;
            while ((line = in.readLine()) != null) {
                pipeOutput += line + "\n";
            }
        } catch (IOException exception) {
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return pipeOutput.trim();
    }

}
