import java.io.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * This class is used for the server to communicate with the Client.
 *
 * @author Ryan Dawkins
 * @version 1.0
 * @since 2015-02-09
 * @see HomeworkOne
 */
public class Server extends HomeworkOne {

    public static String OUTPUT = "rdawkin-server";
    public static String SERVER_KEY = "qwertyuiop";
    public static String RECEIVED_FILE = "Received File";
    public static String EOF = "Server-EOF";
    public static String TERMINATED = "terminated";

    public static void main(String[] args) {

        // The print command for the current date.
        print("Running on "+getCurrentDate());

        // Creates the Pipe for the server writing
        Pipe.create(Server.OUTPUT);
        PipeWriter pw = new PipeWriter(Server.OUTPUT);

        // This loop is to ensure processes are properly synced.
        while(!Pipe.exists(Client.OUTPUT)) {}
        PipeReader pr = new PipeReader(Client.OUTPUT);

        // Synchronizing with special message
        String key = pr.read();
        if (key.equals(Client.PUBLIC_KEY)) {
            pw.write(SERVER_KEY);
            print("Synchronized to Client.");
        } else {
            print("Error, key not correct");
            System.exit(1);
        }

        // Receiving and writing confirmation on file
        String fileName = pr.read();
        pw.write(RECEIVED_FILE);

        // Reading target
        String target = pr.read();

        // Here we create our BufferedReader to read in the file given.
        // We exit if it is not possible to read the file since it would be useless if it cannot.
        File file = new File(fileName);
        BufferedReader bf = null;
        try {
            bf = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            print("Received bad file");
            System.exit(1);
        }

        // Reading through the file and counting occurences.
        String line = null;
        try {
            int lineNum = 1;
            while ((line = bf.readLine()) != null) {
                int occurences = countOccurences(line, target);
                if(occurences > 0) {

                    // Here is the format: line_num,occurences
                    pw.write(Integer.toString(lineNum)+","+Integer.toString(occurences));
                }
                lineNum++;
            }
        } catch(IOException e) {
            print("Could not read file. Exiting");
            System.exit(1);
        }

        // Writes the EOF termination to the Client.
        pw.write(EOF);
        String finished = pr.read();
        if(finished.equals(Client.FINISHED_RECEIVING)) {
            print("Terminated On "+getCurrentDate());
            pw.write(TERMINATED);
            pw.close();
        }
    }

    /**
     * Easy print method for formatting purposes.
     *
     * @param message
     */
    public static void print(String message) {
        System.out.println("Server : PID " + getProcessId() + " - " + message);
    }

    /**
     * Counts occurences
     *
     * @param haystack    The parent string we are searching in (hay stack)
     * @param needle    The substring we are using to search (needle)
     * @return
     */
    public static int countOccurences(String haystack, String needle) {
        Pattern pattern = Pattern.compile(needle);
        Matcher matcher = pattern.matcher(haystack);
        int count = 0;
        while (matcher.find()){
            count +=1;
        }
        return count;
    }

}
