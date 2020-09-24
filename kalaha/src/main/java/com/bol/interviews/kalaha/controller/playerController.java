package com.bol.interviews.kalaha.controller;

import com.bol.interviews.kalaha.dto.playerCreatedDto;
import com.bol.interviews.kalaha.dto.playerNameDto;
import com.bol.interviews.kalaha.model.Player;
import com.bol.interviews.kalaha.service.playerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.google.gson.*;

import java.util.List;

@RestController
public class playerController {
    @Autowired
    playerService playerService;

    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    ModelMapper modelMapper = new ModelMapper();

    @PostMapping("/player")
    playerCreatedDto createPlayer(@RequestBody playerNameDto playerNameDto){
        Player player = playerService.addPlayer(playerNameDto.getPlayerName());
        playerCreatedDto playerCreatedDto = modelMapper.map(player, playerCreatedDto.class);

        return playerCreatedDto;
    }

    @GetMapping("/player/{id}")
    public ResponseEntity<playerNameDto> getPlayerName(@PathVariable(value = "id", required = false) Long id){
        Player player = playerService.getPlayerById(id).orElseGet(null);
        if (player == null)
            return new ResponseEntity<playerNameDto>(HttpStatus.NOT_FOUND);

        playerNameDto playerNameDto = new playerNameDto(player.getplayerName());

        return new ResponseEntity<playerNameDto>(playerNameDto, HttpStatus.FOUND);
    }

    @GetMapping("/players/")
    public List<Player> getAll(){
        return playerService.getPlayers();
    }





}
