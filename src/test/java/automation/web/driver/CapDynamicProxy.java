package automation.web.driver;

import automation.utils.PropertyUtil;
import org.openqa.selenium.WebDriver;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class CapDynamicProxy {

    private String browserType = PropertyUtil.getStringVal("browserType");  // PropertyValue.browserType();
    private CapInterface subject;
    private CapInterface capInterface;
    private InvocationHandler handler;
    private ClassLoader loader;
    private Class[] interfaces;

    CapDynamicProxy() {
        subject = newCapProxy();
    }

    WebDriver getAvailalbeDriver() {
        subject = newCapProxy();
        return subject.getAvailalbeDriver();
    }

//    void quitDriver(WebDriver dr) {
//        subject.quitDriver(dr);
//    }

    void killWebDriverProcess() {
        subject.killWebDriverProcess();
    }

    void killTestBrowserProcess() {
        subject.killTestBrowserProcess();
    }

    private CapInterface newCapProxy() {
        if ("chrome".equalsIgnoreCase(browserType)) {
            capInterface = new CapChrome();
        } else if ("firefox".equalsIgnoreCase(browserType)) {
            capInterface = new CapFirefox();
        } else if ("ie".equalsIgnoreCase(browserType)) {
            capInterface = new CapIE();
        } else if ("edge".equalsIgnoreCase(browserType)) {
            capInterface = new CapEdge();
        } else {
            capInterface = new CapChrome();
        }

        handler = new CapDynamicSubject(capInterface);
        loader = Thread.currentThread().getContextClassLoader();
        interfaces = capInterface.getClass().getInterfaces();
        return (CapInterface) Proxy.newProxyInstance(loader, interfaces, handler);

    }
}
