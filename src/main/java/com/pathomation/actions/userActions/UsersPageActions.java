package com.pathomation.actions.userActions;

import com.pathomation.base.BaseActions;
import com.pathomation.pages.user.UserPagesProvider;
import com.pathomation.util.data.User;
import com.pathomation.util.reporter.Reporter;

public class UsersPageActions extends BaseActions {
    public boolean deleteUser(User user) {
        boolean isUserSuccessfullyDeleted = false;
        //if count of pages > 1, then do search on all of them; -2 because of 'Previous'  and 'Next' buttons
        int counOfUsersPages = UserPagesProvider.usersPage().countOfPaginateButtons() - 2;
        if (counOfUsersPages > 1) {
            for (int i = 1; i <= counOfUsersPages; i++) {
                if (UserPagesProvider.usersPage().isUserLoginPresentInTheLoginColumn(user.getLogin())) {
                    UserPagesProvider.usersPage().clickDeleteUserButton(user.getLogin());
                    BaseActions.acceptAlert();
/*                    new WebDriverWait(driver, 20)
                            .ignoring(NoAlertPresentException.class)
                            .until(ExpectedConditions.alertIsPresent());
                    driver.switchTo().alert().accept();*/
                    isUserSuccessfullyDeleted = true;
                    break;
                } else if (!UserPagesProvider.usersPage().isNextButtonDisabled()) {
                    UserPagesProvider.usersPage().clickNextButton();
                    isUserSuccessfullyDeleted = true;
                }

            }
        } else if (UserPagesProvider.usersPage().isUserLoginPresentInTheLoginColumn(user.getLogin())) {
            UserPagesProvider.usersPage().clickDeleteUserButton(user.getLogin());
            BaseActions.acceptAlert();
            isUserSuccessfullyDeleted = true;

        } else {
            isUserSuccessfullyDeleted = true;
            org.testng.Reporter.log("Delete was failed. User " + user.getLogin() + " isn't in Users table");

        }
        return isUserSuccessfullyDeleted;
    }

    public boolean checkUserAdded(User user) {
        Reporter.log("Check user info present in the list on 'Users page'");
        boolean userSuccesfullyAdded = false;
        //if count of pages > 1, then do search on all of them; -2 because of 'Previous'  and 'Next' buttons
        int countOfUsersPages = UserPagesProvider.usersPage().countOfPaginateButtons() - 2;
        if (countOfUsersPages > 1) {
            for (int i = 1; i <= countOfUsersPages; i++) {
                boolean check = UserPagesProvider.usersPage().isUserLoginPresentInTheLoginColumn(user.getLogin());
                if (check) {
                    userSuccesfullyAdded = UserPagesProvider.usersPage().isUserLoginPresentInTheLoginColumn(user.getLogin());
                    userSuccesfullyAdded = UserPagesProvider.usersPage().isUserFNamePresentInTheFNameColumn(user.getFirstName());
                    userSuccesfullyAdded = UserPagesProvider.usersPage().isUserLNamePresentInTheLNameColumn(user.getLastName());
                    userSuccesfullyAdded = UserPagesProvider.usersPage().isUserEmailPresentInTheEmailColumn(user.getEmail());
                    if (user.isAdmin())
                        userSuccesfullyAdded = UserPagesProvider.usersPage().isAdminByLogin(user.getLogin());
                    if (user.isSuspended())
                        userSuccesfullyAdded = UserPagesProvider.usersPage().isSuspendedByLogin(user.getLogin());
                    if (user.isCanAnotate())
                        userSuccesfullyAdded = UserPagesProvider.usersPage().isCanAnnotateByLogin(user.getLogin());

                    userSuccesfullyAdded = true;
                    break;
                } else if (!UserPagesProvider.usersPage().isNextButtonDisabled()) {
                    UserPagesProvider.usersPage().clickNextButton();
                }

            }
        } else if (UserPagesProvider.usersPage().isUserLoginPresentInTheLoginColumn(user.getLogin())) {
            userSuccesfullyAdded = UserPagesProvider.usersPage().isUserLoginPresentInTheLoginColumn(user.getLogin());
            userSuccesfullyAdded = UserPagesProvider.usersPage().isUserFNamePresentInTheFNameColumn(user.getFirstName());
            userSuccesfullyAdded = UserPagesProvider.usersPage().isUserLNamePresentInTheLNameColumn(user.getLastName());
            userSuccesfullyAdded = UserPagesProvider.usersPage().isUserEmailPresentInTheEmailColumn(user.getEmail());
            if (user.isAdmin())
                userSuccesfullyAdded = UserPagesProvider.usersPage().isAdminByLogin(user.getLogin());
            if (user.isSuspended())
                userSuccesfullyAdded = UserPagesProvider.usersPage().isSuspendedByLogin(user.getLogin());
            if (user.isCanAnotate())
                userSuccesfullyAdded = UserPagesProvider.usersPage().isCanAnnotateByLogin(user.getLogin());
            userSuccesfullyAdded = true;

        } else {
            userSuccesfullyAdded = false;
            Reporter.log("Added was failed. User " + user.getLogin() + " isn't in Users table");


        }

        return userSuccesfullyAdded;
    }
}
