package com.pathomation.actions;

import com.pathomation.base.BaseActions;
import com.pathomation.pages.content.ContentPage;
import com.pathomation.util.data.RootDirectory;

public class RootDirectoryActions extends BaseActions {


    /***
     *
     * @param rootDirectory
     * @param truePath - if false, set incorrect path if true, set correct path
     */
    public void addNewRootDirectory(RootDirectory rootDirectory, boolean truePath) {
        ContentPage.addNewRootDirectoryPage().waitForPathField();
        if(!truePath) ContentPage.addNewRootDirectoryPage().setPathField(rootDirectory.getIncorrectPath());
        else ContentPage.addNewRootDirectoryPage().setPathField(rootDirectory.getCorrectPath());
        ContentPage.addNewRootDirectoryPage().setAliasField(rootDirectory.getAlias());
        ContentPage.addNewRootDirectoryPage().setUserNameField(rootDirectory.getUsername());
        ContentPage.addNewRootDirectoryPage().setPasswordField(rootDirectory.getPassword());
        ContentPage.addNewRootDirectoryPage().setDomainField(rootDirectory.getDomainName());
        ContentPage.addNewRootDirectoryPage().setVisibilityState(rootDirectory.getVisibilityState());

        ContentPage.addNewRootDirectoryPage().clickCreateButton();
    }

}
