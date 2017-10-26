package com.pathomation.actions.userActions;

import com.pathomation.base.BaseActions;
import com.pathomation.pages.user.UserPagesProvider;
import com.pathomation.util.data.User;
import com.pathomation.util.reporter.Reporter;

import static com.pathomation.base.BaseTest.driver;

public class UsersPageActionsPma2 extends BaseActions {


    public boolean deleteUser(User user) {
        boolean isUserSuccessfullyDeleted = false;
        //if count of pages > 1, then do search on all of them; -2 because of 'Previous'  and 'Next' buttons
        int counOfUsersPages = UserPagesProvider.usersPagePma2().countOfPaginateButtons() - 2;
        if (counOfUsersPages > 1) {
            for (int i = 1; i <= counOfUsersPages; i++) {
                if (UserPagesProvider.usersPagePma2().isUserLoginPresentInTheLoginColumn(user.getLogin())) {
                    UserPagesProvider.usersPagePma2().clickDeleteUserButton(user.getLogin());
                    //  MainActions.wait(20);
                    driver.switchTo().alert().accept();
                    isUserSuccessfullyDeleted = true;
                    break;
                } else if (!UserPagesProvider.usersPagePma2().isNextButtonDisabled()) {
                    UserPagesProvider.usersPagePma2().clickNextButton();
                    isUserSuccessfullyDeleted = true;
                }

            }
        } else if (UserPagesProvider.usersPagePma2().isUserLoginPresentInTheLoginColumn(user.getLogin())) {
            UserPagesProvider.usersPagePma2().clickDeleteUserButton(user.getLogin());
            //  MainActions.wait(20);
            driver.switchTo().alert().accept();
            isUserSuccessfullyDeleted = true;

        } else {
            isUserSuccessfullyDeleted = true;
            org.testng.Reporter.log("Delete was failed. User " + user.getLogin() + " isn't in Users table");

        }
        return isUserSuccessfullyDeleted;
    }

    public boolean checkUserInfoPresentInTheTable(User user) {
        boolean checkedUsedAdded = false;
        checkedUsedAdded = UserPagesProvider.usersPagePma2().isUserLoginPresentInTheLoginColumn(user.getLogin());
        Reporter.log("isUserLoginPresentInTheLoginColumn" + checkedUsedAdded);
        checkedUsedAdded = UserPagesProvider.usersPagePma2().isUserFNamePresentInTheFNameColumn(user.getFirstName());
        Reporter.log("isUserFNamePresentInTheFNameColumn" + checkedUsedAdded);

        checkedUsedAdded = UserPagesProvider.usersPagePma2().isUserLNamePresentInTheLNameColumn(user.getLastName());
        Reporter.log("isUserLNamePresentInTheLNameColumn" + checkedUsedAdded);

        checkedUsedAdded = UserPagesProvider.usersPagePma2().isUserEmailPresentInTheEmailColumn(user.getEmail());
        Reporter.log("isUserEmailPresentInTheEmailColumn" + checkedUsedAdded);

        if (user.isAdmin()){
            checkedUsedAdded = UserPagesProvider.usersPagePma2().isAdminCheckBoxByLoginChecked(user.getLogin());
        Reporter.log("isAdminCheckBoxByLoginChecked" + checkedUsedAdded);}

        if (user.isSuspended()){
            checkedUsedAdded = UserPagesProvider.usersPagePma2().isSuspendedCheckBoxByLoginSelected(user.getLogin());
        Reporter.log("isSuspendedCheckBoxByLoginSelected" + checkedUsedAdded);}

        if (user.isCanAnotate()){
            checkedUsedAdded = UserPagesProvider.usersPagePma2().isCanAnotateCheckBoxByLoginChecked(user.getLogin());
        Reporter.log("isCanAnotateCheckBoxByLoginChecked" + checkedUsedAdded);}

        return checkedUsedAdded;

    }

    public boolean checkUserAdded(User user) {
        boolean checkedUsedAdded = false;

        Reporter.log("Check user info present in the list on 'Users page'");

        //if count of pages > 1, then do search on all of them; -2 because of 'Previous'  and 'Next' buttons
        int counOfUsersPages = UserPagesProvider.usersPagePma2().countOfPaginateButtons() - 2;
        if (counOfUsersPages > 1) {
            for (int i = 1; i <= counOfUsersPages; i++) {
                if (UserPagesProvider.usersPagePma2().isUserLoginPresentInTheLoginColumn(user.getLogin())) {
                    checkedUsedAdded = checkUserInfoPresentInTheTable(user);
                    Reporter.log("Here exist");

                    return checkedUsedAdded;
                } else if (!UserPagesProvider.usersPagePma2().isNextButtonDisabled()) {
                    UserPagesProvider.usersPagePma2().clickNextButton();
                }

            }
        } else if (UserPagesProvider.usersPagePma2().isUserLoginPresentInTheLoginColumn(user.getLogin())) {

            checkedUsedAdded = checkUserInfoPresentInTheTable(user);
            return checkedUsedAdded;


        }
        return checkedUsedAdded;


    }
}
