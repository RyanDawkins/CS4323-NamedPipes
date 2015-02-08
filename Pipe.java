import java.io.File;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

/**
 * This is an abstract class for representation of a pipe.
 */
public abstract class Pipe
{
  private String pipeName;

  /**
   * Constructor to see if a pipe exists and save it
   *
   * @throws  PipeDoesNotExistException
   */
  public Pipe(String pipeName)
  {
    this.setPipeName(pipeName);

    if( !this.pipeExists(this.getPipeName()) )
    {
      throw new PipeDoesNotExistException(this.pipeName);
    }
  }

  public String getPipeName() { return this.pipeName; }
  public void setPipeName(String pipeName) { this.pipeName = pipeName; }

  public static void runCommand(String command)
  {
    try {
      System.out.println(command);
      Process process = Runtime.getRuntime().exec(command);
      process.getOutputStream().flush();
      process.getOutputStream().close();
      process.waitFor();
    } catch(IOException e) {
      e.printStackTrace();
    } catch(InterruptedException e){
      e.printStackTrace();
    }
  }

  public static void create(String pipeName)
  {
    runCommand("mkfifo "+pipeName);
  }

  /**
   * Closes the pipe using the rm command
   *
   */
  public void close()
  {
    close(this.getPipeName());
  }

  public static void close(String pipeName)
  {
    runCommand("rm "+pipeName);
  }

  /**
   * Method to check if a pipe exists by checking if a file exists.
   *
   * @param   String  The name of the pipe that should exist
   * @return          Whether or not the pipe as a parameter exists
   */
  public static boolean pipeExists(String pipeName) {
    File file = new File(pipeName);
    return (file.exists() && !file.isDirectory());
  }

}
