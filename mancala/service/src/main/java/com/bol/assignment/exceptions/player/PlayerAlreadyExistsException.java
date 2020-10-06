package com.bol.assignment.exceptions.player;



public class PlayerAlreadyExistsException extends RuntimeException {

    public PlayerAlreadyExistsException(Long id){
        super("Player with Id: " + id + " already exists in the game." );
    }

}
