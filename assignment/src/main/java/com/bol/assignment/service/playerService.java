package com.bol.assignment.service;


import com.bol.assignment.dto.playerDto;
import com.bol.assignment.exceptions.player.playerNotFoundException;
import com.bol.assignment.model.Player;
import com.bol.assignment.repository.playerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bol.assignment.constants.PlayerStatus;

import java.util.List;

@Service
public class playerService {

    @Autowired(required = false)
    public playerRepository playerRepository;

    ModelMapper modelMapper = new ModelMapper();


    public playerDto addPlayer(String name){
        Player player = new Player(name);
        playerRepository.save(player);

        playerDto playerDto = modelMapper.map(player, com.bol.assignment.dto.playerDto.class);

        return playerDto;
    }

    public Player getPlayerById(Long id){
        return playerRepository.findById(id).orElseThrow(() -> new playerNotFoundException(id));
    }

    public void changePlayerStatus(Long playerId,PlayerStatus newStatus){
        Player updatePlayer = getPlayerById(playerId);
        playerRepository.save(updatePlayer);
    }

    public List<Player> getPlayers(){
        List<Player> playerList = (List<Player>) playerRepository.findAll();
        return playerList;



    }





}
