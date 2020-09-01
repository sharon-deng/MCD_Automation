package automation.testcases;

import automation.utils.CaseBase;
import automation.utils.Verify;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class GotoSite2 extends CaseBase {

    @Test(description = "GotoSite2")
    public void goToSite2() {
        driver.get("https://poc15c-aks-ingress.southeastasia.cloudapp.azure.com/?origin=self");
        driver.manage().window().setSize(new Dimension(1925, 1052));

        logout();

        login("user", "Pass123$");

        WebElement element = driver.findElement(By.tagName("body"));
        Actions builder = new Actions(driver);
        builder.moveToElement(element, 0, 0).perform();

        logout();

    }
}
