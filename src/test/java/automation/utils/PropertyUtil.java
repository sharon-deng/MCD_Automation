package automation.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.TestException;
import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Properties;


public class PropertyUtil {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String CONFIG_PROPERTIES_PATH = ConstantValue.CONFIG_PROPERTIES_PATH;
    private static final HashMap<String, Properties> Hmap = new HashMap<>();

    /**
     * Convert and return the property value into boolean value
     * @param	filePath	the project specific property file under '/src/test/resources/' folder
     * @param	key	the key
     * @return	the boolean value.
     * @author	Gary Ye Feng
     * @version 1.0
     */
    public static boolean getBooleanVal (String filePath, String key){
        String strBooleanVal = getItemValue(filePath, key);
        LOGGER.trace("The '"+key+"' value is <"+strBooleanVal+">");
        strBooleanVal=strBooleanVal.trim();
        return ("yes".equalsIgnoreCase(strBooleanVal) || "y".equalsIgnoreCase(strBooleanVal)
                || "true".equalsIgnoreCase(strBooleanVal));
    }

    public static boolean getBooleanVal ( String key){
        return getBooleanVal (CONFIG_PROPERTIES_PATH, key);
    }


    /**
     * Return the property value as String value
     * @param	filePath	the project specific property file under '/src/test/resources/' folder
     * @param	key	the key
     * @return	the String value.
     * @author	Gary Ye Feng
     * @version 1.0
     */
    public static String getStringVal (String filePath, String key) {
        String reVal = getItemValue(filePath, key);
        if (reVal!=null) {
            reVal=reVal.trim();
        }
        LOGGER.trace("The '" + key + "' value is <"+reVal+">");
        return reVal;
    }

    public static String getStringVal (String key) {
        return getStringVal (CONFIG_PROPERTIES_PATH, key);
    }



    /**
     * Convert and return the property value into boolean value
     * @param	filePath	the project specific property file under '/src/test/resources/' folder
     * @param	key	the key
     * @return	the integer value.
     * @author	Gary Ye Feng
     * @version 1.0
     */
    public static int getIntegerVal(String filePath,String key) {

        int val = 0;
        String reVal = getItemValue(filePath, key);
        if (reVal!=null) {
            reVal=reVal.trim();
            val = Integer.parseInt(reVal);
        }
        LOGGER.trace("The '"+key+"' value is <"+reVal+">");
        return val;
    }

    public static int getIntegerVal (String key) {
        return getIntegerVal (CONFIG_PROPERTIES_PATH, key);
    }



    public static double getDoubleVal(String filePath,String key) {
        double val=0;
        String reVal = getItemValue(filePath, key);
        if (reVal!=null) {
            reVal=reVal.trim();
            val = Double.valueOf(reVal);
        }
        LOGGER.trace("The '"+key+"' value is <"+reVal+">");
        return val;
    }

    public static double getDoubleVal (String key) {
        return getDoubleVal (CONFIG_PROPERTIES_PATH, key);
    }



    private synchronized static String getItemValue(String filePath, String key){
        String reVal;
        File f = new File(filePath);

        if ((f.exists() && f.isFile())) {
            if (!Hmap.containsKey(filePath)) {
                try {
                    Hmap.put(filePath, getProperties(filePath));
                } catch (Exception e) {
                    LOGGER.error("Can not find the properties file '"+filePath+"'", e);
                    throw new TestException("Can not find the properties file '"+filePath+"'");
                }
            }

            try {
                reVal = Hmap.get(filePath).getProperty(key);
                if (reVal==null) {
                    LOGGER.warn("In "+filePath+", The value of item:("+key+") is: null, please check the file: "+filePath);
                    reVal="";
                }
                reVal=reVal.trim();
            } catch (Exception e) {
                LOGGER.error("In "+filePath+", can NOT find item: ("+ key +")", e);
                throw new TestException("In "+filePath+", can NOT find item: ("+ key +")");
            }

            LOGGER.trace("In file:"+filePath+", The value of ("+key+") is: ("+ reVal +")");
        } else {
            LOGGER.error("<getItemValue> - Cannot find the properties file: " + filePath);
            throw new TestException("Cannot find the properties file: "+ filePath);
        }

        return reVal;
    }


    private static Properties getProperties(String config) {
        Properties properties = new Properties();
        try(FileInputStream inStream = new FileInputStream(new File(config))){
            properties.load(inStream);
            inStream.close();
            return properties;
        } catch (Exception ex){
            LOGGER.error("<getProperties> - got exception: ", ex);
            throw new TestException("<getProperties> - got exception", ex);
        }
    }
}
