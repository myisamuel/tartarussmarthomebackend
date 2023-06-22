package com.tartarus.util;

import com.tartarus.model.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class UserErrorHandler {

    private ErrorMessage errorMessage;

    public UserErrorHandler() {
        this.errorMessage = new ErrorMessage();
    }

    public ResponseEntity<?> credidentialIsNotFound(){
        errorMessage.setMessage("Email atau password telah terdaftar");
        return new ResponseEntity<>(errorMessage,HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> regexFormatIsWrong(){
        errorMessage.setMessage("Format Email atau password salah");
        return new ResponseEntity<>(errorMessage,HttpStatus.NOT_ACCEPTABLE);
    }
}
