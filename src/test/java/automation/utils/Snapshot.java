package automation.utils;

import automation.web.driver.WebDriverManager;
import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.TestException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;


public class Snapshot {

    private static final Logger LOGGER = LogManager.getLogger();

    @Attachment(value = "{name}", type = "text/plain")
    public static String attachLog(final String name, final String data) {
        return data;
    }

    @Attachment(value = "Screenshot Compare Snapshot", type = "image/png")
    public static byte[] attachCompareSnapShot(String imageFullPath){
        try {
            BufferedImage bufferedImage = ImageIO.read(new File(imageFullPath));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "PNG", baos);
            baos.flush();
            byte[] imageInByte = baos.toByteArray();
            baos.close();
            return imageInByte;
        } catch (Exception e) {
            LOGGER.error("<attachCompareSnapShot> - Error on attach Page Snapshot", e);
            throw new TestException("<attachCompareSnapShot> - Error on attach Page Snapshot", e);
        }
    }

    @Attachment(value = "Page Screenshot - {name}", type = "image/png")
    public static byte[] attachDriverSnapShot(String name){
        WebDriver driver = WebDriverManager.getDriver();
        try{
            if (driver!=null){
                byte[] imageInByte = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
                LOGGER.debug("<attachDriverSnapShot> - Attach page snapshot into Allure Report.");
                return imageInByte;
            } else {
                LOGGER.warn("<attachDriverSnapShot> - Not attach page snapshot as null driver object");
            }

        } catch (Exception ex){
            LOGGER.warn("<attachDriverSnapShot> - Got Exception", ex);
        }
        return null;
    }

    @Attachment(value = "Page Screenshot - {name}", type = "image/png")
    public static byte[] attachDriverSnapShot(WebDriver driver, String name){
        try{
            if (driver!=null){
                byte[] imageInByte = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
                LOGGER.debug("<attachDriverSnapShot> - Attach page snapshot into Allure Report.");
                return imageInByte;
            } else {
                LOGGER.warn("<attachDriverSnapShot> - Not attach page snapshot as null driver object");
            }

        } catch (Exception ex){
            LOGGER.warn("<attachDriverSnapShot> - Got Exception", ex);
        }
        return null;
    }

    @Attachment(value = "Web Element Snapshot", type = "image/png")
    public static byte[] attachElementSnapShot(WebElement element){
        try{
            if (element!=null){
                byte[] returnByte = element.getScreenshotAs(OutputType.BYTES);
                LOGGER.debug("<attachElementSnapShot> - Attach element snapshot into Allure Report");
                return returnByte;
            } else {
                LOGGER.warn("<attachElementSnapShot> - Not attach element snapshot as null object");
            }
        } catch (Exception ex){
            LOGGER.warn("<attachElementSnapShot> - Got Exception", ex);
        }
        return null;
    }



    public static void saveDriverSnapShot(WebDriver driver, String storePath){
        if (driver==null || storePath==null){
            LOGGER.error("<saveDriverSnapShot> - driver or store path is null");
        } else{
            try{
                File fileFullPath = new File(storePath);
                if (!fileFullPath.getParentFile().exists()){
                    fileFullPath.getParentFile().mkdirs();
                }
                File SrcFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(SrcFile, fileFullPath);
                LOGGER.debug("<saveDriverSnapShot> - Save snapshot in: "+storePath);
            } catch (Exception ex){
                LOGGER.warn("<saveDriverSnapShot> - Got Exception", ex);
            }
        }
    }


    public static void saveElementSnapShot(WebElement element, String storePath){
        if (element==null) {
            LOGGER.error("<saveElementSnapShot> - element is null");
        } else {
            try {
                // Create the File object
                File fileFullPath = new File(storePath);
                if (!fileFullPath.getParentFile().exists()){
                    fileFullPath.getParentFile().mkdirs();
                }
                File srcFile = element.getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(srcFile, fileFullPath);
                LOGGER.debug("<saveElementSnapShot> - Save snapshot in: "+storePath);
            } catch (Exception e) {
                LOGGER.error("<saveElementSnapShot> - error on saving element snapshot", e);
            }
        }
    }


}
