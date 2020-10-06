package com.bol.assignment.controller;


import com.bol.assignment.MyConstants.*;
import com.bol.assignment.dto.AddPlayerDto;
import com.bol.assignment.dto.PlayDto;
import com.bol.assignment.dto.loggedInUser;
import com.bol.assignment.model.Game;
import com.bol.assignment.model.GameState;
import com.bol.assignment.model.Player;
import com.bol.assignment.service.GameService;
import com.bol.assignment.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/games")
public class GameController {

    @Autowired
    public GameService gameService;

    @Autowired
    public PlayerService playerService;



    @PostMapping("/")
    public ResponseEntity<Game> createOrJoinGame(@RequestBody loggedInUser loggedInPlayer){

        Player testplayer = playerService.getPlayerById(loggedInPlayer.getId());

        Optional<Game> existingGame = gameService.getFirstGameByStatus(GameStatus.AWAITING_PLAYER);


        if (existingGame.isPresent()){
            ResponseEntity<Game> joinedGame = joinGame(testplayer, existingGame.get().getId());
            return new ResponseEntity(joinedGame, HttpStatus.OK);

        }else{
            return new ResponseEntity<>(gameService.createGame(testplayer), HttpStatus.CREATED);
        }

    }

    @PostMapping("/{gameId}/join")
    public ResponseEntity<Game> joinGame(Player loggedin, @PathVariable(value = "gameId")Long gameid){

        return new ResponseEntity<>(gameService.joinGame(gameid, loggedin), HttpStatus.ACCEPTED);
    }

    @GetMapping("/{gameId}")
    public Game getGameDetails(@PathVariable(value = "gameId")Long gameId){


        return gameService.getGameById(gameId);
    }


    @DeleteMapping("/{gameId}")
    public void deleteGame(@PathVariable(value = "gameId") Long gameId){

    }

    @PostMapping("/{gameId}/play")
    public Game play(@RequestBody PlayDto playDto, @PathVariable(value = "gameId") Long gameId){
        return gameService.playMove(gameId, playDto.getPit());
    }

    @GetMapping("/allgames/")
    public Game getAll(){
        return gameService.getFirstGameByStatus(GameStatus.AWAITING_PLAYER).orElseThrow(null);
    }











}
