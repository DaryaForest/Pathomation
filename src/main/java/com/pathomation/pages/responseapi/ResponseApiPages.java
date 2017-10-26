package com.pathomation.pages.responseapi;

public class ResponseApiPages {

    private static MainPage mainPage;
    private static VersionNumberPage versionNumberPage;
    private static VersionNumberJSONPage versionNumberJSONPage;
    private static AuthenticatePage authenticatePage;
    private static GetRootDirectoryPage getRootDirectoryPage;
    private static GetDirectoryPage getDirectoryPage;
    private static GetFilesPage getFilesPage;
    private static GetUIDPage getUIDPage;

    public static MainPage mainPage() {
        if (mainPage == null){
            mainPage = new MainPage();
        }
        return mainPage;
    }

    public static VersionNumberPage versionNumberPage() {
        if (versionNumberPage == null){
            versionNumberPage = new VersionNumberPage();
        }
        return versionNumberPage;
    }

    public static VersionNumberJSONPage versionNumberJSONPage() {
        if (versionNumberJSONPage == null){
            versionNumberJSONPage = new VersionNumberJSONPage();
        }
        return versionNumberJSONPage;
    }

    public static AuthenticatePage authenticatePage() {
        if (authenticatePage == null){
            authenticatePage = new AuthenticatePage();
        }
        return authenticatePage;
    }

    public static GetRootDirectoryPage getRootDirectoryPage() {
        if (getRootDirectoryPage == null){
            getRootDirectoryPage = new GetRootDirectoryPage();
        }
        return getRootDirectoryPage;
    }

    public static GetDirectoryPage getDirectoryPage() {
        if (getDirectoryPage == null){
            getDirectoryPage = new GetDirectoryPage();
        }
        return getDirectoryPage;
    }

    public static GetFilesPage getFilesPage() {
        if (getFilesPage == null){
            getFilesPage = new GetFilesPage();
        }
        return getFilesPage;
    }

    public static GetUIDPage getUIDPage() {
        if (getUIDPage == null){
            getUIDPage = new GetUIDPage();
        }
        return getUIDPage;
    }

}
