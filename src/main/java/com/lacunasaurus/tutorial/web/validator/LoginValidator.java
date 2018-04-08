package com.lacunasaurus.tutorial.web.validator;

import com.lacunasaurus.tutorial.web.models.LoginModel;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class LoginValidator extends TutorialValidator implements Validator {

    @Override
    public boolean supports(Class<?> type) {

        return LoginModel.class.equals(type);
    }

    @Override
    public void validate(Object targetObject, Errors errors) {
        
        ValidationUtils.rejectIfEmpty(errors, "pseudo", "form.createaccount.pseudo.notempty");
        ValidationUtils.rejectIfEmpty(errors, "password", "form.createaccount.password.notempty");
    }
}
