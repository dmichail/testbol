package com.bol.assignment.controller;


import com.bol.assignment.model.PlayerState;
import com.bol.assignment.service.PlayerStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/players/state")
public class PlayerStateController {

    @Autowired
    public PlayerStateService playerStateService;


    @PostMapping("/")
    public PlayerState createState(){
        return playerStateService.createPlayerState();
    }

    @PutMapping("")
    public void updateState(Long playerId){

    }








}
