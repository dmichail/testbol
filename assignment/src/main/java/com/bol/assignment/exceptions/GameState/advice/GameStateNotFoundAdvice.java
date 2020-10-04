package com.bol.assignment.exceptions.GameState.advice;


import com.bol.assignment.exceptions.GameState.GameStateNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
@Component
public class GameStateNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(GameStateNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String gameStateNotFoundHandler(GameStateNotFoundException ex){
        return ex.getMessage();
    }


}
