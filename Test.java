import java.io.File;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

class Test
{

  public static void main(String[] args) {

    String command = "mkfifo hello";
    try {
      System.out.println(command);
      Process process = Runtime.getRuntime().exec(command);
      process.getOutputStream().flush();
      process.getOutputStream().close();
    } catch(IOException exception) {
    }

    File pipe = new File("hello");
    if(!pipe.exists() && !pipe.isDirectory()) {
      System.out.println("NO!");
    }

  }

}
