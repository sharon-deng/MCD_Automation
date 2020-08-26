package automation.utils;

import automation.web.driver.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

public abstract class CaseBase {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final int DEFAULT_TIMEOUT = PropertyUtil.getIntegerVal("defaultWaitTime");
    private String testMethodName;
    private String testDesc;

    protected WebDriver driver;
    protected JavascriptExecutor js;
    protected Wait<WebDriver> defaultWait;
    protected Wait<WebDriver> shortWait;

    @BeforeMethod
    protected void beforeMethod(Method method, Object[] testData){
        Test test = method.getAnnotation(Test.class);
        testDesc = test.description();

        WebDriverManager.newDriver();
        driver = WebDriverManager.getDriver();
        driver.manage().timeouts().implicitlyWait(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        js = (JavascriptExecutor) WebDriverManager.getDriver();
        defaultWait = new WebDriverWait(driver, DEFAULT_TIMEOUT).ignoring(StaleElementReferenceException.class);
        shortWait = new WebDriverWait(driver, 5).ignoring(StaleElementReferenceException.class);
    }


    @AfterMethod
    protected void afterMethod(ITestResult result){

        // Do snapshot first.

        if (result.getStatus()!=1){
            LOGGER.error("Test case is failed: " + testDesc);
            Snapshot.attachDriverSnapShot("Test case is failed: " + testDesc);
        } else {
            LOGGER.info("Test case is passed: " + testDesc);
            Snapshot.attachDriverSnapShot("Test case is passed: " + testDesc);
        }

        // Quit the driver
        WebDriverManager.quitDriver();
    }

    protected void login(String userName, String pwd){
        driver.findElement(By.id("email")).sendKeys(userName);
        driver.findElement(By.id("password")).sendKeys(pwd);
        driver.findElement(By.xpath("//button/span")).click();
        Verify.assertTrue(shortWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[text()='Werribee']"))) != null, "Verify Login.");
    }

    protected void logout(){
        try{
            By byIcon = By.xpath("(//*[@id='root']//button[@type='button' and @aria-haspopup='true'])[2]");
            By byLogOut = By.xpath("//*[@id='menu-appbar']//li[normalize-space(text())='Log Out']");
            WebElement elementIcon = shortWait.until(ExpectedConditions.elementToBeClickable(byIcon));
            if (elementIcon!=null){
                elementIcon.click();
                driver.findElement(byLogOut).click();
                LOGGER.info("Logout system.");
            }
        } catch (WebDriverException ex){
            LOGGER.warn("Cannot logout due to NOT login yet.");
        }
    }

}
