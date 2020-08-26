package automation.web.driver;

import automation.utils.PropertyUtil;
import automation.utils.ConstantValue;
import automation.utils.OsUtil;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.testng.TestException;
import java.io.IOException;

public interface CapInterface {

    WebDriver getAvailalbeDriver();


    default String getDriverPath() {
        return ConstantValue.DRIVERS_FOLDER + getDriverProcessName();
    }

    
    default String getDriverProcessName() {
        String processName = "null";
        String browserType = PropertyUtil.getStringVal("browserType").toLowerCase();

        switch(browserType) {
            case "chrome":
                if (OsUtil.isWinOS()) {
                    processName = PropertyUtil.getStringVal("chromeDriverPath");
                } else if (OsUtil.isMacOS()) {
                    processName = PropertyUtil.getStringVal("macChromeDriverPath");
                } else if (OsUtil.isUnixOS())
                    processName = PropertyUtil.getStringVal("linuxChromeDriverPath");
                break;
            case "firefox":
                if (OsUtil.isWinOS()) {
                    processName = PropertyUtil.getStringVal("geckoDriverPath");
                } else if (OsUtil.isMacOS()) {
                    processName = PropertyUtil.getStringVal("macGeckoDriverPath");
                } else if (OsUtil.isUnixOS())
                    processName = PropertyUtil.getStringVal("linuxGeckoDriverPath");
                break;
            case "safari":
                processName = "safaridriver";
                break;
            case "ie":
                processName = PropertyUtil.getStringVal("IEDriverPath");
                break;
            case "edge":
                processName = PropertyUtil.getStringVal("EdgeDriverPath");
                break;
        }
        return processName;
    }

    default String getBrowserProcessName() {
        String processName = "null";
        String browserType = PropertyUtil.getStringVal("browserType").toLowerCase();

        switch (browserType){
            case "chrome":
                if (OsUtil.isWinOS()) {
                    processName = "chrome.exe";
                } else {
                    processName = "Google Chrome";
                }
                break;
            case "firefox":
                if (OsUtil.isWinOS()) {
                    processName = "firefox.exe";
                } else {
                    processName = "Firefox";
                }
                break;
            case "safari":
                processName = "Safari";
                break;
            case "ie":
                processName = "iexplore.exe";
                break;
            case "edge":
                processName = "edge.exe";
                break;
        }
        return processName;
    }

    default void killWebDriverProcess() {
        String driverProcessName = getDriverProcessName();
        try {
            Runtime rt = Runtime.getRuntime();
            if (OsUtil.isWinOS()) {
                rt.exec("taskkill " + driverProcessName);
            } else {
                rt.exec("pkill " + driverProcessName);
            }
        } catch (IOException ex) {
            throw new TestException("killWebDriverProcess - : " + driverProcessName, ex);
        }
    }

    default void killTestBrowserProcess() {
        String browserProcessName = getBrowserProcessName();
        try {
            Runtime rt = Runtime.getRuntime();
            if (OsUtil.isWinOS()) {
                rt.exec("taskkill " + browserProcessName);
            } else {
                rt.exec("pkill " + browserProcessName);
            }
        } catch (IOException ex) {
            throw new TestException("killTestBrowserProcess - : " + browserProcessName, ex);
        }
    }


    default Proxy getSeleniumProxy() {
        Proxy proxy = new Proxy();
        proxy.setHttpProxy(PropertyUtil.getStringVal("proxyAddress"))
                .setFtpProxy(PropertyUtil.getStringVal("proxyAddress"))
                .setSslProxy(PropertyUtil.getStringVal("proxyAddress"))
                .setNoProxy(PropertyUtil.getStringVal("excludedProxy"));
        if (PropertyUtil.getStringVal("proxyUsername").length()>0){
            proxy.setSocksUsername(PropertyUtil.getStringVal("proxyUsername"))
                    .setSocksPassword(PropertyUtil.getStringVal("proxyPassword"));
        }
        return proxy;
    }

}
