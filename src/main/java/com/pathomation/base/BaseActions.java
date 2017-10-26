package com.pathomation.base;

import com.pathomation.actions.MainActions;
import com.pathomation.util.ConstantsPma2;
import com.pathomation.util.reporter.Reporter;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.Set;

import static com.pathomation.base.BaseTest.driver;

public class BaseActions {

    public static void waitForTabsCount(int minTabsCount) {
        Reporter.log("Waiting for >= " + minTabsCount + " tabs opened (up to " + ConstantsPma2.ELEMENT_EXTRALONG_TIMEOUT_SECONDS + " seconds)..");
        long timeBefore = System.currentTimeMillis();
        do {
            wait(ConstantsPma2.ELEMENT_MICRO_TIMEOUT_SECONDS);
        }
        while (driver.getWindowHandles().size() < minTabsCount && System.currentTimeMillis() - timeBefore <= ConstantsPma2.ELEMENT_EXTRALONG_TIMEOUT_SECONDS * 1000);

        Assert.assertTrue(driver.getWindowHandles().size() >= minTabsCount, "Number of opened tabs is not more or equals to " + minTabsCount);
    }

    public static void closeOtherTabs() {
        Reporter.log("Closing other browser tabs");
        String currentHandle = driver.getWindowHandle();
        Set<String> handles = driver.getWindowHandles();
        for (String handle : handles) {
            if (!handle.equals(currentHandle)) {
                driver.switchTo().window(handle);
                driver.close();
            }
        }
        driver.switchTo().window(currentHandle);
    }


    public static void wait(int waitInSeconds) {
        try {
            Thread.sleep(waitInSeconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void acceptAlert() {
        try {
            Reporter.log("Accept alert ");
            WebDriverWait wait = new WebDriverWait(driver, 3);
            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = driver.switchTo().alert();
            alert.accept();
            MainActions.wait(ConstantsPma2.ELEMENT_MICRO_TIMEOUT_SECONDS);

        } catch (Exception e) {
            System.out.println("Error with alert " + e.toString());
        }
    }
/*    public static void waitAlertIsPresent(int waitInSeconds) {
       (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.alertIsPresent());

    }*/
}
