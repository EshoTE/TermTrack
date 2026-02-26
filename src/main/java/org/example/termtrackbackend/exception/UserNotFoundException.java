package org.example.termtrackbackend.exception;

import org.springframework.web.bind.annotation.GetMapping;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(Integer id){

        super("Could not find the user with id " + id);
    }
}


