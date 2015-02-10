import java.io.File;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

/**
 * This is an abstract class for representation of a pipe.
 *
 * @author Ryan Dawkins
 * @version 1.0
 * @since 2015-02-09
 */
public abstract class Pipe {
    private String pipeName;

    /**
     * Constructor to see if a pipe exists and save it
     *
     * @throws PipeDoesNotExistException
     */
    public Pipe(String pipeName) {
        this.setPipeName(pipeName);

        if (!this.exists(this.getPipeName())) {
            throw new PipeDoesNotExistException(this.pipeName);
        }
    }

    /*
     * Getters and setters for the pipeName
     */
    public String getPipeName() {
        return this.pipeName;
    }
    public void setPipeName(String pipeName) {
        this.pipeName = pipeName;
    }

    /**
     * Runs a simple command using the runtime output stream.
     * @param command   An ssh command in string format
     */
    public static void runCommand(String command) {
        try {
            Process process = Runtime.getRuntime().exec(command);
            process.getOutputStream().flush();
            process.getOutputStream().close();
            process.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates a new pipe using the mkfifo command
     * @param pipeName  A string representing the desired pipe
     */
    public static void create(String pipeName) {
        runCommand("mkfifo " + pipeName);
    }

    /**
     * Closes the pipe using the rm command
     */
    public void close() {
        close(this.getPipeName());
    }

    /**
     * Uses the rm method to close the pipe
     * @param pipeName  The pipe name
     */
    public static void close(String pipeName) {
        runCommand("rm " + pipeName);
    }

    /**
     * Method to check if a pipe exists by checking if a file exists.
     *
     * @param String The name of the pipe that should exist
     * @return Whether or not the pipe as a parameter exists
     */
    public static boolean exists(String pipeName) {
        File file = new File(pipeName);
        return (file.exists() && !file.isDirectory());
    }

}
