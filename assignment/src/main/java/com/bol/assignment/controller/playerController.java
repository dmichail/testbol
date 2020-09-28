package com.bol.assignment.controller;


import com.bol.assignment.dto.addPlayerDto;
import com.bol.assignment.dto.playerDto;
import com.bol.assignment.dto.playerUpdateStatusDto;
import com.bol.assignment.model.Player;
import com.bol.assignment.service.playerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class playerController {

    @Autowired
    public playerService playerService;

    @PostMapping("/players")
    playerDto addPlayer(@RequestBody addPlayerDto addPlayerDto){
        return playerService.addPlayer(addPlayerDto.getPlayerName());
    }


    @GetMapping("/players")
    ResponseEntity<List<Player>> getPlayers(){
        List<Player> players = playerService.getPlayers();

        return new ResponseEntity<>(players, HttpStatus.FOUND);
    }

    @PutMapping("/players/")
    void updatePlayer(@RequestBody playerUpdateStatusDto newPlayer){
        playerService.changePlayerStatus(newPlayer.getPlayerId(), newPlayer.getNewStatus());
    }





}
