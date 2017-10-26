package com.pathomation.actions;

import com.pathomation.base.BaseActions;
import com.pathomation.util.ConstantsPma2;
import com.pathomation.util.reporter.Reporter;

import static com.pathomation.base.BaseTest.driver;

public class MainActions extends BaseActions {

    public void openMainPage(){
        Reporter.log("Opening main page: " + ConstantsPma2.BASE_URL);
        driver.get(ConstantsPma2.BASE_URL);
    }

    public void clearSession() {
        driver.manage().deleteAllCookies();
    }

    public void openPage(String url){
        Reporter.log("Opening page: " + url);
        driver.get(url);
    }
}
