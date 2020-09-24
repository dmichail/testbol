package com.bol.interviews.kalaha.service;


import com.bol.interviews.kalaha.model.Player;
import com.bol.interviews.kalaha.repository.playerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class playerService {

    @Autowired
    public playerRepository playerRepository;

    public Player addPlayer(String name) {
        Player player = new Player(name);
        playerRepository.save(player);
        return player;
    }

    public Optional<Player> getPlayerById(Long id){
        return playerRepository.findById(id);
    }

    public List<Player> getPlayers(){
        List<Player> playerList = (List<Player>) playerRepository.findAll();
        return playerList;
    }







}
