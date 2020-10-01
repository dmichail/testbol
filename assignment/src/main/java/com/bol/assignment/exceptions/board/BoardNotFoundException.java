package com.bol.assignment.exceptions.board;

public class BoardNotFoundException extends  RuntimeException {

    public BoardNotFoundException(Long id){
        super("Could not find board with id "+ id);
    }

}
