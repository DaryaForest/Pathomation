package com.pathomation.actions.userActions;


import com.pathomation.base.BaseActions;
import com.pathomation.pages.user.UserPagesProvider;
import com.pathomation.util.data.User;

public class AddUserActions extends BaseActions {
    public void fillAddUserForm(User user) {
        UserPagesProvider.addUserPage().waitFirstNameField();
        UserPagesProvider.addUserPage().setFirstName(user.getFirstName());
        UserPagesProvider.addUserPage().setLastName(user.getFirstName());
        UserPagesProvider.addUserPage().setLogin(user.getLogin());
        UserPagesProvider.addUserPage().setPassword(user.getPass());
        UserPagesProvider.addUserPage().setConfirmPassword(user.getPass());
        UserPagesProvider.addUserPage().setEmail(user.getEmail());
        if(user.isAdmin()) {
            UserPagesProvider.addUserPage().setCheckedAdminCheckBoxState(true);}
        wait(5);
        if(user.isSuspended()){UserPagesProvider.addUserPage().setCheckedSuspendedCheckBoxState(true);}
        wait(5);
        if(user.isCanAnotate()){UserPagesProvider.addUserPage().setCheckedCanAnnotateCheckBoxState(true);}


    }
}
