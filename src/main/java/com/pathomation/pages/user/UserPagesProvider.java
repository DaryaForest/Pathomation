package com.pathomation.pages.user;


public class UserPagesProvider {
    private static UsersPage usersPage;
    private static UsersPagePma2 usersPagePma2;
    private static EditUserPage editUserPage;
    private static AddUserPage addUserPage;
    private static AddUserPagePma2 addUserPagePma2;
    private static SummaryPage summaryPage;
    private static UserLogPage userLogPage;



    public static UsersPage usersPage() {
        if (usersPage == null) {
            usersPage = new UsersPage();
        }
        return usersPage;
    }

    public static UsersPagePma2 usersPagePma2() {
        if (usersPagePma2 == null) {
            usersPagePma2 = new UsersPagePma2();
        }
        return usersPagePma2;
    }

    public static EditUserPage editUserPage() {
        if (editUserPage == null) {
            editUserPage = new EditUserPage();
        }
        return editUserPage;
    }

    public static AddUserPage addUserPage() {
        if (addUserPage == null) {
            addUserPage = new AddUserPage();
        }
        return addUserPage;
    }

    public static AddUserPagePma2 addUserPagePma2() {
        if (addUserPagePma2 == null) {
            addUserPagePma2 = new AddUserPagePma2();
        }
        return addUserPagePma2;
    }
    public static SummaryPage summaryPage() {
        if (summaryPage == null) {
            summaryPage = new SummaryPage();
        }
        return summaryPage;
    }

    public static UserLogPage userLogPage() {
        if (userLogPage == null) {
            userLogPage = new UserLogPage();
        }
        return userLogPage;
    }
}
