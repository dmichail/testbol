package com.bol.assignment.service;


import com.bol.assignment.dto.PlayerStateDto;
import com.bol.assignment.model.PlayerState;
import com.bol.assignment.repository.PlayerStateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.Arrays;

@Service
public class PlayerStateService {


    @Autowired
    public PlayerStateRepository playerStateRepository;


    public PlayerState createPlayerState()
    {
        PlayerStateDto playerStateDto = new PlayerStateDto();
        playerStateDto.setKalaha(0);
        playerStateDto.setPits(Arrays.asList(6,6,6,6,6,6));

        PlayerState playerState = new PlayerState();
        playerState.setKalaha(0);
        playerState.setPits(Arrays.asList(6,6,6,6,6,6));


        playerStateRepository.save(playerState);

        return playerState;


    }


    public PlayerState getStateById(Long playerId){
        return playerStateRepository.findById(playerId).orElseThrow(() -> new RuntimeException("Could not find player State"));


    }








}
