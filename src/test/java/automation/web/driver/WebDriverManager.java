package automation.web.driver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

/*** This class conduct the Web Driver.
* @author Gary Ye Feng
* @version 1.0
*/
public class WebDriverManager {
    private static final Logger LOGGER = LogManager.getLogger();
    private static ThreadLocal<WebDriver> seleniumDriver = new ThreadLocal<>();
    private static ThreadLocal<CapDynamicProxy> dynamicProxy = new ThreadLocal<>();
    
    private WebDriverManager(){

    }


    private static WebDriver createInstance() {
        WebDriver factoryDriver;
        try {
            CapDynamicProxy capDynamicProxy = new CapDynamicProxy();
            factoryDriver = capDynamicProxy.getAvailalbeDriver();
            LOGGER.trace(factoryDriver.toString());
        } catch (WebDriverException e) {
            LOGGER.error("Create Web Driver Instance error", e);
            throw new WebDriverException("Create Web Driver Instance error", e);
        }
        return factoryDriver;
    }


    /**
     * Get the webDriver of the ThreadLocal webDriver object.
     * @return	The object of ThreadLocal WebDriver.
     * @author	Gary Ye Feng
     * @version	1.0
     */
    public static WebDriver getDriver(){
        return seleniumDriver.get();
    }
	
    /**
     * New a webDriver instance.
     * @author	Gary Ye Feng
     * @version	1.0
     */
    public static void newDriver() {
    	if (seleniumDriver.get()==null){
            seleniumDriver.set(createInstance());
        }
    }
	
    /**
     * Quit the web driver and close browser.
     * @author	Gary Ye Feng
     * @version	1.0
     */
    public static void quitDriver(){
        try {
            if (seleniumDriver.get() != null) {
                seleniumDriver.get().close();
                seleniumDriver.get().quit();
                seleniumDriver.set(null);
            }
        } catch (WebDriverException e) {
            LOGGER.error("Quit Web Driver got exception", e);
            throw new WebDriverException("Quit Web Driver got exception", e);
        }
    }
	
	
    public static void killDriver(){
        getDynamicProxy().killWebDriverProcess();
        seleniumDriver.set(null);
    }
	
    public static void killBrowserProcess(){
        getDynamicProxy().killTestBrowserProcess();
    }

    private static CapDynamicProxy getDynamicProxy(){
        if (dynamicProxy.get()==null){
            CapDynamicProxy newProxy = new CapDynamicProxy();
            dynamicProxy.set(newProxy);
        }
        return dynamicProxy.get();
    }
}
