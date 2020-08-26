package automation.web.driver;

import automation.utils.PropertyUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.TestException;

import java.io.File;
import java.util.logging.Level;


public class CapChrome implements CapInterface {

    private static final Logger LOGGER = LogManager.getLogger();

    private ChromeDriverService service;
    private ChromeOptions options = new ChromeOptions();
    private String userName=System.getProperty("user.name");
    private String userProfile;
    private String chromeBinaryPath=getDriverPath();
    private String browserDriverPath;
    private File pathToBinary;
    ChromeDriver driver;
    DesiredCapabilities capabilities = DesiredCapabilities.chrome();
    
    public CapChrome(){
        // Do nothing
    }
    
    @Override
    public WebDriver getAvailalbeDriver(){
        try {
            System.setProperty("webdriver.chrome.driver", chromeBinaryPath);
//            capabilities.setCapability(ChromeOptions.CAPABILITY, options);

            // Setup proxy
            if (PropertyUtil.getBooleanVal("isSetupProxy")){
                options.setProxy(getSeleniumProxy());
            }

            if (PropertyUtil.getBooleanVal("isHeadless")) {
                options.setHeadless(true);
            } else {
                options.setHeadless(false);
            }
            
            options.setAcceptInsecureCerts(true);
            options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
            options.addArguments("incognito");
            options.setAcceptInsecureCerts(true);

//            options.addArguments("--start-maximized");
//            options.addArguments("--start-fullscreen");

            System.setProperty("webdriver.chrome.silentOutput", "true");
            // this could be used to stop INFO logging in chrome driver
            java.util.logging.Logger.getLogger("org.openqa.selenium").setLevel(Level.OFF);

            return new ChromeDriver(options);
        } catch (Exception ex) {
            LOGGER.error("Cannot get Chrom Webdriver object.", ex);
            throw new TestException("Cannot get Chrom Webdriver object.", ex);
        }
    }



}
