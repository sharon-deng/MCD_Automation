package automation.web.driver;

import automation.utils.ConstantValue;
import automation.utils.PropertyUtil;
import automation.utils.TimeUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class CapIE implements CapInterface {

    /**
     * 
     */
    private static final Logger LOGGER = LogManager.getLogger();

    private DesiredCapabilities capabilities;
    private boolean ifLoadBrowserDefaultProfile=false;
    private String driverFolder = ConstantValue.DRIVERS_FOLDER;
    private String IEDriverPath = PropertyUtil.getStringVal("IEDriverPath");

    private String browserDriverPath;
    InternetExplorerDriver driver;
    
    public CapIE(){
        //
    }
    
    public WebDriver getAvailalbeDriver(){
    	getAvailalbeCapabilities();   
    	driver = new InternetExplorerDriver();
    	return driver;
    }
    
    public void killWebDriverProcess(){
        TimeUtil.sleep(500);
//        ProcessUtils.winKillProcess(getDriverProcessName());
        TimeUtil.sleep(500);
    }
    
    public void killTestBrowserProcess() {
        TimeUtil.sleep(500);
//        ProcessUtils.winKillProcess("iexplore.exe");
        TimeUtil.sleep(500);
    }
    

    public String getDriverPath(){
        browserDriverPath = driverFolder + getDriverProcessName();
        LOGGER.trace("getDriverPath - the browser driver path is: ("+browserDriverPath+")");
        return browserDriverPath;
    }
    
    
    public String getDriverProcessName(){
        LOGGER.trace("getDriverProcessName - the driver process name is: ("+IEDriverPath+")");
        return IEDriverPath;
    }
    

    private DesiredCapabilities getAvailalbeCapabilities(){
        newCapabilities();
        updateSpecificCapByOS();
        updateBrowserSpecificCap();
        return capabilities;
    }
    
    private void newCapabilities() {        
        capabilities = new DesiredCapabilities();
        if (PropertyUtil.getBooleanVal("isSetupProxy")) {
            capabilities.setCapability(CapabilityType.PROXY, getSeleniumProxy());
        }
    }
    
    
    private void updateSpecificCapByOS(){
        capabilities.setPlatform(Platform.WINDOWS);
        System.setProperty("webdriver.ie.driver", getDriverPath());
    };
    
    
    private void updateBrowserSpecificCap(){    
        capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
        capabilities.setCapability(InternetExplorerDriver.FORCE_CREATE_PROCESS, true);
        capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
        capabilities.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, true);
        capabilities.setCapability(InternetExplorerDriver.ENABLE_ELEMENT_CACHE_CLEANUP,true);    
        capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
        
        capabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
        capabilities.setCapability(CapabilityType.SUPPORTS_APPLICATION_CACHE, false);
        capabilities.setCapability(CapabilityType.SUPPORTS_SQL_DATABASE , true);
        capabilities.setCapability(CapabilityType.SUPPORTS_ALERTS, true);
        capabilities.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT, true);
        
        capabilities.setCapability(CapabilityType.SUPPORTS_WEB_STORAGE, true);
        capabilities.setCapability(CapabilityType.SUPPORTS_FINDING_BY_CSS,true);        
        capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);        
        capabilities.setCapability(CapabilityType.HAS_NATIVE_EVENTS, true);
        capabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, 
                UnexpectedAlertBehaviour.ACCEPT);    
    }

}
