package com.bol.assignment.controller;


import com.bol.assignment.requestObjects.AddPlayerRequest;
import com.bol.assignment.requestObjects.PlayerUpdateStatusRequest;
import com.bol.assignment.model.Player;
import com.bol.assignment.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/v1/players")
public class PlayerController {

    @Autowired
    public PlayerService playerService;

    @PostMapping("/")
    ResponseEntity<Player> createPlayer(@RequestBody AddPlayerRequest addPlayerRequest){
        Player resultPlayer = playerService.addPlayer(addPlayerRequest.getName());
        return new ResponseEntity<>(resultPlayer, HttpStatus.CREATED);
    }

    @GetMapping("/")
    ResponseEntity<List<Player>> getPlayers(){
        List<Player> players = playerService.getPlayers();

        return new ResponseEntity<>(players, HttpStatus.FOUND);
    }

    @PutMapping("/{id}/status")
    void updatePlayer(@RequestBody PlayerUpdateStatusRequest newPlayer, @PathVariable(value = "id") Long id){
        playerService.changePlayerStatus(id, newPlayer.getNewStatus());
    }





}
