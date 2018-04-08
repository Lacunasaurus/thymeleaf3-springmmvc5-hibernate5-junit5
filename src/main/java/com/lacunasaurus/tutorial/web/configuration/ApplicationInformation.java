package com.lacunasaurus.tutorial.web.configuration;

import com.lacunasaurus.tutorial.entities.UserAccount;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

@Component("applicationInformation")
@ApplicationScope
public class ApplicationInformation {

    Map<String, UserAccount> userAccountList;

    public ApplicationInformation() {
        userAccountList = new HashMap<>();
        userAccountList.put("login1", new UserAccount("email1@domain.com", "login1", "password1"));
        userAccountList.put("login2", new UserAccount("email2@domain.com", "login2", "password2"));
        userAccountList.put("login3", new UserAccount("email3@domain.com", "login3", "password3"));
    }

    public Map<String, UserAccount> getUserAccountList() {
        return userAccountList;
    }

    public void setUserAccountList(Map<String, UserAccount> userAccountList) {
        this.userAccountList = userAccountList;
    }
    
    

}
