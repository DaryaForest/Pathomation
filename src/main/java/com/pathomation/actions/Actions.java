package com.pathomation.actions;

public class Actions {

    private static MainActions mainActions;
    private static LoginActions loginActions;
    private static RootDirectoryActions rootDirectoryActions;

    public static MainActions mainActions() {
        if (mainActions == null) {
            mainActions = new MainActions();
        }
        return mainActions;
    }

    public static LoginActions loginActions() {
        if (loginActions == null) {
            loginActions = new LoginActions();
        }
        return loginActions;
    }

    public static RootDirectoryActions rootDirectoryActions() {
        if (rootDirectoryActions == null) {
            rootDirectoryActions = new RootDirectoryActions();
        }
        return rootDirectoryActions;
    }


}

