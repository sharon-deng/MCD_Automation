package automation.web.driver;

import automation.utils.ConstantValue;
import automation.utils.PropertyUtil;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxDriverLogLevel;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

public class CapFirefox implements CapInterface {

    private FirefoxProfile profile = null;
    private FirefoxOptions options;

    public CapFirefox() {
        //
    }

//    @Override
    public WebDriver getAvailalbeDriver() {
        System.setProperty("webdriver.gecko.driver", getDriverPath());
        System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
        System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, ConstantValue.DRIVERS_FOLDER + "/logs/firefox_driver_log.txt");

        options = new FirefoxOptions();
        if (PropertyUtil.getBooleanVal("isSetupProxy")) {
            options.setProxy(getSeleniumProxy());
        }

        if (PropertyUtil.getBooleanVal("isHeadless")) {
            options.setHeadless(true);
        } else {
            options.setHeadless(false);
        }
        options.setAcceptInsecureCerts(true);
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        options.setLogLevel(FirefoxDriverLogLevel.INFO);


        //// Create and assign value to profile.
        profile = new FirefoxProfile();

//        if (PropertyValue.isLoadFirefoxDefaultProfile()) {
//            String profilePath = PropertyValue.firefoxProfilePath();
//            profile = new FirefoxProfile(new File(profilePath));
//        } else {
//            profile = new FirefoxProfile();
//        }

        profile.setAcceptUntrustedCertificates(true);
        profile.setAssumeUntrustedCertificateIssuer(false);
        profile.setPreference("browser.popups.showPopupBlocker", false);
//        profile.setPreference("browser.privatebrowsing.autostart", true);
        profile.setPreference("geo.enabled", false);
        profile.setPreference("geo.provider.use_corelocation", false);
        profile.setPreference("geo.prompt.testing", false);
        profile.setPreference("geo.prompt.testing.allow", false);
        profile.setPreference("dom.disable_open_during_load", false);

        options.setProfile(profile);
        // Return firefox driver.
        return new FirefoxDriver(options);
    }

}
