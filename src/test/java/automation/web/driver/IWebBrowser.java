package automation.web.driver;

import org.openqa.selenium.WebDriver;

import java.util.function.Function;

public interface IWebBrowser {

    void active();
    void navigateTo(String url);
    void maximize();
    void resize(int width, int height);
    void back();
    void forward();
    void refresh();
    void clearBrowserCookies();
    boolean waitAjaxToLoad();

    // popup JS message
    boolean isAlertPresent();
    String getAlertText();
    void acceptAlert();
    void dismissAlert();

    String getUrl();
    String getTitle();
    String getPageText();
    String getPageDom();


    boolean isContainText(String textVal);

    Object executeScript(String script);

    Object executeAsyncScript(String script);

    String getCurrentWinHandle();
    void closeCurrentWindow();
    void switchToChildWindow();
    void switchToMainWindow();
    void switchToDefaultContent();
    <T extends Function<? super WebDriver, R>, R> R genericWait(T t);
    <T extends Function<? super WebDriver, R>, R> R genericWait(T t, long timeout);
}
