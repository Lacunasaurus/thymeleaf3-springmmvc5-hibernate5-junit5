package com.lacunasaurus.tutorial.web.validator;

public class TutorialValidator {

    protected final String regexValidationEmail = "^([a-z0-9][a-z0-9._-]*[a-z0-9])[@]([a-z0-9][a-z0-9._-]*[a-z0-9])[.]([a-z0-9]{2,})$";
    protected final String regexValidationLogin = "^[a-zA-Z0-9]{6,30}$";
    protected final String regexValidationPassword = "^[a-zA-Z0-9]{6,100}$";
}
