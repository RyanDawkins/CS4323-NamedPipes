import java.io.File;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

class Test
{

  public static void main(String[] args) {

    String command = "mkfifo hello";
    Pipe.create("hello");

    File pipe = new File("hello");
    if(!pipe.exists() && !pipe.isDirectory()) {
      System.out.println("NO!");
    }

    Pipe.close("hello");
  }
}
