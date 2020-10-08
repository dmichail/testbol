package com.bol.assignment.service;


import com.bol.assignment.model.Player;
import com.bol.assignment.model.PlayerState;
import com.bol.assignment.repository.PlayerStateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.bol.assignment.MyConstants.initKalaha;
import static com.bol.assignment.MyConstants.initPits;

@Service
public class PlayerStateService {


    @Autowired
    public PlayerStateRepository playerStateRepository;

    @Autowired
    public PlayerService playerService;

    public PlayerState createPlayerState(Long playerId)
    {
        Player found = playerService.getPlayerById(playerId);

        PlayerState playerState = new PlayerState(initKalaha,initPits, found);

        playerStateRepository.save(playerState);
        return playerState;
    }


    public PlayerState getStateById(Long playerId){
        return playerStateRepository.findById(playerId).orElseThrow(() -> new RuntimeException("Could not find player State with id: " + playerId));
    }

    public PlayerState updateState(Long playerId, PlayerState updateState){
        PlayerState state = getStateById(playerId);

        state.setPits(updateState.getPits());
        state.setKalaha(updateState.getKalaha());

        return playerStateRepository.save(state);


    }








}
