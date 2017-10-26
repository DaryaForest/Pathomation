package com.pathomation.pages.content;

import com.pathomation.base.BasePage;

public class ContentPage extends BasePage {

    private static ContentHeaderPage contentHeaderPage;
    private static RootDirectoriesPage rootDirectoriesPage;
    private static AddNewRootDirectoryPage addNewRootDirectoryPage;

    public static ContentHeaderPage contentHeaderPage() {
        if (contentHeaderPage == null) {
            contentHeaderPage = new ContentHeaderPage();
        }
        return contentHeaderPage;
    }

    public static RootDirectoriesPage rootDirectoriesPage() {
        if (rootDirectoriesPage == null) {
            rootDirectoriesPage = new RootDirectoriesPage();
        }
        return rootDirectoriesPage;
    }

    public static AddNewRootDirectoryPage addNewRootDirectoryPage() {
        if (addNewRootDirectoryPage == null) {
            addNewRootDirectoryPage = new AddNewRootDirectoryPage();
        }
        return addNewRootDirectoryPage;
    }


}
