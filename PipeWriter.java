import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import java.io.File;
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
      BufferedWriter bf = new BufferedWriter(
        new OutputStreamWriter(
          new FileOutputStream(new File(this.getPipeName()))
        )
      );
      bf.write(message);
      bf.flush();
      bf.close();
    } catch(IOException e) {

    }

  }

}
