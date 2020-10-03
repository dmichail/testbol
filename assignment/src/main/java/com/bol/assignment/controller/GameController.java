package com.bol.assignment.controller;


import com.bol.assignment.MyConstants;
import com.bol.assignment.dto.PlayDto;
import com.bol.assignment.model.Game;
import com.bol.assignment.model.GameState;
import com.bol.assignment.model.Player;
import com.bol.assignment.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/games")
public class GameController {

    @Autowired
    public GameService gameService;



    @PostMapping("/")
    public Game createOrJoinGame(Player loggedInPlayer){


        //get current games with state awating for player
        //if found, join player in it.
        //if not, create new game and assign first player.

        Player testplayer = new  Player();
        testplayer.setId(1);
        testplayer.setName("baby");
        testplayer.setStatus(MyConstants.PlayerStatus.ONLINE);



        return gameService.createGame(testplayer);
    }

    @PostMapping("/join/{gameId}")
    public Game joinGame(Player loggedin, @PathVariable(value = "gameId")Long gameid){

        Player newPlayer = new Player("mike");
        newPlayer.setId(2);




        return gameService.joinGame(gameid, newPlayer);
    }

    @GetMapping("/{gameId}")
    public Game getGameDetails(@PathVariable(value = "gameId")Long gameId){


        return gameService.getGameById(gameId);
    }


    @DeleteMapping("/{gameId}")
    public void deleteGame(@PathVariable(value = "gameId") Long gameId){

    }

    @PostMapping("/{gameId}/play")
    public GameState play(@RequestBody PlayDto playDto){

        return gameService.playMove(playDto.getPit());
    }

    @GetMapping("/allgames/")
    public List<Game> getAll(){
        return gameService.getGamesByStatus(MyConstants.GameStatus.AWAITING_PLAYER);

    }











}
