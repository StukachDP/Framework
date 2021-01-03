package com.epam.framework.test;

import com.epam.framework.model.User;
import com.epam.framework.page.MainPage;
import com.epam.framework.service.UserCreator;
import org.testng.Assert;
import org.testng.annotations.Test;


public class UserAccessTests extends CommonConditions{

    @Test
    public void testUserCanSignIn() {
        User testUser = UserCreator.withCredentialsFromProperty();
        String  logedInUserEmail = new MainPage(driver)
                .openPage()
                .signIn(testUser)
                .getAuthorizedUserEmail();
        Assert.assertEquals(logedInUserEmail, testUser.getEmail());
    }

}
