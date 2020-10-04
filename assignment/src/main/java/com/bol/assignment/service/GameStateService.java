package com.bol.assignment.service;


import com.bol.assignment.MyConstants;
import com.bol.assignment.model.Game;
import com.bol.assignment.model.GameState;
import com.bol.assignment.model.Player;
import com.bol.assignment.model.PlayerState;
import com.bol.assignment.repository.GameStateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToOne;
import java.util.HashMap;
import java.util.Map;

@Service
public class GameStateService {

    @Autowired
    public GameStateRepository gameStateRepository;

    public GameState createGameState(PlayerState playerState){
        Map<Long, PlayerState> stateMap = new HashMap<>();
        stateMap.put(playerState.getPlayer().getId(), playerState);

        GameState gameState = new GameState(playerState.getPlayer().getId(), null,
                false, stateMap);

        gameStateRepository.save(gameState);

        return gameState;

    }

    public void changeTurn(Long gameStateId,Long playerId){
        GameState gameState = gameStateRepository.findById(gameStateId)
                .orElseThrow(() -> new RuntimeException("Could not find gameState with id:" + gameStateId));

        gameState.setCurrPlayerID(playerId);

        gameStateRepository.save(gameState);
    }


}
