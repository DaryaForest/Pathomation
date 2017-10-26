package com.pathomation.pages.responseapi;

import com.pathomation.base.BasePage;
import com.pathomation.locators.CSS;
import com.pathomation.locators.Locator;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;

public class GetDirectoryPage extends BasePage {

    Locator wsiDataContent = new CSS(".collapsible-content span.text");

    public ArrayList<String> getWSIDataContent() {

        ArrayList<String> wsiData = new ArrayList<>();

        for (WebElement webEl: getElements(wsiDataContent)) {
            wsiData.add(webEl.getText());
        }

        return wsiData;
    }

}
