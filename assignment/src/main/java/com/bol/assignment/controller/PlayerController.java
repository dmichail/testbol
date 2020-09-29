package com.bol.assignment.controller;


import com.bol.assignment.dto.AddPlayerDto;
import com.bol.assignment.dto.PlayerUpdateStatusDto;
import com.bol.assignment.model.Player;
import com.bol.assignment.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PlayerController {

    @Autowired
    public PlayerService playerService;

    /*@PostMapping("/players")
    playerDto addPlayer(@RequestBody addPlayerDto addPlayerDto) {
        return playerService.addPlayer(addPlayerDto.getName());
    }*/


    @PostMapping("/players/")
    Player addPlayer(@RequestBody AddPlayerDto addPlayerDto){
        Player resultPlayer = playerService.addPlayer(addPlayerDto.getName());
        return resultPlayer;
    }


    @GetMapping("/players/")
    ResponseEntity<List<Player>> getPlayers(){
        List<Player> players = playerService.getPlayers();

        return new ResponseEntity<>(players, HttpStatus.FOUND);
    }

    @PutMapping("/players/")
    void updatePlayer(@RequestBody PlayerUpdateStatusDto newPlayer){
        playerService.changePlayerStatus(newPlayer.getPlayerId(), newPlayer.getNewStatus());
    }





}
