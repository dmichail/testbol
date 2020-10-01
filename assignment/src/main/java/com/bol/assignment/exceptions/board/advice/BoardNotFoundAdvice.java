package com.bol.assignment.exceptions.board.advice;

import com.bol.assignment.exceptions.board.BoardNotFoundException;
import com.bol.assignment.exceptions.player.PlayerNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
@Component
public class BoardNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(BoardNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String boardNotFoundHandler(BoardNotFoundException ex){
        return ex.getMessage();
    }

}

