package automation.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;


public final class OsUtil {
    private static final String OS_NAME=System.getProperty("os.name").toLowerCase();

    private OsUtil(){
    }
    
    
    public static String getOSName(){        
        if (OS_NAME.contains("win")) {
            return "windows";
        } else if (OS_NAME.contains("mac")) {
            return "osx";
        } else if (OS_NAME.contains("sunos")) {
            return "solaris";
        } else if (OS_NAME.contains("nux") || OS_NAME.contains("nix") || OS_NAME.contains("aix") ) {
            return "unix";
        }        
        return "err";
    }
    
    public static boolean isMacOS(){
        return ("osx".equalsIgnoreCase(getOSName()));
    }
        
    public static boolean isWinOS(){
        return ("windows".equalsIgnoreCase(getOSName()));
    }
    
    public static boolean isUnixOS(){
        return ("unix".equalsIgnoreCase(getOSName()));
    }
    
    public static boolean isSolarisOS(){
        return ("solaris".equalsIgnoreCase(getOSName()));
    }
    
    public static String getMachineName(){
        String computername="Unknown";
        try {
            computername = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException ex) {
            Logger.getLogger(OsUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return computername;
    }
    

}
