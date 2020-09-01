package automation.testcases;

import automation.utils.CaseBase;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class GotoSite1 extends CaseBase {

    @Test(description = "GotoSite1")

    public void goToSite1() {
        driver.get("https://poc15c-aks-ingress.southeastasia.cloudapp.azure.com/?origin=self");
        driver.manage().window().setSize(new Dimension(1925, 1052));

        logout();

        login("user", "Pass123$");
        //Thread.sleep(5000);
        WebElement element = driver.findElement(By.tagName("body"));
        Actions builder = new Actions(driver);
        builder.moveToElement(element, 0, 0).perform();
       // Thread.sleep(5000);
        logout();

    }
}
