package automation.web.driver;

import automation.utils.ConstantValue;
import automation.utils.PropertyUtil;
import automation.utils.OsUtil;
import automation.utils.ProcessUtils;
import automation.utils.TimeUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class CapEdge implements CapInterface {
    /**
     * 
     */
    private static final Logger LOGGER = LogManager.getLogger();

    private DesiredCapabilities capabilities;
    private String driverFolder = ConstantValue.DRIVERS_FOLDER;
    private String edgeDriverPath = PropertyUtil.getStringVal("EdgeDriverPath");

    private String browserDriverPath;
    private EdgeOptions options;
    private Proxy proxy;
    
    public CapEdge(){
        //
    }
    
    @Override
    public WebDriver getAvailalbeDriver(){
        getAvailalbeCapabilities();
        return new EdgeDriver();
    }
    
    @Override
    public void killWebDriverProcess(){
        if ("windows".equals(OsUtil.getOSName())){
            TimeUtil.sleep(500);
            ProcessUtils.killProcess(getDriverProcessName());
            TimeUtil.sleep(500);
        }        
    }
    
    @Override
    public void killTestBrowserProcess() {
        if ("windows".equals(OsUtil.getOSName())){
            TimeUtil.sleep(500);
            ProcessUtils.killProcess("edge.exe");
            TimeUtil.sleep(500);
        }    
    }

    
    @Override
    public String getDriverProcessName(){
        LOGGER.debug("getDriverProcessName - the driver process name is: ("+edgeDriverPath+")");
        return edgeDriverPath;
    }
    
    @Override
    public String getDriverPath(){        
        browserDriverPath = ConstantValue.DRIVERS_FOLDER + edgeDriverPath;
        LOGGER.debug("getDriverPath - the browser driver path is: ("+browserDriverPath+")");
        return browserDriverPath;
    }
    
    
    private DesiredCapabilities getAvailalbeCapabilities(){
        newCapabilities();
        updateSpecificCapByOS();
        updateBrowserSpecificCap();
        return capabilities;
    }
    
    private void newCapabilities(){        
        capabilities = new DesiredCapabilities();
        if (PropertyUtil.getBooleanVal("isSetupProxy")) {
            capabilities.setCapability(CapabilityType.PROXY, getSeleniumProxy());
        }
    }
    
    private void updateSpecificCapByOS(){
        capabilities.setPlatform(Platform.WINDOWS);
        System.setProperty("webdriver.edge.driver", getDriverPath());
    };
    
    
    private void updateBrowserSpecificCap(){    
        options = new EdgeOptions();
//        options.setPageLoadStrategy(PageLoadStrategy.EAGER);
//
//        capabilities.setCapability(EdgeOptions.CAPABILITY, options);
        
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
