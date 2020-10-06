package com.bol.assignment.exceptions.player.advice;


import com.bol.assignment.exceptions.player.PlayerAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Component
public class PlayerAlreadyExistsAdvice {

    @ResponseBody
    @ExceptionHandler(PlayerAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    String playerAlreadyExistsHandler(PlayerAlreadyExistsException ex){
        return ex.getMessage();
    }


}
