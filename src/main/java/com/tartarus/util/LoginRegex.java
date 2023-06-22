package com.tartarus.util;

import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class LoginRegex {
    private final Pattern EMAIL_PATTERN = Pattern.compile("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$");
    private final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$");

    public boolean checkPatternEmail(String email){

        boolean result = false;

        if(EMAIL_PATTERN.matcher(email).matches()){
            result = true;
        }

        return result;

    }

    public boolean checkPasswordPattern(String password){

        boolean result = false;

        if(PASSWORD_PATTERN.matcher(password).matches()){
            result = true;
        }

        return result;

    }
}
