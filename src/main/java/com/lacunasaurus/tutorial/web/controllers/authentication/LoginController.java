package com.lacunasaurus.tutorial.web.controllers.authentication;

import com.lacunasaurus.tutorial.entities.UserAccount;
import com.lacunasaurus.tutorial.web.configuration.ApplicationInformation;
import com.lacunasaurus.tutorial.web.models.LoginModel;
import com.lacunasaurus.tutorial.web.validator.LoginValidator;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @Autowired
    LoginValidator loginValidator;
    
    @Autowired
    HttpSession session;
    
    @Autowired
    private ApplicationInformation applicationInformation;

    @GetMapping({"/login", "/connexion"})
    public String navigate() {
        return "pages/authentication/login";
    }

    @ModelAttribute("loginModel")
    public LoginModel setDefaultLoginData() {
        return new LoginModel();
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(loginValidator);
    }

    @PostMapping({"/login", "/connexion"})
    public String connect(@Validated LoginModel loginModel, BindingResult bindingResult) {

        UserAccount userAccount = applicationInformation.getUserAccountList().get(loginModel.getPseudo());
        if (bindingResult.hasErrors() || userAccount == null) {
            return "pages/authentication/login";
        }
        
        if(userAccount.getPassword().equalsIgnoreCase(loginModel.getPassword())) {
            session.setAttribute("userAccount", userAccount);
            return "pages/website/home";
        }
        
        return "pages/authentication/login";
    }
}
