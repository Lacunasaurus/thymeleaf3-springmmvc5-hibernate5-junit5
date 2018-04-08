package com.lacunasaurus.tutorial.web.controllers.authentication;

import com.lacunasaurus.tutorial.entities.UserAccount;
import com.lacunasaurus.tutorial.web.configuration.ApplicationInformation;
import com.lacunasaurus.tutorial.web.models.SignUpModel;
import com.lacunasaurus.tutorial.web.validator.SignUpValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/sign-up", "/inscription"})
public class SignUpController {

    @Autowired
    private ApplicationInformation applicationInformation;

    @Autowired
    SignUpValidator signUpValidator;

    @GetMapping
    public String navigate() {
        return "pages/authentication/sign-up";
    }

    @ModelAttribute("signUpModel")
    public SignUpModel setDefaultSignUpData() {
        return new SignUpModel();
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(signUpValidator);
    }

    @PostMapping
    public String createAccount(@Validated SignUpModel signUpModel, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "pages/authentication/sign-up";
        }
        
        UserAccount userAccount = new UserAccount(signUpModel.getEmail(), signUpModel.getPseudo(), signUpModel.getPassword());
        applicationInformation.getUserAccountList().put(userAccount.getPseudo(), userAccount);
        return "redirect:/login";
    }
}
