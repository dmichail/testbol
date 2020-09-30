package com.bol.assignment.exceptions.pit;

public class PitNotFoundException extends RuntimeException {

    public PitNotFoundException(Long id){
        super("Could not find pit with Id "+ id);
    }

}
