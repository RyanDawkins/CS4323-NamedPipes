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
    runCommand("printf \""+message+"\" > "+this.getPipeName());
    System.out.println("printf \""+message+"\" > "+this.getPipeName());
  }

}
