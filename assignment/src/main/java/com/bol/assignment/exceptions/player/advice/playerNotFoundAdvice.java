package com.bol.assignment.exceptions.player.advice;


import com.bol.assignment.exceptions.player.playerNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class playerNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(playerNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String playerNotFoundHandler(playerNotFoundException ex){
        return ex.getMessage();
    }

}
