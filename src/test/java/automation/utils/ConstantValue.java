package automation.utils;

public class ConstantValue {

    //// For framework using.
    public static final String ROOT_FOLDER = System.getProperty("user.dir");
    public static final String USER_NAME = System.getProperty("user.name");

    public static final String MAIN_RESOURCES_FOLDER = ROOT_FOLDER + "/src/main/resources/";
    public static final String TEST_RESOURCES_FOLDER = ROOT_FOLDER + "/src/test/resources/";

    public static final String PROPERTIES_FOLDER = TEST_RESOURCES_FOLDER + "properties/";
    public static final String JSON_DATA_FOLDER = TEST_RESOURCES_FOLDER +"data/";
    public static final String TEST_DATA_FOLDER = TEST_RESOURCES_FOLDER + "data/";
    
    public static final String DRIVERS_FOLDER = ROOT_FOLDER+"/libs/drivers/";
    public static final String HTML_REPORT_FOLDER = ROOT_FOLDER+"/allure-report/html/";
    public static final String LOGS_FOLDER = ROOT_FOLDER+"/logs/";
    public static final String SAMPLES_FOLDER = ROOT_FOLDER+"/samples/";
    public static final String AI_REPORT_FOLDER = ROOT_FOLDER+"/AI_Report/";

    //  The Constant CONFIG_FILE.
    public static final String CONFIG_PROPERTIES_PATH = PROPERTIES_FOLDER+"config.properties";
    public static final String RUNTIME_PROPERTIES_PATH = PROPERTIES_FOLDER+"runtime.properties";
    public static final String ANDROID_BROWSER_PROPERTIES_PATH = PROPERTIES_FOLDER+"android.browser.properties";
    public static final String ANDROID_APP_PROPERTIES_PATH = PROPERTIES_FOLDER+"android.app.properties";
    public static final String IOS_BROWSER_PROPERTIES_PATH = PROPERTIES_FOLDER+"ios.browser.properties";
    public static final String IOS_APP_PROPERTIES_PATH = PROPERTIES_FOLDER+"ios.app.properties";
    
    public static final String WINDOWS_APP_PROPERTIES_PATH = PROPERTIES_FOLDER+"windows.app.properties";
}
