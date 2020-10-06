package com.bol.assignment.controller;


import com.bol.assignment.MyConstants.*;
import com.bol.assignment.dto.PlayDto;
import com.bol.assignment.dto.LoggedInUser;
import com.bol.assignment.model.Game;
import com.bol.assignment.model.Player;
import com.bol.assignment.service.GameService;
import com.bol.assignment.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/v1/games")
public class GameController {

    private GameService gameService;
    private PlayerService playerService;

    @Autowired
    public GameController(GameService gameService, PlayerService playerService){
        this.gameService = gameService;
        this.playerService = playerService;
    }

    @PostMapping("/")
    public ResponseEntity<Game> createOrJoinGame(@RequestBody LoggedInUser loggedInPlayer){

        Player testPlayer = playerService.getPlayerById(loggedInPlayer.getId());

        Optional<Game> existingGame = gameService.getFirstGameByStatus(GameStatus.AWAITING_PLAYER);

        if (existingGame.isPresent()){
            ResponseEntity<Game> joinedGame = joinGame(testPlayer, existingGame.get().getId());
            return new ResponseEntity(joinedGame, HttpStatus.OK);

        }else{
            return new ResponseEntity<>(gameService.createGame(testPlayer), HttpStatus.CREATED);
        }

    }

    @PostMapping("/{gameId}/join")
    public ResponseEntity<Game> joinGame(Player loggedIn, @PathVariable(value = "gameId")Long gameId){
        return new ResponseEntity<>(gameService.joinGame(gameId, loggedIn), HttpStatus.ACCEPTED);
    }

    @GetMapping("/{gameId}")
    public ResponseEntity<Game> getGameDetails(@PathVariable(value = "gameId")Long gameId){
        return new ResponseEntity<>(gameService.getGameById(gameId), HttpStatus.FOUND) ;
    }

    @DeleteMapping("/{gameId}")
    public void deleteGame(@PathVariable(value = "gameId") Long gameId){
        gameService.deleteGame(gameId);
    }

    @PostMapping("/{gameId}/play")
    public Game play(@RequestBody PlayDto playDto, @PathVariable(value = "gameId") Long gameId){
        return gameService.playMove(gameId, playDto.getPit());
    }









}
