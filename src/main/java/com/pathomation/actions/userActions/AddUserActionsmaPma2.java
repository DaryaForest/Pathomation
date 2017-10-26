package com.pathomation.actions.userActions;


import com.pathomation.base.BaseActions;
import com.pathomation.pages.user.UserPagesProvider;
import com.pathomation.util.data.User;

public class AddUserActionsmaPma2 extends BaseActions {
    public void fillAddUserForm(User user) {
        UserPagesProvider.addUserPagePma2().waitFirstNameField();
        UserPagesProvider.addUserPagePma2().setFirstName(user.getFirstName());
        UserPagesProvider.addUserPagePma2().setLastName(user.getFirstName());
        UserPagesProvider.addUserPagePma2().setLogin(user.getLogin());
        UserPagesProvider.addUserPagePma2().setPassword(user.getPass());
        UserPagesProvider.addUserPagePma2().setConfirmPassword(user.getPass());
        UserPagesProvider.addUserPagePma2().setEmail(user.getEmail());
        if(user.isAdmin()) UserPagesProvider.addUserPagePma2().setAdministrator();
        if(user.isSuspended()) UserPagesProvider.addUserPagePma2().setSuspended();
        if(user.isCanAnotate()) UserPagesProvider.addUserPagePma2().setCanAnnotate();


    }
}
