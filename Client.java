import java.io.IOException;
import java.io.PipedReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.io.File;

/**
 * This is the class that interacts with the Server. It will start the server and create an exchange as delimited
 * in the homework handout provided. The handshake consists of the client sending it's public key and the server
 * returning a private key.
 *
 * @author Ryan Dawkins
 * @version 1.0
 * @since 2015-02-09
 * @see HomeworkOne
 */
public class Client extends HomeworkOne {

    public static String OUTPUT = "rdawkin-client";
    public static String PUBLIC_KEY = "asdfghjkl";
    public static String FINISHED_RECEIVING = "Finished Receiving";

    public static void main(String[] args) {

        print("Running on "+getCurrentDate());

        //Start the server
        ProcessBuilder processBuilder = new ProcessBuilder("java", "Server");
        processBuilder.inheritIO();
        try {
            processBuilder.start();
        } catch (IOException e) {
            print("The Server cannot be started.");
            System.exit(1);
        }

        // Creates writing pipe and waits for server to create its pipe
        Pipe.create(OUTPUT);
        PipeWriter pw = new PipeWriter(OUTPUT);
        while(!Pipe.exists(Server.OUTPUT)) {}
        PipeReader pr = new PipeReader(Server.OUTPUT);

        // Synchronize with Server
        boolean isSynchronized = false;
        pw.write(PUBLIC_KEY);
        while(!isSynchronized) {
            String key = pr.read();
            if (key.equals(Server.SERVER_KEY)) {
                isSynchronized = true;
                print("Synchronized to Server.");
            } else {
                print("Bad key: " + key);
                System.exit(1);
            }
        }

        Scanner scanner = new Scanner(System.in);

        // Grabs the input file name from the user, handles files that do not exist
        String inputFile = "";
        print("Enter File Name :");
        do {
            inputFile = scanner.nextLine();
            File file = new File(inputFile);
            if(Pipe.exists(inputFile)) {
                pw.write(inputFile);
                break;
            } else {
                print("File does not exist. Try again!");
                print("Enter file name :");
            }
        } while(scanner.hasNextLine());

        // Ensures syncing for file
        while(true) {
            String reply = pr.read();
            if(reply.equals(Server.RECEIVED_FILE)) {
                break;
            }
        }

        // Gets the target, this can be a phrase.
        String target = "";
        print("Enter Target :");
        if(scanner.hasNextLine()) {
            target = scanner.nextLine();
            pw.write(target);
        }

        // Grabs response data as the server writes it to the pipe
        // This also adds the total of the occurences
        String response = null;
        int total = 0;
        while(!(response = pr.read()).equals(Server.EOF)) {

            // The protocol is: line_number,occurences
            String[] split = response.split(",");

            total += Integer.parseInt(split[1]);
            print("Target >>"+target+"<< Appeared on Line "+split[0]+",\t"+split[1]+" Times");
        }

        print("Target >>"+target+"<< in File >>"+inputFile+"<< Appeared a Total of "+total+" Times.\n");
        pw.write(FINISHED_RECEIVING);

        // Waits for the server to say it's terminated and then terminates it's own pipes
        if(pr.read().equals(Server.TERMINATED)) {
            print("Terminated On "+getCurrentDate());
            pw.close();
        }
    }

    /**
     * This is a standardized function to make printing in the format desired by the handout easier.
     * @param message   A string to be placed in the format.
     */
    public static void print(String message) {
        System.out.println("Client : PID " + getProcessId() + " - " + message);
    }

}
