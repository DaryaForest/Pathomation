package com.pathomation.pages;


public class Pages {

    private static LoginPage loginPage;
    private static NavigationBarPage navigationBarPage;
    private static LeftSideBarPage leftSideBarPage;
    private static ImagesPage imagesPage;
    private static HomePage homePage;

    public static LoginPage loginPage() {
        if (loginPage == null) {
            loginPage = new LoginPage();
        }
        return loginPage;
    }

    public static HomePage homePage() {
        if (homePage == null) {
            homePage = new HomePage();
        }
        return homePage;
    }

    public static NavigationBarPage navigationBarPage() {
        if (navigationBarPage == null) {
            navigationBarPage = new NavigationBarPage();
        }
        return navigationBarPage;
    }

    public static LeftSideBarPage leftSideBarPage() {
        if (leftSideBarPage == null) {
            leftSideBarPage = new LeftSideBarPage();
        }
        return leftSideBarPage;
    }



    public static ImagesPage imagesPage() {
        if (imagesPage == null) {
            imagesPage = new ImagesPage();
        }
        return imagesPage;
    }
}
