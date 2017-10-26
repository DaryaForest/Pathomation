package com.pathomation.pages.diagnostics;

public class DiagnosticsPages {

    private static InstallationChecksPage installationChecksPage;
    private static LogFilesPage logFilesPage;

    public static InstallationChecksPage installationChecksPage() {
        if (installationChecksPage == null) {
            installationChecksPage = new InstallationChecksPage();
        }
        return installationChecksPage;
    }

    public static com.pathomation.pages.diagnostics.LogFilesPage logFilesPage() {
        if (logFilesPage == null) {
            logFilesPage = new com.pathomation.pages.diagnostics.LogFilesPage();
        }
        return logFilesPage;
    }
}
