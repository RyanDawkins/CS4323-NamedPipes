import java.lang.RuntimeException;

/**
 * This exception will be used by the Pipe.java class more than likely.
 *
 * @author Ryan Dawkins
 * @see Pipe.java
 */
public class PipeDoesNotExistException extends RuntimeException
{

  /**
   * This constructor is for calling the exception with a pipe name only
   *
   * @param   String  The name of the pipe that does not exist
   */
  public PipeDoesNotExistException(String pipeName)
  {
    super( message(pipeName) );
  }

  /**
  * This constructor is for calling the exception with a message OR
  * if isMessage is false the pipeName
  *
  * @param    String  The name of the pipe that does not exist or the message
  * @param    bool    Whether or not the string parameter is a message or pipe
  */
  public PipeDoesNotExistException(String message, boolean isMessage) {
    this( (isMessage ? message : message(message)) );
  }

  /**
   * Constructs the pipe does not exist method
   *
   * @param   String  The name of the pipe
   * @return          The error message with a pipe name
   */
  public static String message(String pipeName) {
    return "Pipe named \""+pipeName+"\" does not exist.";
  }

}
