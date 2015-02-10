import java.lang.management.ManagementFactory;
import java.util.Date;
import java.util.Calendar;
import java.util.TimeZone;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * This is the base class for the Client and Server class. It allows me to add basic utilities that they both
 * would use.
 *
 * @author Ryan Dawkins
 * @version 1.0
 * @since 2015-02-09
 */
public class HomeworkOne {

    /**
     * Gets the process id by checking the process name (i.e processid@host)
     * @return  The process id
     */
    public static int getProcessId() {
        String processName = ManagementFactory.getRuntimeMXBean().getName();

        int partition = 0;
        for (int i = 0; i < processName.length(); i++) {
            if (processName.charAt(i) == '@') {
                partition = i;
                break;
            }
        }
        String processIdString = processName.substring(0, partition);

        return Integer.parseInt(processIdString);
    }

    /**
     * Gets the current date in a string format by the SimpleDateFormat class
     *
     * @return  The date in format yyyy-MM-dd HH:mm:ss
     */
    public static String getCurrentDate() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        df.setTimeZone(TimeZone.getTimeZone("US/Central"));
        Date today = Calendar.getInstance().getTime();
        return df.format(today);
    }

}
