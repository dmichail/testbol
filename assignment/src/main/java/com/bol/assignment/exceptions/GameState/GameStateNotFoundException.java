package com.bol.assignment.exceptions.GameState;

import org.springframework.http.HttpStatus;

public class GameStateNotFoundException extends RuntimeException {

    public GameStateNotFoundException(Long id)
    {
        super("Could not find game state with id" + id);
    }
}
