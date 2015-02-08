import java.lang.management.ManagementFactory;

public class HomeworkOne
{

  public static int getProcessId()
  {
    String processName = ManagementFactory.getRuntimeMXBean().getName();

    int partition = 0;
    for(int i = 0; i < processName.length(); i++) {
      if(processName.charAt(i) == '@') {
        partition = i;
        break;
      }
    }
    String processIdString = processName.substring(0, partition);

    return Integer.parseInt(processIdString);
  }

}
