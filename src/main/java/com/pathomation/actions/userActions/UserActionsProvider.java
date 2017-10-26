package com.pathomation.actions.userActions;


public class UserActionsProvider {
    private static AddUserActions addUserActions;
    private static AddUserActionsmaPma2 addUserActionsmaPma2;
    private static UsersPageActions usersPageActions;
    private static UsersPageActionsPma2 usersPageActionsPma2;

    public static AddUserActions addUserActions() {
        if (addUserActions == null) {
            addUserActions = new AddUserActions();
        }
        return addUserActions;
    }

    public static AddUserActionsmaPma2 addUserActionsmaPma2() {
        if (addUserActionsmaPma2 == null) {
            addUserActionsmaPma2 = new AddUserActionsmaPma2();
        }
        return addUserActionsmaPma2;
    }

    public static UsersPageActions usersPageActions() {
        if (usersPageActions == null) {
            usersPageActions = new UsersPageActions();
        }
        return usersPageActions;
    }
    public static UsersPageActionsPma2 usersPageActionsPma2() {
        if (usersPageActionsPma2 == null) {
            usersPageActionsPma2 = new UsersPageActionsPma2();
        }
        return usersPageActionsPma2;
    }
}
