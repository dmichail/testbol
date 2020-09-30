package com.bol.assignment.exceptions.pit.advice;


import com.bol.assignment.exceptions.pit.PitNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Component
public class PitNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(PitNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String pitNotFoundHandler(PitNotFoundException ex){
        return ex.getMessage();
    }


}
