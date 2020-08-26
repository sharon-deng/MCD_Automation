package automation.utils;

import com.google.gson.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.TestException;
import java.io.*;

/**
 * The class to Serialize and Deserialize Java Object
 * @author Gary Ye
 * @version 1.0 Completed Date: 19 Jan 2020
 */
public final class SerializeObject {

    private static final Logger LOGGER = LogManager.getLogger();

    private SerializeObject(){}

    /**
     * Convert Java object to Json String
     *
     * @author    Gary Ye Feng
     *
     * @param       object      The java object
     * @param       clazz       The class of the Java Object
     * @return  return the Json String
     */
    public static String objectToJsonString(Object object, Class clazz) {
        try{
            Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();
            TypeAdapter adapter = gson.getAdapter(clazz);
            return adapter.toJson(object);
        } catch (Exception ex){
            LOGGER.error("<objectToJsonString> - Error", ex);
            throw new TestException("<objectToJsonString> - Error", ex);
        }
    }

    public static void objectToJsonFile(Object object, Class clazz, String path) {
        try(FileWriter writer = new FileWriter(path)){
            File f = new File(path);
            f.getParentFile().mkdirs();
            String myObjectJson = objectToJsonString(object, clazz);
            writer.write(myObjectJson);
            writer.flush();
        } catch (Exception ex){
            LOGGER.error("<objectToJsonFile> - Got exception");
            throw new TestException("<objectToJsonFile> - Got exception", ex);
        }
    }


    /**
     * Convert Json String to Java object
     *
     * @author    Gary Ye Feng
     *
     * @param       strJson     the Json String
     * @param       clazz       the class of the Java Object
     * @return  return the Java object
     */
    public static <T> Object jsonStringToObject(String strJson, Class<T> clazz) {
        try{
            Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();
            return gson.fromJson(strJson, clazz);
        } catch (Exception ex){
            LOGGER.error("<jsonStringToObject> - Error");
            throw new TestException("<jsonStringToObject> - Error", ex);
        }
    }


    /**
     * Convert Json file to Java object
     *
     * @author    Gary Ye Feng
     *
     * @param       path        The string of the file path
     * @param       clazz       the class of the Java Object
     * @return  return the Java object
     */
    public static <T> Object jsonFileToObject(String path, Class<T> clazz) {
        File file = new File(path);
        try(Reader reader = new BufferedReader(new FileReader(file))){
            Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();
            return gson.fromJson(reader, clazz);
        } catch (Exception ex){
            LOGGER.error("<jsonFileToObject> - Error", ex);
            throw new TestException("<jsonFileToObject> - Error", ex);
        }
    }

    /**
     * Write a Json String into a Json file
     *
     * @author    Gary Ye Feng
     * @param       strJson     the Json String
     * @param       path        the String path of the Json file
     */
    public static void stringToFile(String strJson, String path) {
        try(FileWriter writer = new FileWriter(path)){
            File f = new File(path);
            f.getParentFile().mkdirs();
            writer.write(strJson);
            writer.flush();
        } catch (Exception ex){
            LOGGER.error("<stringToJsonFile> - Got exception");
            throw new TestException("<stringToJsonFile> - Got exception", ex);
        }
    }




//    public static void main(String[] args){
//
//
//        List<DataCSV> result = (List<DataCSV>) SerializeObject.csvFileToObject("d:/test.csv", DataCSV.class);
//
//        SerializeObject.objectToCsvFile(result,"d:/test001.csv");
//        System.out.println(result);
//    }

//    public static void main(String[] args){
//        CommonData result = (CommonData) SerializeObject.jsonFileToObject("d:/CommonData.json", CommonData.class);
//
//        SerializeObject.objectToJsonFile(result, CommonData.class, "d:/CommonData1.json");
//        System.out.println(result);
//    }


}
