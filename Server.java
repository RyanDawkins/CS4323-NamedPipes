public class Server extends HomeworkOne
{

  public static String OUTPUT = "rdawkin-server";

  public static void main(String[] args)
  {
    Pipe.create(Client.OUTPUT);
    Pipe.create(Server.OUTPUT);

    PipeWriter pw = new PipeWriter(Server.OUTPUT);
    PipeReader pr = new PipeReader(Client.OUTPUT);

    while(true) {

      System.out.print("Server : PID "+getProcessId()+" - Recieved: ");
      String message = pr.read();
      System.out.println(message);
      System.out.print("Response: ");
      if(message.equals("Hello")) {
        pw.write("Hi");
      } else {
        System.out.println();
      }

    }

  }
}
