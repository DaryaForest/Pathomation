package com.pathomation.actions.settings;


public class SettingsActions {
    private static ImageSettingsActions imageSettingsActions;

    public static ImageSettingsActions imageSettingsActions() {
        if (imageSettingsActions == null) {
            imageSettingsActions = new ImageSettingsActions();
        }
        return imageSettingsActions;
    }


}
