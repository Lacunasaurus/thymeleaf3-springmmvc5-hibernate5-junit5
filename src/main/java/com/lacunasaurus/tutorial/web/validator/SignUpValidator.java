package com.lacunasaurus.tutorial.web.validator;

import com.lacunasaurus.tutorial.web.models.SignUpModel;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class SignUpValidator extends TutorialValidator implements Validator {

    @Override
    public boolean supports(Class<?> type) {

        return SignUpModel.class.equals(type);
    }

    @Override
    public void validate(Object targetObject, Errors errors) {

        SignUpModel signUpModel = (SignUpModel) targetObject;

        ValidationUtils.rejectIfEmpty(errors, "email", "form.createaccount.email.notempty");
        ValidationUtils.rejectIfEmpty(errors, "pseudo", "form.createaccount.pseudo.notempty");
        ValidationUtils.rejectIfEmpty(errors, "password", "form.createaccount.password.notempty");

        // Verify input patterns
        Pattern regexPattern = Pattern.compile(regexValidationEmail);
        Matcher regMatcher = regexPattern.matcher(signUpModel.getEmail());

        if (!regMatcher.matches()) {
            errors.rejectValue("email", "field.validation.email.error.validation");
        }
        
        regexPattern = Pattern.compile(regexValidationPassword);
        regMatcher = regexPattern.matcher(signUpModel.getPassword());

        
        if (!regMatcher.matches()) {
            Integer[] pseudoLength = {6,30};
            errors.rejectValue("pseudo", "field.validation.pseudo.error.validation", pseudoLength, "field.validation.default.error.commons");
        }
        
        regexPattern = Pattern.compile(regexValidationPassword);
        regMatcher = regexPattern.matcher(signUpModel.getPassword());

        if (!regMatcher.matches()) {
            Integer[] passwordLength = {6,100};
            errors.rejectValue("password", "field.validation.password.error.validation", passwordLength, "field.validation.default.error.commons");
        }
    }
}
