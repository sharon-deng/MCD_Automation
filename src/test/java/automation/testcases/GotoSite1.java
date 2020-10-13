package automation.testcases;

import automation.utils.CaseBase;
import automation.utils.Verify;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;


public class GotoSite1 extends CaseBase {

    String UserName;
    String Password = "Pass123$";
    String URL ="https://poc15c-aks-ingress.southeastasia.cloudapp.azure.com/?origin=self";

    @Test(description = "GotoSite1")

    public void goToSite1() throws InterruptedException {
        driver.get(URL);
        driver.manage().window().setSize(new Dimension(1925, 1052));

        logout();
        UserName = "user";
        login(UserName, Password);
        System.out.println("username: "+UserName+" Login Successful");
        Thread.sleep(5000);
        WebElement element = driver.findElement(By.tagName("body"));
        Actions builder = new Actions(driver);
        builder.moveToElement(element, 0, 0).perform();
       // Thread.sleep(5000);
        logout();
        System.out.println("username: "+UserName+" Logout Successful");


    }
}
