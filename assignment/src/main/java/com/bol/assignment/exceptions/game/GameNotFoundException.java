package com.bol.assignment.exceptions.game;

public class GameNotFoundException extends RuntimeException {

    public GameNotFoundException(Long id){
        super("Could not find Game with id "+ id);
    }

}
