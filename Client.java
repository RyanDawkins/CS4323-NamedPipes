public class Client extends HomeworkOne
{

  public static String OUTPUT = "rdawkin-client";

  public static void main(String[] args)
  {
    PipeWriter pw = new PipeWriter(Client.OUTPUT);
    PipeReader pr = new PipeReader(Server.OUTPUT);

    System.out.print("Client : PID "+getProcessId()+" - Sent ");
    pw.write("Hello");
    System.out.println("'Hello'");
    System.out.print("Client : PID "+getProcessId()+" - Client received: ");
    String message = pr.read();
    System.out.println(message);
  }
}
