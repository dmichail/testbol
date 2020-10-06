package com.bol.assignment.service;


import com.bol.assignment.dto.PlayerDto;
import com.bol.assignment.exceptions.player.PlayerNotFoundException;
import com.bol.assignment.model.Player;
import com.bol.assignment.repository.PlayerRepository;
import org.dom4j.rule.Mode;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bol.assignment.MyConstants.PlayerStatus;

import java.util.List;

@Service
public class PlayerService {

    @Autowired
    public PlayerRepository playerRepository;

    ModelMapper modelMapper = new ModelMapper();

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public Player addPlayer(PlayerDto playerDto){
        Player player = new Player(playerDto.getName());
        playerRepository.save(player);

        return player;
    }

    public Player addPlayer(String name){
        Player player = new Player(name);
        playerRepository.save(player);

        return player;
    }

    public Player getPlayerById(Long id){
        return playerRepository.findById(id).orElseThrow(() -> new PlayerNotFoundException(id));
    }

    public void changePlayerStatus(Long playerId,PlayerStatus newStatus){
        Player updatePlayer = getPlayerById(playerId);
        updatePlayer.setStatus(newStatus);
        playerRepository.save(updatePlayer);
    }

    public List<Player> getPlayers(){
        List<Player> playerList = (List<Player>) playerRepository.findAll();
        return playerList;
    }





}