import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class PipeWriter extends Pipe
{

  public PipeWriter(String pipeName)
  {
    super(pipeName);
  }

  public void write(String message)
  {
    try {
      Process process = Runtime.getRuntime().exec(message+" > "+this.getPipeName());
      process.getOutputStream().flush();
      process.getOutputStream().close();
    } catch(IOException exception) {
    }
  }

}
