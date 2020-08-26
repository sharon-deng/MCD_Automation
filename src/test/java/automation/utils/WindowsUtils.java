package automation.utils;

import com.google.common.collect.ImmutableList;
import org.openqa.selenium.Platform;
import org.openqa.selenium.os.CommandLine;
import org.testng.TestException;
import java.io.File;
import java.util.Map;
import java.util.Properties;

import static org.openqa.selenium.Platform.WINDOWS;

public class WindowsUtils {

    private WindowsUtils(){}

    private static final boolean THIS_IS_WINDOWS = Platform.getCurrent().is(WINDOWS);
    private static Properties env = null;

    public static void killByName(String name) {
        executeCommand(findTaskKill(), "/f", "/t", "/im", name);
    }

    public static void killPID(String processID) {
        executeCommand(findTaskKill(), "/f", "/t", "/pid", processID);
    }
    public static String getProgramFilesPath() {
        return getEnvVarPath("ProgramFiles", "C:\\Program Files").replace(" (x86)", "");
    }

    public static String getProgramFiles86Path() {
        return getEnvVarPath("ProgramFiles(x86)", "C:\\Program Files (x86)");
    }

    private static String getEnvVarPath(final String envVar, final String defaultValue) {
        String pf = getEnvVarIgnoreCase(envVar);
        if (pf != null) {
            File programFiles = new File(pf);
            if (programFiles.exists()) {
                return programFiles.getAbsolutePath();
            }
        }
        return new File(defaultValue).getAbsolutePath();
    }

    public static ImmutableList getPathsInProgramFiles(final String childPath) {
        return new ImmutableList.Builder<>()
                .add(getFullPath(WindowsUtils.getProgramFilesPath(), childPath))
                .add(getFullPath(WindowsUtils.getProgramFiles86Path(), childPath))
                .build();
    }

    private static String getFullPath(String parent, String child) {
        return new File(parent, child).getAbsolutePath();
    }

    public static String getEnvVarIgnoreCase(String var) {
        Properties p = loadEnvironment();
        for (String key : p.stringPropertyNames()) {
            if (key.equalsIgnoreCase(var)) {
                return env.getProperty(key);
            }
        }
        return null;
    }

    /**
     * Finds the system root directory, e.g. "c:\windows" or "c:\winnt"
     *
     * @return location of system root
     */
    public static File findSystemRoot() {
        Properties p = loadEnvironment();
        String systemRootPath = p.getProperty("SystemRoot");
        if (systemRootPath == null) {
            systemRootPath = p.getProperty("SYSTEMROOT");
        }
        if (systemRootPath == null) {
            systemRootPath = p.getProperty("systemroot");
        }
        if (systemRootPath == null) {
            throw new TestException("SystemRoot apparently not set!");
        }
        File systemRoot = new File(systemRootPath);
        if (!systemRoot.exists()) {
            throw new TestException("SystemRoot doesn't exist: " + systemRootPath);
        }
        return systemRoot;
    }

    /**
     * Finds taskkill.exe
     *
     * @return the exact path to taskkill.exe, or just the string "taskkill" if it couldn't be found
     *         (in which case you can pass that to exec to try to run it from the path)
     */
    public static String findTaskKill() {
        String taskkill = "taskkill";
        File systemRoot = findSystemRoot();
        File taskkillExe = new File(systemRoot, "system32/taskkill.exe");
        if (taskkillExe.exists()) {
            taskkill = taskkillExe.getAbsolutePath();
        }
        return taskkill;
    }

    /**
     * Returns true if the current OS is MS Windows; false otherwise
     *
     * @return true if the current OS is MS Windows; false otherwise
     */
    public static boolean thisIsWindows() {
        return THIS_IS_WINDOWS;
    }

    public static synchronized Properties loadEnvironment() {
        if (env != null) {
            return (Properties) env.clone();
        }
        env = new Properties();
        for (Map.Entry entry : System.getenv().entrySet()) {
            env.put(entry.getKey(), entry.getValue());
        }
        return (Properties) env.clone();
    }

    private static String executeCommand(String commandName, String... args) {
        CommandLine cmd = new CommandLine(commandName, args);
        cmd.execute();

        String output = cmd.getStdOut();
        if (cmd.getExitCode() == 0 || cmd.getExitCode() ==  128 || cmd.getExitCode() ==  255) {
            return output;
        }
        throw new TestException("exec return code " + cmd.getExitCode() + ": " + output);
    }

}
