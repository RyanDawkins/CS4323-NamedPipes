import java.lang.Runtime;
import java.lang.Process;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class PipeReader extends Pipe
{

  public PipeReader(String pipeName)
  {
    super(pipeName);
  }

  /**
   * Method uses the cat command to read in info from the pipe
   *
   * @return    Returns the output from the pipe
   */
  public String read()
  {
    String pipeOutput = "";
    try {
      Process process = Runtime.getRuntime().exec("cat "+this.getPipeName());

      BufferedReader in = new BufferedReader(
        new InputStreamReader(process.getInputStream())
      );

      String line;
      while((line = in.readLine()) != null) { pipeOutput += line + "\n"; }
    } catch(IOException exception) {
    }

    return pipeOutput.trim();
  }

}
