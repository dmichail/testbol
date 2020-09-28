package com.bol.assignment.exceptions.player;

public class playerNotFoundException extends RuntimeException {

    public playerNotFoundException(Long id){
        super("Could not find player with id "+ id);
    }
}
