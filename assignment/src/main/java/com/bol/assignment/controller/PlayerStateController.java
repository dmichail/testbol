package com.bol.assignment.controller;


import com.bol.assignment.model.PlayerState;
import com.bol.assignment.service.PlayerStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/players")
public class PlayerStateController {


    @Autowired
    public PlayerStateService playerStateService;


    @PostMapping("/{id}/states")
    public PlayerState createState(@PathVariable(value = "id") Long id){
        return playerStateService.createPlayerState(id);
    }

    @PutMapping("")
    public void updateState(Long playerId){

    }








}
