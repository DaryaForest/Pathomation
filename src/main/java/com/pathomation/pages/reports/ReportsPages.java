package com.pathomation.pages.reports;


public class ReportsPages {

    private static ActiveSessionsPage activeSessionsPage;


    public static ActiveSessionsPage activeSessionsPage() {
        if (activeSessionsPage == null) {
            activeSessionsPage = new ActiveSessionsPage();
        }
        return activeSessionsPage;
    }
}
