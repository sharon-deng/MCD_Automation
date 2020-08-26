package automation.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.TestException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProcessUtils {

    private static final Logger LOGGER = LogManager.getLogger();

    public static void killProcess(String processName) {
        if (System.getProperty("os.name").toLowerCase().indexOf("windows") > -1) {
            WindowsUtils.killByName(processName);
            LOGGER.debug("Kill the windows process:" + processName);
        } else {

            if (processName.contains(".")) {
                processName = processName.split("\\.")[0];
            }

            if (processName.contains("_")) {
                processName = processName.split("_")[0];
            }

            try {
                String user = System.getProperty("user.name");
                Runtime.getRuntime().exec("pkill -9 -u " + user + " " + processName);
                LOGGER.debug("Kill the Linux process:" + processName);
            } catch (IOException e) {
                LOGGER.error("Kill the Linux process got exception" , e);
                throw new TestException("Kill the Linux process got exception" , e);
            }
        }
    }


    public static boolean isWinProcessRunning(String processName) {
        try {
            Runtime testRunTime = Runtime.getRuntime();
            Process process = testRunTime.exec("tasklist");
            String line;

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            while ((line = reader.readLine()) != null) {
                if (line.contains(processName)) {
                    return true;
                }
            }
            return false;
        } catch (IOException e) {
            LOGGER.warn("<isWinProcessRunning> - got IOException", e);
            throw new TestException("<isWinProcessRunning> - got IOException", e);
        }

    }

}
