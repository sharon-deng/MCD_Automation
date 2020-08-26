package automation.web.driver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.TestException;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Date;


public class CapDynamicSubject implements InvocationHandler{
    /**
     * 
     */
    private static final Logger LOGGER = LogManager.getLogger();
    Object subject;
    
    public CapDynamicSubject(Object sub) {  
        subject = sub;
    }  
    
        
    @Override    
    public Object invoke(Object proxy, Method method, Object[] args){
        try {
            before();
            Object result = method.invoke(subject, args);
            after();
            return result;
        } catch (Exception ex) {
            LOGGER.error("Innoke incorrect: " + ex.getMessage());
            throw new TestException("Cannot get Chrom Webdriver object.", ex);
        }
    }


    private void before() {
        System.out.println(String.format("log start time [%s] ", new Date()));
    }


    private void after() {
        System.out.println(String.format("log end time [%s] ", new Date()));
    }
}
