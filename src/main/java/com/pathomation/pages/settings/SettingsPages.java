package com.pathomation.pages.settings;

public class SettingsPages {

    private static LicensePage licensePage;
    private static RootDerictoriesPage rootDerictoriesPage;
    private static ImageSettingsPage imageSettingsPage;


    public static RootDerictoriesPage rootDerictoriesPage() {
        if (rootDerictoriesPage == null) {
            rootDerictoriesPage = new RootDerictoriesPage();
        }
        return rootDerictoriesPage;
    }

    public static LicensePage licensePage() {
        if (licensePage == null) {
            licensePage = new LicensePage();
        }
        return licensePage;
    }

    public static ImageSettingsPage imageSettingsPage() {
        if (imageSettingsPage == null) {
            imageSettingsPage = new ImageSettingsPage();
        }
        return imageSettingsPage;
    }
}
