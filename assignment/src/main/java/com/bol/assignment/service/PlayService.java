package com.bol.assignment.service;

import com.bol.assignment.model.PlayerState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayService {

    private PlayerStateService playerStateService;
    private GameStateService gameStateService;
    private GameService gameService;

    @Autowired
    public PlayService(PlayerStateService playerStateService, GameStateService gameStateService, GameService gameService){
        this.playerStateService = playerStateService;
        this.gameStateService = gameStateService;
        this.gameService = gameService;
    }





}
